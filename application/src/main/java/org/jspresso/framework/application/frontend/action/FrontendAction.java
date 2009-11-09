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
package org.jspresso.framework.application.frontend.action;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import org.jspresso.framework.action.ActionContextConstants;
import org.jspresso.framework.application.action.AbstractAction;
import org.jspresso.framework.application.frontend.IFrontendController;
import org.jspresso.framework.binding.ICollectionConnector;
import org.jspresso.framework.binding.ICollectionConnectorProvider;
import org.jspresso.framework.binding.ICompositeValueConnector;
import org.jspresso.framework.binding.IMvcBinder;
import org.jspresso.framework.binding.IValueConnector;
import org.jspresso.framework.util.descriptor.DefaultIconDescriptor;
import org.jspresso.framework.util.gate.IGate;
import org.jspresso.framework.util.i18n.ITranslationProvider;
import org.jspresso.framework.view.IActionFactory;
import org.jspresso.framework.view.IIconFactory;
import org.jspresso.framework.view.IViewFactory;
import org.jspresso.framework.view.action.IDisplayableAction;

/**
 * Base class for frontend actions.
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
public class FrontendAction<E, F, G> extends AbstractAction implements
    IDisplayableAction {

  private String                acceleratorAsString;
  private Collection<IGate>     actionabilityGates;
  private DefaultIconDescriptor actionDescriptor;
  private String                mnemonicAsString;

  /**
   * Constructs a new <code>AbstractFrontendAction</code> instance.
   */
  public FrontendAction() {
    actionDescriptor = new DefaultIconDescriptor();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final IDisplayableAction other = (IDisplayableAction) obj;
    if (getName() == null) {
      return false;
    } else if (!getName().equals(other.getName())) {
      return false;
    }
    return true;
  }

  /**
   * {@inheritDoc}
   */
  public String getAcceleratorAsString() {
    return acceleratorAsString;
  }

  /**
   * Gets the actionabilityGates.
   * 
   * @return the actionabilityGates.
   */
  public Collection<IGate> getActionabilityGates() {
    return actionabilityGates;
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
  public String getI18nDescription(ITranslationProvider translationProvider,
      Locale locale) {
    if (getDescription() != null) {
      return translationProvider.getTranslation(getDescription(), locale);
    }
    return getI18nName(translationProvider, locale);
  }

  /**
   * {@inheritDoc}
   */
  public String getI18nName(ITranslationProvider translationProvider,
      Locale locale) {
    return translationProvider.getTranslation(getName(), locale);
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
  public String getMnemonicAsString() {
    return mnemonicAsString;
  }

  /**
   * {@inheritDoc}
   */
  public String getName() {
    return actionDescriptor.getName();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result;
    if (getName() != null) {
      result += getName().hashCode();
    }
    return result;
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
   * Sets the acceleratorAsString.
   * 
   * @param acceleratorAsString
   *          the acceleratorAsString to set.
   */
  public void setAcceleratorAsString(String acceleratorAsString) {
    this.acceleratorAsString = acceleratorAsString;
  }

  /**
   * Sets the actionabilityGates.
   * 
   * @param actionabilityGates
   *          the actionabilityGates to set.
   */
  public void setActionabilityGates(Collection<IGate> actionabilityGates) {
    this.actionabilityGates = actionabilityGates;
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
   * Sets the name.
   * 
   * @param name
   *          the name to set.
   */
  public void setName(String name) {
    actionDescriptor.setName(name);
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

  /**
   * Retrieves the widget which triggered the action from the action context.
   * 
   * @param context
   *          the action context.
   * @return the widget which triggered the action.
   */
  @SuppressWarnings("unchecked")
  protected E getActionWidget(Map<String, Object> context) {
    return (E) context.get(ActionContextConstants.ACTION_WIDGET);
  }

  /**
   * Gets the frontend controller out of the action context.
   * 
   * @param context
   *          the action context.
   * @return the frontend controller.
   */
  @Override
  @SuppressWarnings("unchecked")
  protected IFrontendController<E, F, G> getController(
      Map<String, Object> context) {
    return (IFrontendController<E, F, G>) getFrontendController(context);
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
  protected ICompositeValueConnector getModuleConnector(
      Map<String, Object> context) {
    return (ICompositeValueConnector) context
        .get(ActionContextConstants.MODULE_VIEW_CONNECTOR);
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
   * Retrieves the widget this action was triggered from. It may serve to
   * determine the root window or dialog for instance. It uses a well-known
   * action context key which is : <li>
   * <code>ActionContextConstants.SOURCE_COMPONENT</code>.
   * 
   * @param context
   *          the action context.
   * @return the source widget this action was triggered from.
   */
  @SuppressWarnings("unchecked")
  protected E getSourceComponent(Map<String, Object> context) {
    return (E) context.get(ActionContextConstants.SOURCE_COMPONENT);
  }

  /**
   * This is a utility method which is able to retrieve the view connector this
   * action has been executed on from its context. It uses well-known context
   * keys of the action context which are:
   * <ul>
   * <li> <code>ActionContextConstants.VIEW_CONNECTOR</code> to get the the view
   * value connector the action executes on.
   * </ul>
   * <p>
   * The returned connector mainly serves for acting on the view component the
   * action has to be triggered on.
   * 
   * @param context
   *          the action context.
   * @return the value connector this model action was triggered on.
   */
  protected IValueConnector getViewConnector(Map<String, Object> context) {
    return (IValueConnector) context.get(ActionContextConstants.VIEW_CONNECTOR);
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
   * Returns the view connector value. If the connector this action is assigned
   * to is a collection connector, the returned model is the 1st selected
   * element or null if none is selected.
   * 
   * @param context
   *          the action context.
   * @return the view connector value.
   */
  protected Object getModel(Map<String, Object> context) {
    IValueConnector viewConnector = getViewConnector(context);
    Object model;
    if (viewConnector instanceof ICollectionConnectorProvider) {
      int[] selectedIndices = ((ICollectionConnectorProvider) viewConnector)
          .getCollectionConnector().getSelectedIndices();
      ICollectionConnector collectionConnector = (ICollectionConnector) ((ICollectionConnectorProvider) viewConnector)
          .getCollectionConnector().getModelConnector();
      if (selectedIndices == null || selectedIndices.length == 0
          || collectionConnector == null) {
        return null;
      }
      model = collectionConnector.getChildConnector(selectedIndices[0])
          .getConnectorValue();
    } else {
      model = viewConnector.getModelConnector().getConnectorValue();
    }
    return model;
  }
}
