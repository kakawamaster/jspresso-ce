/*
 * Copyright (c) 2005 Design2see. All rights reserved.
 */
package com.d2s.framework.application.frontend.action;

import java.util.Map;

import com.d2s.framework.application.frontend.IFrontendController;
import com.d2s.framework.binding.ICompositeValueConnector;
import com.d2s.framework.binding.IMvcBinder;
import com.d2s.framework.binding.IValueConnector;
import com.d2s.framework.util.descriptor.DefaultIconDescriptor;
import com.d2s.framework.view.IActionFactory;
import com.d2s.framework.view.IIconFactory;
import com.d2s.framework.view.IViewFactory;
import com.d2s.framework.view.action.AbstractAction;
import com.d2s.framework.view.action.ActionContextConstants;
import com.d2s.framework.view.action.IDisplayableAction;

/**
 * Base class for frontend actions.
 * <p>
 * Copyright 2005 Design2See. All rights reserved.
 * <p>
 * 
 * @version $LastChangedRevision$
 * @author Vincent Vandenschrick
 * @param <E>
 *          the actual gui component type used.
 * @param <F>
 *          the actual icon type used.
 * @param <G>
 *          the actual action type used.
 */
public abstract class AbstractFrontendAction<E, F, G> extends AbstractAction
    implements IDisplayableAction {

  private String                mnemonicAsString;
  private String                acceleratorAsString;
  private DefaultIconDescriptor actionDescriptor;

  /**
   * Constructs a new <code>AbstractFrontendAction</code> instance.
   */
  public AbstractFrontendAction() {
    actionDescriptor = new DefaultIconDescriptor();
  }

  /**
   * {@inheritDoc}
   */
  public String getMnemonicAsString() {
    return mnemonicAsString;
  }

  /**
   * Sets the mnemonic of the action.
   * 
   * @param mnemonicStringRep
   *          the mnemonic to set represented as a string as KeyStroke factory
   *          would parse it.
   */
  public void setMnemonicAsString(String mnemonicStringRep) {
    this.mnemonicAsString = mnemonicStringRep;
  }

  /**
   * {@inheritDoc}
   */
  public String getDescription() {
    return actionDescriptor.getDescription();
  }

  /**
   * {@inheritDoc}
   */
  public String getIconImageURL() {
    return actionDescriptor.getIconImageURL();
  }

  /**
   * {@inheritDoc}
   */
  public String getName() {
    return actionDescriptor.getName();
  }

  /**
   * Sets the description.
   * 
   * @param description
   *          the description to set.
   */
  public void setDescription(String description) {
    actionDescriptor.setDescription(description);
  }

  /**
   * Sets the iconImageURL.
   * 
   * @param iconImageURL
   *          the iconImageURL to set.
   */
  public void setIconImageURL(String iconImageURL) {
    actionDescriptor.setIconImageURL(iconImageURL);
  }

  /**
   * Sets the name.
   * 
   * @param name
   *          the name to set.
   */
  public void setName(String name) {
    actionDescriptor.setName(name);
  }

  /**
   * This is a utility method which is able to retrieve the view connector this
   * action has been executed on from its context. It uses well-known context
   * keys of the action context which are:
   * <ul>
   * <li> <code>ActionContextConstants.VIEW_CONNECTOR</code> to get the the
   * view value connector the action executes on.
   * </ul>
   * <p>
   * The returned connector mainly serves for acting on the view component the
   * action has to be triggered on.
   * 
   * @param context
   *          the action context.
   * @return the value connector this model action was triggered on.
   */
  public IValueConnector getViewConnector(Map<String, Object> context) {
    return (IValueConnector) context.get(ActionContextConstants.VIEW_CONNECTOR);
  }

  /**
   * This is a utility method which is able to retrieve the module view
   * connector this action has been executed on from its context. It uses
   * well-known context keys of the action context which are:
   * <ul>
   * <li> <code>ActionContextConstants.MODULE_VIEW_CONNECTOR</code> to get the
   * the module view connector the action executes on.
   * </ul>
   * <p>
   * The returned connector mainly serves for acting on the view component the
   * action has to be triggered on.
   * 
   * @param context
   *          the action context.
   * @return the value connector this model action was triggered on.
   */
  public ICompositeValueConnector getModuleConnector(Map<String, Object> context) {
    return (ICompositeValueConnector) context
        .get(ActionContextConstants.MODULE_VIEW_CONNECTOR);
  }

  /**
   * This is a utility method which is able to retrieve the parent module view
   * connector this action has been executed on from its context. It uses
   * well-known context keys of the action context which are:
   * <ul>
   * <li> <code>ActionContextConstants.PARENT_MODULE_VIEW_CONNECTOR</code> to
   * get the the parent module view connector the action executes on.
   * </ul>
   * <p>
   * The returned connector mainly serves for acting on the view component the
   * action has to be triggered on.
   * 
   * @param context
   *          the action context.
   * @return the parent value connector this model action was triggered on.
   */
  public ICompositeValueConnector getParentModuleConnector(
      Map<String, Object> context) {
    return (ICompositeValueConnector) context
        .get(ActionContextConstants.PARENT_MODULE_VIEW_CONNECTOR);
  }

  /**
   * {@inheritDoc}
   */
  public String getAcceleratorAsString() {
    return acceleratorAsString;
  }

  /**
   * Sets the acceleratorAsString.
   * 
   * @param acceleratorAsString
   *          the acceleratorAsString to set.
   */
  public void setAcceleratorAsString(String acceleratorAsString) {
    this.acceleratorAsString = acceleratorAsString;
  }

  /**
   * Returns false.
   * <p>
   * {@inheritDoc}
   */
  public boolean isBackend() {
    return false;
  }

  /**
   * Gets the frontend controller out of the action context.
   * 
   * @param context
   *          the action context.
   * @return the frontend controller.
   */
  @SuppressWarnings("unchecked")
  protected IFrontendController<E, F, G> getController(Map<String, Object> context) {
    return (IFrontendController<E, F, G>) context.get(ActionContextConstants.CONTROLLER);
  }

  /**
   * Gets the mvcBinder.
   * 
   * @param context
   *          the action context.
   * @return the mvcBinder.
   */
  protected IMvcBinder getMvcBinder(Map<String, Object> context) {
    return getController(context).getMvcBinder();
  }

  /**
   * Gets the viewFactory.
   * 
   * @param context
   *          the action context.
   * @return the viewFactory.
   */
  protected IViewFactory<E, F, G> getViewFactory(Map<String, Object> context) {
    return getController(context).getViewFactory();
  }

  /**
   * Gets the iconFactory.
   * 
   * @param context
   *          the action context.
   * @return the iconFactory.
   */
  protected IIconFactory<F> getIconFactory(Map<String, Object> context) {
    return getViewFactory(context).getIconFactory();
  }

  /**
   * Gets the actionFactory.
   * 
   * @param context
   *          the action context.
   * @return the actionFactory.
   */
  protected IActionFactory<G, E> getActionFactory(Map<String, Object> context) {
    return getViewFactory(context).getActionFactory();
  }
}
