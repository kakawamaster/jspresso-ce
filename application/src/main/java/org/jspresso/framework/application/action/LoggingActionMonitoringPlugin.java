/*
 * Copyright (c) 2005-2017 Vincent Vandenschrick. All rights reserved.
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
package org.jspresso.framework.application.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.jspresso.framework.action.IAction;
import org.jspresso.framework.application.backend.session.IApplicationSession;
import org.jspresso.framework.application.model.Module;
import org.jspresso.framework.application.model.Workspace;
import org.jspresso.framework.security.UserPrincipal;

/**
 * A simple action monitoring plugin that logs the actions executions.
 *
 * @author Vincent Vandenschrick
 */
public class LoggingActionMonitoringPlugin extends AbstractActionMonitoringPlugin {

  private static final Logger LOG = LoggerFactory.getLogger(LoggingActionMonitoringPlugin.class);

  @Override
  protected void traceResults(IApplicationSession session, UserPrincipal user, Workspace workspace, Module module,
                              List<IAction> callStack, Map<String, Object> context, Date startTimestamp,
                              Date endTimestamp) {
    if (isEnabled()) {
      String workspaceName = "";
      if (workspace != null) {
        workspaceName = workspace.getName();
      }
      String moduleName = "";
      if (module != null) {
        moduleName = module.getName();
        if (moduleName == null) {
          moduleName = module.getI18nName();
        }
      }
      String sessionId = "";
      if (session != null) {
        sessionId = session.getId();
      }
      String userName = "";
      if (user != null) {
        userName = user.getName();
      }
      StringBuilder callStackNames = new StringBuilder();
      for (IAction action : callStack) {
        Class<? extends IAction> actionClass = action.getClass();
        String actionClassName = actionClass.getSimpleName();
        if (actionClassName == null || actionClassName.isEmpty()) {
          actionClassName = actionClass.getName();
          int lastDotIndex = actionClassName.lastIndexOf(".");
          if (lastDotIndex >= 0) {
            actionClassName = actionClassName.substring(lastDotIndex + 1);
          }
        }
        callStackNames.append(actionClassName).append("|");
      }
      callStackNames.delete(callStackNames.length() - 1, callStackNames.length());
      SimpleDateFormat tsFormat = new SimpleDateFormat("HH:mm:ss.SSS");
      String startTs = tsFormat.format(startTimestamp);
      String endTs = tsFormat.format(endTimestamp);
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      String date = dateFormat.format(startTimestamp);
      long duration = endTimestamp.getTime() - startTimestamp.getTime();
      LOG.trace("[{}][{}][{}][{}][{}][{}][{}][{}][{} ms.]", sessionId, userName, date, startTs, endTs, workspaceName,
          moduleName, callStackNames, duration);
    }
  }

  @Override
  protected boolean isEnabled() {
    return LOG.isTraceEnabled();
  }
}
