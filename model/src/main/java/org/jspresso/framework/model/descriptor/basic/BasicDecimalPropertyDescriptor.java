/*
 * Copyright (c) 2005-2012 Vincent Vandenschrick. All rights reserved.
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

import java.math.BigDecimal;

import org.jspresso.framework.model.descriptor.IDecimalPropertyDescriptor;

/**
 * Describes a decimal property. Property value is either stored as a
 * <code>Double</code> or as a <code>BigDecimal</code> depending on the
 * <code>usingBigDecimal</code> property.
 * 
 * @version $LastChangedRevision$
 * @author Vincent Vandenschrick
 */
public class BasicDecimalPropertyDescriptor extends
    BasicNumberPropertyDescriptor implements IDecimalPropertyDescriptor {

  private Integer maxFractionDigit;
  private Boolean usingBigDecimal;

  /**
   * {@inheritDoc}
   */
  @Override
  public BasicDecimalPropertyDescriptor clone() {
    BasicDecimalPropertyDescriptor clonedDescriptor = (BasicDecimalPropertyDescriptor) super
        .clone();

    return clonedDescriptor;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Integer getMaxFractionDigit() {
    return maxFractionDigit;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Class<?> getModelType() {
    if (isUsingBigDecimal()) {
      return BigDecimal.class;
    }
    return Double.class;
  }

  /**
   * Returns false by default.
   * <p>
   * {@inheritDoc}
   */
  @Override
  public boolean isUsingBigDecimal() {
    if (usingBigDecimal != null) {
      return usingBigDecimal.booleanValue();
    }
    return false;
  }

  /**
   * Configures the precision of the decimal property. Default value is
   * <code>null</code> which means unlimited.
   * 
   * @param maxFractionDigit
   *          the maxFractionDigit to set.
   */
  public void setMaxFractionDigit(Integer maxFractionDigit) {
    this.maxFractionDigit = maxFractionDigit;
  }

  /**
   * Configures the property to be managed using
   * <code>java.math.BigDecimal</code>. Default value is <code>false</code>
   * which means <code>java.lang.Double</code> will be used.
   * 
   * @param usingBigDecimal
   *          the usingBigDecimal to set.
   */
  public void setUsingBigDecimal(boolean usingBigDecimal) {
    this.usingBigDecimal = Boolean.valueOf(usingBigDecimal);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public BigDecimal getMinValue() {
    BigDecimal min = super.getMinValue();
    if (min == null) {
      if (!isUsingBigDecimal()) {
        min = new BigDecimal(-Double.MAX_VALUE);
      }
    }
    return min;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public BigDecimal getMaxValue() {
    BigDecimal max = super.getMaxValue();
    if (max == null) {
      if (!isUsingBigDecimal()) {
        max = new BigDecimal(Double.MAX_VALUE);
      }
    }
    return max;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean isDefault(BigDecimal boundValue) {
    if (boundValue != null) {
      if (!isUsingBigDecimal()) {
        return boundValue.doubleValue() == Double.MAX_VALUE
            || boundValue.doubleValue() == -Double.MAX_VALUE;
      }
    }
    return super.isDefault(boundValue);
  }

}
