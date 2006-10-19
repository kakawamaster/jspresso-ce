/*
 * Copyright (c) 2005 Design2see. All rights reserved.
 */
package com.d2s.framework.application.backend.action.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.d2s.framework.action.ActionBusinessException;
import com.d2s.framework.action.ActionContextConstants;
import com.d2s.framework.action.IActionHandler;
import com.d2s.framework.application.backend.action.AbstractBackendAction;
import com.d2s.framework.model.descriptor.IComponentDescriptor;
import com.d2s.framework.model.descriptor.IPropertyDescriptor;
import com.d2s.framework.model.descriptor.basic.BasicComponentDescriptor;
import com.d2s.framework.model.descriptor.basic.BasicPasswordPropertyDescriptor;
import com.d2s.framework.security.UserPrincipal;

/**
 * Changes a user password asking for the current and new password.
 * <p>
 * Copyright 2005 Design2See. All rights reserved.
 * <p>
 * 
 * @version $LastChangedRevision$
 * @author Vincent Vandenschrick
 */
public abstract class AbstractChangePasswordAction extends
    AbstractBackendAction {

  /**
   * <code>PASSWD_CURRENT</code>.
   */
  public static final String               PASSWD_CURRENT           = "password.current";
  /**
   * <code>PASSWD_TYPED</code>.
   */
  public static final String               PASSWD_TYPED             = "password.typed";
  /**
   * <code>PASSWD_RETYPED</code>.
   */
  public static final String               PASSWD_RETYPED           = "password.retyped";

  /**
   * <code>PASSWD_CHANGE_DESCRIPTOR</code> is a unique reference to the model
   * descriptor of the change password action.
   */
  public static final IComponentDescriptor PASSWD_CHANGE_DESCRIPTOR = createPasswordChangeModel();

  private static IComponentDescriptor createPasswordChangeModel() {
    BasicComponentDescriptor passwordChangeModel = new BasicComponentDescriptor(
        Map.class.getName());
    BasicPasswordPropertyDescriptor currentPassword = new BasicPasswordPropertyDescriptor();
    currentPassword.setName(PASSWD_CURRENT);
    BasicPasswordPropertyDescriptor typedPassword = new BasicPasswordPropertyDescriptor();
    typedPassword.setName(PASSWD_TYPED);
    BasicPasswordPropertyDescriptor retypedPassword = new BasicPasswordPropertyDescriptor();
    retypedPassword.setName(PASSWD_RETYPED);

    List<IPropertyDescriptor> propertyDescriptors = new ArrayList<IPropertyDescriptor>();
    propertyDescriptors.add(currentPassword);
    propertyDescriptors.add(typedPassword);
    propertyDescriptors.add(retypedPassword);
    passwordChangeModel.setPropertyDescriptors(propertyDescriptors);

    return passwordChangeModel;
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  public boolean execute(@SuppressWarnings("unused")
  IActionHandler actionHandler, Map<String, Object> context) {
    Map<String, Object> actionParam = (Map<String, Object>) context
        .get(ActionContextConstants.ACTION_PARAM);
    char[] typedPasswd = (char[]) actionParam.get(PASSWD_TYPED);
    char[] retypedPasswd = (char[]) actionParam.get(PASSWD_RETYPED);
    if (!Arrays.equals(typedPasswd, retypedPasswd)) {
      throw new ActionBusinessException(
          "Typed and retyped passwords are different.",
          "password.typed.retyped.different");
    }
    UserPrincipal principal = getApplicationSession(context).getPrincipal();
    return changePassword(principal, (char[]) actionParam.get(PASSWD_CURRENT),
        typedPasswd);
  }

  /**
   * Performs the effective password change depending on the underlying storage.
   * 
   * @param userPrincipal
   *          the connected user principal.
   * @param currentPassword
   *          the current password.
   * @param newPassword
   *          the new password.
   * @return true if password was changed succesfully.
   */
  protected abstract boolean changePassword(UserPrincipal userPrincipal,
      char[] currentPassword, char[] newPassword);
}
