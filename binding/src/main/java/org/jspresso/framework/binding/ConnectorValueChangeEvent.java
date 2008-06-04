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

import java.util.EventObject;

/**
 * A "ConnectorValueChangeEvent" event gets delivered whenever a connector
 * detects a change in its connectee's value. A ConnectorValueChangeEvent object
 * is sent as an argument to the IConnectorValueChangeListener methods. Normally
 * ConnectorValueChangeEvent are accompanied by the old and new value of the
 * changed value. If the new value is a primitive type (such as int or boolean)
 * it must be wrapped as the corresponding java.lang.* Object type (such as
 * Integer or Boolean). Null values may be provided for the old and the new
 * values if their true values are not known.
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
public class ConnectorValueChangeEvent extends EventObject {

  private static final long serialVersionUID = -8122264101249785686L;

  /**
   * New value for connector.
   */
  private Object            newValue;

  /**
   * Previous value for connector.
   */
  private Object            oldValue;

  /**
   * Constructs a new <code>ConnectorValueChangeEvent</code>.
   * 
   * @param source
   *            the connector that initiated the event.
   * @param oldValue
   *            the old value of the connector.
   * @param newValue
   *            the new value of the connector.
   */
  public ConnectorValueChangeEvent(IValueConnector source, Object oldValue,
      Object newValue) {
    super(source);
    this.newValue = newValue;
    this.oldValue = oldValue;
  }

  /**
   * Gets the new value of the connector.
   * 
   * @return The new value of the connector, expressed as an Object.
   */
  public Object getNewValue() {
    return newValue;
  }

  /**
   * Gets the old value of the connector.
   * 
   * @return The old value of the connector, expressed as an Object.
   */
  public Object getOldValue() {
    return oldValue;
  }

  /**
   * Narrows return type.
   * <p>
   * {@inheritDoc}
   */
  @Override
  public IValueConnector getSource() {
    return (IValueConnector) source;
  }
}
