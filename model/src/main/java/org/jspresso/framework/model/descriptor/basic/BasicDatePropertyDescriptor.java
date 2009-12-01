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
package org.jspresso.framework.model.descriptor.basic;

import java.util.Date;

import org.jspresso.framework.model.descriptor.EDateType;
import org.jspresso.framework.model.descriptor.IDatePropertyDescriptor;
import org.jspresso.framework.model.descriptor.query.ComparableQueryStructureDescriptor;

/**
 * Describes a date based property. Wether the date property should include time
 * information or not, can be configured using the type property.
 * 
 * @version $LastChangedRevision$
 * @author Vincent Vandenschrick
 */
public class BasicDatePropertyDescriptor extends BasicScalarPropertyDescriptor
    implements IDatePropertyDescriptor {

  private EDateType type;

  /**
   * Constructs a new <code>BasicDatePropertyDescriptor</code> instance.
   */
  public BasicDatePropertyDescriptor() {
    type = EDateType.DATE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public BasicDatePropertyDescriptor clone() {
    BasicDatePropertyDescriptor clonedDescriptor = (BasicDatePropertyDescriptor) super
        .clone();

    return clonedDescriptor;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ComparableQueryStructureDescriptor createQueryDescriptor() {
    return new ComparableQueryStructureDescriptor(super.createQueryDescriptor());
  }

  /**
   * {@inheritDoc}
   */
  public Class<?> getModelType() {
    return Date.class;
  }

  /**
   * {@inheritDoc}
   */
  public EDateType getType() {
    return type;
  }

  /**
   * Sets wether this property should contain time information or not. The
   * incoming value must be part of the <code>EDateType</code> enum, i.e. :
   * <ul>
   * <li><code>DATE</code> if the property should only contain the date
   * information</li>
   * <li><code>DATE_TIME</code> if the property should contain both date and
   * time information</li>
   * </ul>
   * Default value is <code>EDateType.DATE</code>.
   * 
   * @param type
   *          the type to set.
   */
  public void setType(EDateType type) {
    this.type = type;
  }
}
