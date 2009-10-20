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
package org.jspresso.framework.view.descriptor.basic;

import java.util.List;

import org.jspresso.framework.model.descriptor.ICollectionDescriptor;
import org.jspresso.framework.model.descriptor.ICollectionPropertyDescriptor;
import org.jspresso.framework.model.descriptor.IModelDescriptor;
import org.jspresso.framework.model.descriptor.IReferencePropertyDescriptor;
import org.jspresso.framework.view.descriptor.IPropertyViewDescriptor;

/**
 * Default implementation of a property view descriptor.
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
public class BasicPropertyViewDescriptor extends BasicViewDescriptor implements
    IPropertyViewDescriptor {

  private List<String> renderedChildProperties;
  private int          width;
  private String       labelBackground;
  private String       labelFont;
  private String       labelForeground;

  /**
   * Constructs a new <code>BasicPropertyViewDescriptor</code> instance.
   */
  public BasicPropertyViewDescriptor() {
    width = 1;
  }

  /**
   * {@inheritDoc}
   */
  public List<String> getRenderedChildProperties() {
    if (renderedChildProperties != null) {
      return renderedChildProperties;
    }
    // IPropertyDescriptor childPropertyDescriptor =
    // ((IComponentDescriptorProvider<?>) getModelDescriptor())
    // .getComponentDescriptor().getPropertyDescriptor(getName());
    IModelDescriptor propertyDescriptor = getModelDescriptor();
    if (propertyDescriptor instanceof ICollectionPropertyDescriptor<?>) {
      return ((ICollectionDescriptor<?>) ((ICollectionPropertyDescriptor<?>) propertyDescriptor)
          .getCollectionDescriptor()).getElementDescriptor()
          .getRenderedProperties();
    } else if (propertyDescriptor instanceof IReferencePropertyDescriptor<?>) {
      return ((IReferencePropertyDescriptor<?>) propertyDescriptor)
          .getReferencedDescriptor().getRenderedProperties();
    }
    return null;
  }

  /**
   * Sets the renderedChildProperties.
   * 
   * @param renderedChildProperties
   *          the renderedChildProperties to set.
   */
  public void setRenderedChildProperties(List<String> renderedChildProperties) {
    this.renderedChildProperties = renderedChildProperties;
  }

  /**
   * Gets the width.
   * 
   * @return the width.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Sets the width.
   * 
   * @param width
   *          the width to set.
   */
  public void setWidth(int width) {
    this.width = width;
  }

  /**
   * Gets the labelBackground.
   * 
   * @return the labelBackground.
   */
  public String getLabelBackground() {
    return labelBackground;
  }

  /**
   * Sets the labelBackground.
   * 
   * @param labelBackground
   *          the labelBackground to set.
   */
  public void setLabelBackground(String labelBackground) {
    this.labelBackground = labelBackground;
  }

  /**
   * Gets the labelFont.
   * 
   * @return the labelFont.
   */
  public String getLabelFont() {
    return labelFont;
  }

  /**
   * Sets the labelFont.
   * 
   * @param labelFont
   *          the labelFont to set.
   */
  public void setLabelFont(String labelFont) {
    this.labelFont = labelFont;
  }

  /**
   * Gets the labelForeground.
   * 
   * @return the labelForeground.
   */
  public String getLabelForeground() {
    return labelForeground;
  }

  /**
   * Sets the labelForeground.
   * 
   * @param labelForeground
   *          the labelForeground to set.
   */
  public void setLabelForeground(String labelForeground) {
    this.labelForeground = labelForeground;
  }
}
