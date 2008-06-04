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
package org.jspresso.framework.model.descriptor.basic;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jspresso.framework.model.descriptor.IComponentDescriptor;


/**
 * Implementation of enumeration based on types of components registered.
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
public class TypeEnumerationPropertyDescriptor extends
    BasicEnumerationPropertyDescriptor {

  /**
   * {@inheritDoc}
   */
  @Override
  public TypeEnumerationPropertyDescriptor clone() {
    TypeEnumerationPropertyDescriptor clonedDescriptor = (TypeEnumerationPropertyDescriptor) super
        .clone();

    return clonedDescriptor;
  }

  /**
   * Sets the componentDescriptorList property.
   * 
   * @param componentDescriptorList
   *            the componentDescriptorList to set.
   */
  public void setComponentDescriptors(
      List<IComponentDescriptor<?>> componentDescriptorList) {
    Map<String, String> componentDescriptorImages = new LinkedHashMap<String, String>();
    for (IComponentDescriptor<?> componentDescriptor : componentDescriptorList) {
      componentDescriptorImages.put(componentDescriptor.getName(),
          componentDescriptor.getIconImageURL());
    }
    setValuesAndIconImageUrls(componentDescriptorImages);
  }
}
