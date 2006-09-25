/*
 * Copyright (c) 2005 Design2see. All rights reserved.
 */
package com.d2s.framework.model.descriptor.basic;

import java.util.Map;

import com.d2s.framework.model.descriptor.IComponentDescriptor;
import com.d2s.framework.model.descriptor.IReferencePropertyDescriptor;

/**
 * Default implementation of a reference descriptor.
 * <p>
 * Copyright 2005 Design2See. All rights reserved.
 * <p>
 * 
 * @version $LastChangedRevision$
 * @author Vincent Vandenschrick
 */
public class BasicReferencePropertyDescriptor extends
    BasicRelationshipEndPropertyDescriptor implements
    IReferencePropertyDescriptor {

  private IComponentDescriptor referencedDescriptor;
  private Map<String, String>  initializationMapping;

  /**
   * {@inheritDoc}
   */
  public IComponentDescriptor getReferencedDescriptor() {
    if (referencedDescriptor != null) {
      return referencedDescriptor;
    }
    if (getParentDescriptor() != null) {
      return ((IReferencePropertyDescriptor) getParentDescriptor())
          .getReferencedDescriptor();
    }
    return referencedDescriptor;
  }

  /**
   * Sets the referencedDescriptor property.
   * 
   * @param referencedDescriptor
   *          the referencedDescriptor to set.
   */
  public void setReferencedDescriptor(IComponentDescriptor referencedDescriptor) {
    this.referencedDescriptor = referencedDescriptor;
  }

  /**
   * {@inheritDoc}
   */
  public Class getModelType() {
    return getReferencedDescriptor().getComponentContract();
  }

  /**
   * {@inheritDoc}
   */
  public IComponentDescriptor getComponentDescriptor() {
    return getReferencedDescriptor();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isQueryable() {
    return true;
  }

  /**
   * Gets the initializationMapping.
   * 
   * @return the initializationMapping.
   */
  public Map<String, String> getInitializationMapping() {
    if (initializationMapping != null) {
      return initializationMapping;
    }
    if (getParentDescriptor() != null) {
      return ((IReferencePropertyDescriptor) getParentDescriptor())
          .getInitializationMapping();
    }
    return initializationMapping;
  }

  /**
   * Sets the initializationMapping.
   * 
   * @param initializationMapping
   *          the initializationMapping to set.
   */
  public void setInitializationMapping(Map<String, String> initializationMapping) {
    this.initializationMapping = initializationMapping;
  }
}
