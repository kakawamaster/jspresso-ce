/*
 * Copyright (c) 2005-2013 Vincent Vandenschrick. All rights reserved.
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
package org.jspresso.framework.binding.remote;

import org.jspresso.framework.binding.basic.BasicValueConnector;
import org.jspresso.framework.state.remote.IRemoteStateOwner;
import org.jspresso.framework.state.remote.IRemoteStateValueMapper;
import org.jspresso.framework.state.remote.RemoteValueState;
import org.jspresso.framework.util.automation.IPermIdSource;
import org.jspresso.framework.util.remote.IRemotePeer;

/**
 * The server peer of a remote value connector.
 *
 * @author Vincent Vandenschrick
 */
public class RemoteValueConnector extends BasicValueConnector implements
    IRemotePeer, IRemoteStateOwner, IPermIdSource {

  private String                  permId;
  private final RemoteConnectorFactory  connectorFactory;
  private String                  guid;
  private IRemoteStateValueMapper remoteStateValueMapper;
  private RemoteValueState        state;

  /**
   * Constructs a new {@code RemoteValueConnector} instance.
   *
   * @param id
   *          the connector id.
   * @param connectorFactory
   *          the remote connector factory.
   */
  public RemoteValueConnector(String id, RemoteConnectorFactory connectorFactory) {
    super(id);
    this.guid = connectorFactory.generateGUID();
    this.connectorFactory = connectorFactory;
    connectorFactory.register(this);
  }

  /**
   * Returns the actual connector value.
   * <p>
   * {@inheritDoc}
   */
  @Override
  public Object actualValue() {
    return getConnectorValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public RemoteValueConnector clone() {
    return clone(getId());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public RemoteValueConnector clone(String newConnectorId) {
    RemoteValueConnector clonedConnector = (RemoteValueConnector) super
        .clone(newConnectorId);
    clonedConnector.guid = connectorFactory.generateGUID();
    clonedConnector.state = null;
    connectorFactory.attachListeners(clonedConnector);
    connectorFactory.register(clonedConnector);
    return clonedConnector;
  }

  /**
   * Gets the permId.
   *
   * @return the permId.
   */
  @Override
  public String getPermId() {
    if (permId != null) {
      return permId;
    }
    return getId();
  }

  /**
   * Gets the guid.
   *
   * @return the guid.
   */
  @Override
  public String getGuid() {
    return guid;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public RemoteValueState getState() {
    if (state == null) {
      state = connectorFactory.createRemoteValueState(getGuid(), getPermId());
    }
    state.setValue(getValueForState());
    state.setReadable(isReadable());
    state.setWritable(isWritable());
    return state;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isWritable() {
    return (getModelConnector() != null) && super.isWritable();
  }

  /**
   * Sets the permanent identifier to this application element. Permanent
   * identifiers are used by different framework parts, like dynamic security or
   * record/replay controllers to uniquely identify an application element.
   * Permanent identifiers are generated by the SJS build based on the element
   * id but must be explicitly set if Spring XML is used.
   *
   * @param permId
   *          the permId to set.
   */
  @Override
  public void setPermId(String permId) {
    this.permId = permId;
  }

  /**
   * Sets the remoteStateValueMapper.
   *
   * @param remoteStateValueMapper
   *          the remoteStateValueMapper to set.
   */
  public void setRemoteStateValueMapper(
      IRemoteStateValueMapper remoteStateValueMapper) {
    this.remoteStateValueMapper = remoteStateValueMapper;
  }

  /**
   * Gets the remoteStateValueMapper.
   *
   * @return the remoteStateValueMapper.
   */
  protected IRemoteStateValueMapper getRemoteStateValueMapper() {
    return remoteStateValueMapper;
  }

  /**
   * Gets the value that has to be set to the remote state when updating it. It
   * defaults to the connector value but the developer is given a chance here
   * to mutate the actual object returned. This allows for changing the type of
   * objects actually exchanged with the remote frontend peer.
   *
   * @return the value that has to be set to the remote state when updating it.
   */
  protected Object getValueForState() {
    Object valueForState = getConnectorValue();
    if (getRemoteStateValueMapper() != null) {
      valueForState = getRemoteStateValueMapper().getValueForState(state, valueForState);
    }
    return valueForState;
  }

  /**
   * Sets the value that has to be set from the remote state when updating it.
   * It defaults to the incoming value but the developer is given a chance here
   * to mutate the actual object that comes in. This allows for changing the
   * type of objects actually exchanged with the remote frontend peer.
   *
   * @param stateValue
   *          the incoming value.
   */

  @Override
  public void setValueFromState(Object stateValue) {
    Object valueFromState;
    if (getRemoteStateValueMapper() != null) {
      valueFromState = getRemoteStateValueMapper()
          .getValueFromState(state, stateValue);
    } else {
      valueFromState = stateValue;
    }
    setConnectorValue(valueFromState);
  }
}
