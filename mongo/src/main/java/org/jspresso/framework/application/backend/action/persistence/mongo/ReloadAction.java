/*
 * Copyright (c) 2005-2014 Vincent Vandenschrick. All rights reserved.
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
package org.jspresso.framework.application.backend.action.persistence.mongo;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jspresso.framework.action.IActionHandler;
import org.jspresso.framework.model.entity.IEntity;

/**
 * Reloads the entities provided by the context {@code ActionParameter}.
 * The whole entities graphs are reloaded from the persistent store.
 *
 * @version $LastChangedRevision$
 * @author Vincent Vandenschrick
 */
public class ReloadAction extends AbstractMongoAction {

  /**
   * Reloads the object(s) provided by the action context in a transaction.
   * <p>
   * {@inheritDoc}
   */
  @Override
  public boolean execute(IActionHandler actionHandler,
      final Map<String, Object> context) {
    getController(context).clearPendingOperations();
    List<IEntity> entitiesToReload = getEntitiesToReload(context);
    for (Iterator<IEntity> ite = entitiesToReload.iterator(); ite.hasNext();) {
      IEntity entity = ite.next();
      reloadEntity(entity, context);
    }
    return super.execute(actionHandler, context);
  }

  /**
   * Gets the list of entities to reload.
   * 
   * @param context
   *          the action context.
   * @return the list of entities to save.
   */
  protected List<IEntity> getEntitiesToReload(Map<String, Object> context) {
    return getActionParameter(context);
  }
}