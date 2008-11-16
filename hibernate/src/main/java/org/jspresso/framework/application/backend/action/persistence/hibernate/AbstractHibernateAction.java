/*
 * Copyright (c) 2005-2008 Vincent Vandenschrick. All rights reserved.
 *
 *  This file is part of the Jspresso framework.
 *
 *  Jspresso is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Jspresso is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Jspresso.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.jspresso.framework.application.backend.action.persistence.hibernate;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.jspresso.framework.application.backend.action.AbstractBackendAction;
import org.jspresso.framework.application.backend.persistence.hibernate.HibernateBackendController;
import org.jspresso.framework.application.backend.session.MergeMode;
import org.jspresso.framework.model.descriptor.ICollectionPropertyDescriptor;
import org.jspresso.framework.model.descriptor.IComponentDescriptor;
import org.jspresso.framework.model.descriptor.IPropertyDescriptor;
import org.jspresso.framework.model.descriptor.IReferencePropertyDescriptor;
import org.jspresso.framework.model.entity.IEntity;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * This the root abstract class of all hibernate related persistence actions.
 * <p>
 * Copyright (c) 2005-2008 Vincent Vandenschrick. All rights reserved.
 * <p>
 * This file is part of the Jspresso framework. Jspresso is free software: you
 * can redistribute it and/or modify it under the terms of the GNU Lesser
 * General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version. Jspresso is
 * distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details. You should have received a copy of the GNU Lesser General Public
 * License along with Jspresso. If not, see <http://www.gnu.org/licenses/>.
 * <p>
 * 
 * @version $LastChangedRevision$
 * @author Vincent Vandenschrick
 */
public abstract class AbstractHibernateAction extends AbstractBackendAction {

  /**
   * Performs necessary cleanings when an entity is deleted.
   * 
   * @param entity
   *          the deleted entity.
   * @param context
   *          The action context.
   * @throws IllegalAccessException
   *           whenever this kind of exception occurs.
   * @throws InvocationTargetException
   *           whenever this kind of exception occurs.
   * @throws NoSuchMethodException
   *           whenever this kind of exception occurs.
   */
  @SuppressWarnings("unchecked")
  protected void cleanRelationshipsOnDeletion(IEntity entity,
      Map<String, Object> context) throws IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {
    try {
      entity.setPropertyProcessorsEnabled(false);
      IComponentDescriptor entityDescriptor = getEntityFactory(context)
          .getComponentDescriptor(entity.getContract());
      for (Map.Entry<String, Object> property : entity.straightGetProperties()
          .entrySet()) {
        if (property.getValue() != null) {
          IPropertyDescriptor propertyDescriptor = entityDescriptor
              .getPropertyDescriptor(property.getKey());
          if (propertyDescriptor instanceof IReferencePropertyDescriptor) {
            getAccessorFactory(context).createPropertyAccessor(
                property.getKey(), entity.getContract()).setValue(entity, null);
          } else if (propertyDescriptor instanceof ICollectionPropertyDescriptor) {
            if (((ICollectionPropertyDescriptor) propertyDescriptor)
                .isComposition()) {
              getApplicationSession(context).initializePropertyIfNeeded(entity,
                  propertyDescriptor);
              for (IEntity composedEntity : new ArrayList<IEntity>(
                  (Collection<IEntity>) property.getValue())) {
                cleanRelationshipsOnDeletion(composedEntity, context);
              }
            } else if (propertyDescriptor.isModifiable()) {
              getAccessorFactory(context).createPropertyAccessor(
                  property.getKey(), entity.getContract()).setValue(entity,
                  null);
            }
          }
        }
      }
    } finally {
      entity.setPropertyProcessorsEnabled(true);
    }
    getApplicationSession(context).deleteEntity(entity);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected HibernateBackendController getController(Map<String, Object> context) {
    return (HibernateBackendController) super.getController(context);
  }

  /**
   * Gets the hibernateTemplate.
   * 
   * @param context
   *          the action context.
   * @return the hibernateTemplate.
   */
  protected HibernateTemplate getHibernateTemplate(Map<String, Object> context) {
    return getController(context).getHibernateTemplate();
  }

  /**
   * Gets the transactionTemplate.
   * 
   * @param context
   *          the action context.
   * @return the transactionTemplate.
   */
  protected TransactionTemplate getTransactionTemplate(
      Map<String, Object> context) {
    return getController(context).getTransactionTemplate();
  }

  /**
   * This method must be called to (re)attach application session entities to
   * the current hibernate session.
   * 
   * @param entity
   *          the entity to merge.
   * @param hibernateSession
   *          the hibernate session
   * @param context
   *          the action context.
   * @return the merged entity.
   */
  protected IEntity mergeInHibernate(IEntity entity, Session hibernateSession,
      Map<String, Object> context) {
    return mergeInHibernate(Collections.singletonList(entity),
        hibernateSession, context).get(0);
  }

  /**
   * This method must be called to (re)attach application session entities to
   * the current hibernate session.
   * 
   * @param entities
   *          the entities to merge.
   * @param hibernateSession
   *          the hibernate session
   * @param context
   *          the action context.
   * @return the merged entity.
   */
  protected List<IEntity> mergeInHibernate(List<IEntity> entities,
      Session hibernateSession, Map<String, Object> context) {
    List<IEntity> mergedEntities = getApplicationSession(context)
        .cloneInUnitOfWork(entities);
    Set<IEntity> alreadyLocked = new HashSet<IEntity>();
    for (IEntity mergedEntity : mergedEntities) {
      lockInHibernate(mergedEntity, hibernateSession, alreadyLocked, context);
    }
    return mergedEntities;
  }

  /**
   * Reloads an entity in hibernate.
   * 
   * @param entity
   *          the entity to save.
   * @param context
   *          the action context.
   */
  protected void reloadEntity(IEntity entity, Map<String, Object> context) {
    if (entity.isPersistent()) {
      HibernateTemplate hibernateTemplate = getHibernateTemplate(context);
      getApplicationSession(context).merge(
          (IEntity) hibernateTemplate.load(entity.getContract().getName(),
              entity.getId()), MergeMode.MERGE_CLEAN_EAGER);
    }
  }

  /**
   * Saves an entity in hibernate.
   * 
   * @param entity
   *          the entity to save.
   * @param context
   *          the action context.
   */
  protected void saveEntity(final IEntity entity,
      final Map<String, Object> context) {
    getHibernateTemplate(context).execute(new HibernateCallback() {

      public Object doInHibernate(Session session) {
        session.clear(); // important to avoid duplicates in session when
        // looping on saves.

        if (!getApplicationSession(context).isUpdatedInUnitOfWork(entity)) {
          IEntity mergedEntity = mergeInHibernate(entity, session, context);
          session.saveOrUpdate(mergedEntity);
          session.flush();
        }
        return null;
      }
    });
  }

  @SuppressWarnings("unchecked")
  private void lockInHibernate(IEntity entity, Session hibernateSession,
      Set<IEntity> alreadyLocked, Map<String, Object> context) {
    if (alreadyLocked.add(entity)) {
      if (entity.isPersistent()) {
        try {
          hibernateSession.lock(entity, LockMode.NONE);
        } catch (Exception ex) {
          ex.printStackTrace();
          hibernateSession.evict(hibernateSession.get(entity.getContract(),
              entity.getId()));
          hibernateSession.lock(entity, LockMode.NONE);
        }
        Map<String, Object> entityProperties = entity.straightGetProperties();
        for (Map.Entry<String, Object> property : entityProperties.entrySet()) {
          if (Hibernate.isInitialized(property.getValue())) {
            if (property.getValue() instanceof IEntity) {
              lockInHibernate((IEntity) property.getValue(), hibernateSession,
                  alreadyLocked, context);
            } else if (property.getValue() instanceof Collection) {
              for (Iterator<IEntity> ite = ((Collection<IEntity>) property
                  .getValue()).iterator(); ite.hasNext();) {
                lockInHibernate(ite.next(), hibernateSession, alreadyLocked,
                    context);
              }
            }
          }
        }
      }
    }
  }
}
