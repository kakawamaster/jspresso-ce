/*
 * Copyright (c) 2005 Design2see. All rights reserved.
 */
package com.d2s.framework.application.frontend.action.ulc.std;

import com.d2s.framework.application.frontend.action.std.OkAction;
import com.d2s.framework.application.frontend.action.ulc.IDialogAwareAction;
import com.ulcjava.base.application.IAction;
import com.ulcjava.base.application.ULCComponent;
import com.ulcjava.base.application.ULCDialog;
import com.ulcjava.base.application.util.ULCIcon;

/**
 * A standard ok action. Since it is a chained action, it can be chained with
 * another action.
 * <p>
 * Copyright 2005 Design2See. All rights reserved.
 * <p>
 * 
 * @version $LastChangedRevision$
 * @author Vincent Vandenschrick
 */
public class DialogOkAction extends OkAction<ULCComponent, ULCIcon, IAction> implements IDialogAwareAction {

  /**
   * {@inheritDoc}
   */
  public void postActionExecution(ULCDialog dialog) {
    dialog.setVisible(false);
  }

}
