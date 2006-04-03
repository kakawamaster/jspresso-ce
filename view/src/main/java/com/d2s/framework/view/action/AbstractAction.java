/*
 * Copyright (c) 2005 Design2see. All rights reserved.
 */
package com.d2s.framework.view.action;

import java.util.Locale;
import java.util.Map;

/**
 * Base class for all application actions. Takes care of the context reference
 * as well as the input context keys reference.
 * <p>
 * Copyright 2005 Design2See. All rights reserved.
 * <p>
 * 
 * @version $LastChangedRevision$
 * @author Vincent Vandenschrick
 */
public abstract class AbstractAction implements IAction {

  private boolean longOperation;

  /**
   * Retrieves the locale the action has to use to execute from its context
   * using a well-known key.
   * 
   * @param context
   *          the action context.
   * @return the locale the action executes in.
   */
  public Locale getLocale(Map<String, Object> context) {
    return (Locale) context.get(ActionContextConstants.LOCALE);
  }

  /**
   * Gets the parent module selected indices from the context. it uses the
   * <code>ActionContextConstants.PARENT_MODULE_SELECTED_INDICES</code> key.
   * 
   * @param context
   *          the action context.
   * @return the selected indices if any.
   */
  public int[] getParentModuleSelectedIndices(Map<String, Object> context) {
    return (int[]) context
        .get(ActionContextConstants.PARENT_MODULE_SELECTED_INDICES);
  }

  /**
   * {@inheritDoc}
   */
  public boolean isLongOperation() {
    return longOperation;
  }

  /**
   * Sets the longOperation.
   * 
   * @param longOperation
   *          the longOperation to set.
   */
  public void setLongOperation(boolean longOperation) {
    this.longOperation = longOperation;
  }
}
