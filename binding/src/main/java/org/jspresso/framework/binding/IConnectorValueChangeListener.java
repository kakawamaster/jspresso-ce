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
package org.jspresso.framework.binding;

/**
 * This public interfacehas to be implemented by classes wishing to keep track
 * of of connectors modifications. This is typically used when binding two
 * connectors together (in a MVC relationship for instance) where the 2
 * connectors will listen for eachother value.
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
public interface IConnectorValueChangeListener {

  /**
   * This method is triggered whenever a connector detects that its peer value
   * changed. Ideally this method will only fire when <code>oldValue</code>
   * differs from <code>newValue</code>.
   * 
   * @param evt
   *            The event representing the change. This event will have :
   *            <li><code>source</code> set to the connector which initiated
   *            the event.
   *            <li><code>oldValue</code> set to the old value of the source
   *            connector.
   *            <li><code>newValue</code> set to the new value of the source
   *            connector.
   */
  void connectorValueChange(ConnectorValueChangeEvent evt);

}
