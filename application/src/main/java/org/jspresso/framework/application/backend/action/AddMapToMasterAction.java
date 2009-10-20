/*
 * Copyright (c) 2005-2009 Vincent Vandenschrick. All rights reserved.
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
package org.jspresso.framework.application.backend.action;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jspresso.framework.model.component.service.ILifecycleInterceptor;
import org.jspresso.framework.model.descriptor.IComponentDescriptor;
import org.jspresso.framework.model.descriptor.IPropertyDescriptor;
import org.jspresso.framework.model.descriptor.IScalarPropertyDescriptor;
import org.jspresso.framework.util.collection.ObjectEqualityMap;

/**
 * An action used in master/detail views where models are backed by maps to
 * create and add a new detail to a master domain object.
 * <p>
 * Copyright (c) 2005-2009 Vincent Vandenschrick. All rights reserved.
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
public class AddMapToMasterAction extends AbstractAddCollectionToMasterAction {

  /**
   * Gets the new map component to add.
   * 
   * @param context
   *          the action context.
   * @return the map to add to the collection.
   */
  @Override
  @SuppressWarnings("unchecked")
  protected List<?> getAddedComponents(Map<String, Object> context) {
    IComponentDescriptor<?> componentDescriptor = getModelDescriptor(context)
        .getCollectionDescriptor().getElementDescriptor();
    Map<String, Object> newMap = new ObjectEqualityMap<String, Object>();
    for (IPropertyDescriptor propertyDescriptor : componentDescriptor
        .getPropertyDescriptors()) {
      if (propertyDescriptor instanceof IScalarPropertyDescriptor
          && ((IScalarPropertyDescriptor) propertyDescriptor).getDefaultValue() != null) {
        newMap.put(propertyDescriptor.getName(),
            ((IScalarPropertyDescriptor) propertyDescriptor).getDefaultValue());
      }
    }
    List<ILifecycleInterceptor<?>> interceptors = componentDescriptor
        .getLifecycleInterceptors();
    if (interceptors != null) {
      for (ILifecycleInterceptor<?> interceptor : interceptors) {
        ((ILifecycleInterceptor<Map<String, Object>>) interceptor).onCreate(
            newMap, getEntityFactory(context), getApplicationSession(context)
                .getPrincipal(), getApplicationSession(context));
      }
    }
    return Collections.singletonList(newMap);
  }

}
