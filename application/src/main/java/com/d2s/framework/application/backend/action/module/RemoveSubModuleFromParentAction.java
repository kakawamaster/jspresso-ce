/*
 * Copyright (c) 2005 Design2see. All rights reserved.
 */
package com.d2s.framework.application.backend.action.module;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.d2s.framework.application.backend.action.AbstractCollectionAction;
import com.d2s.framework.binding.ConnectorHelper;
import com.d2s.framework.binding.ICollectionConnectorProvider;
import com.d2s.framework.binding.ICompositeValueConnector;
import com.d2s.framework.view.action.ActionContextConstants;
import com.d2s.framework.view.action.IActionHandler;
import com.d2s.framework.view.module.Module;
import com.d2s.framework.view.module.SubModule;

/**
 * This action removes the selected modules from their parent.
 * <p>
 * Copyright 2005 Design2See. All rights reserved.
 * <p>
 * 
 * @version $LastChangedRevision$
 * @author Vincent Vandenschrick
 */
public class RemoveSubModuleFromParentAction extends AbstractCollectionAction {

  /**
   * Removes the selected modules from their parent.
   * <p>
   * {@inheritDoc}
   */
  public void execute(@SuppressWarnings("unused")
  IActionHandler actionHandler, Map<String, Object> context) {
    ICompositeValueConnector parentModuleConnector = (ICompositeValueConnector) getModuleConnector(
        context).getParentConnector().getParentConnector();

    int[] selectedIndices = getParentModuleSelectedIndices(context);

    if (selectedIndices == null || selectedIndices.length == 0) {
      return;
    }
    Module parentModule = (Module) parentModuleConnector.getConnectorValue();
    Collection<SubModule> childrenToRemove = new ArrayList<SubModule>();

    for (int i = 0; i < selectedIndices.length; i++) {
      childrenToRemove.add(parentModule.getSubModules().get(i));
    }
    parentModule.removeSubModules(childrenToRemove);
    Map<String, Object> executionResult = new HashMap<String, Object>();
    executionResult.put(ActionContextConstants.SELECTED_INDICES,
        ConnectorHelper.getIndicesOf(
            ((ICollectionConnectorProvider) parentModuleConnector
                .getParentConnector()).getCollectionConnector(), Collections
                .singleton(parentModuleConnector.getConnectorValue())));
  }
}
