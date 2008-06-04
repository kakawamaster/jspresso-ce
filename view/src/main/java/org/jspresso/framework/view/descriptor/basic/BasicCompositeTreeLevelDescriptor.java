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
package org.jspresso.framework.view.descriptor.basic;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jspresso.framework.view.descriptor.ICompositeTreeLevelDescriptor;
import org.jspresso.framework.view.descriptor.ITreeLevelDescriptor;


/**
 * Basic implementation of a composite subtree view descriptor.
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
public class BasicCompositeTreeLevelDescriptor extends BasicTreeLevelDescriptor
    implements ICompositeTreeLevelDescriptor {

  private Map<String, ITreeLevelDescriptor> childrenDescriptors;

  /**
   * {@inheritDoc}
   */
  public ITreeLevelDescriptor getChildDescriptor(String name) {
    if (childrenDescriptors != null) {
      return childrenDescriptors.get(name);
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  public List<ITreeLevelDescriptor> getChildrenDescriptors() {
    if (childrenDescriptors != null) {
      return new ArrayList<ITreeLevelDescriptor>(childrenDescriptors.values());
    }
    return null;
  }

  /**
   * Sets the childrenDescriptors.
   * 
   * @param childrenDescriptors
   *            the childrenDescriptors to set.
   */
  public void setChildrenDescriptors(
      List<ITreeLevelDescriptor> childrenDescriptors) {
    this.childrenDescriptors = new LinkedHashMap<String, ITreeLevelDescriptor>();
    for (ITreeLevelDescriptor descriptor : childrenDescriptors) {
      // String nodeGroupDescriptorName = descriptor.getNodeGroupDescriptor()
      // .getName();
      // if (nodeGroupDescriptorName == null) {
      String nodeGroupDescriptorName = descriptor.getNodeGroupDescriptor()
          .getModelDescriptor().getName();
      // }
      this.childrenDescriptors.put(nodeGroupDescriptorName, descriptor);
    }
  }
}
