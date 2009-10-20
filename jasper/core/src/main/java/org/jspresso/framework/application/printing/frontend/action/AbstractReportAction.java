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
package org.jspresso.framework.application.printing.frontend.action;

import java.util.HashMap;
import java.util.Map;

import org.jspresso.framework.action.IActionHandler;
import org.jspresso.framework.application.frontend.action.AbstractChainedAction;
import org.jspresso.framework.application.printing.model.IReport;
import org.jspresso.framework.application.printing.model.IReportFactory;

/**
 * Abstract base class of report actions.
 * <p>
 * Copyright (c) 2005-2009 Vincent Vandenschrick. All rights reserved.
 * <p>
 * This file is part of the Jspresso framework. Jspresso is free software: you
 * can redistribute it and/or modify it under the terms of the GNU Lesser
 * General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version. Jspresso is
 * distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details. You should have received a copy of the GNU Lesser General Public
 * License along with Jspresso. If not, see <http://www.gnu.org/licenses/>.
 * <p>
 * 
 * @version $LastChangedRevision: 1332 $
 * @author Vincent Vandenschrick
 * @param <E>
 *          the actual gui component type used.
 * @param <F>
 *          the actual icon type used.
 * @param <G>
 *          the actual action type used.
 */
public abstract class AbstractReportAction<E, F, G> extends
    AbstractChainedAction<E, F, G> {

  private IReportFactory reportFactory;

  /**
   * Sets the reportFactory.
   * 
   * @param reportFactory
   *          the reportFactory to set.
   */
  public void setReportFactory(IReportFactory reportFactory) {
    this.reportFactory = reportFactory;
  }

  /**
   * Gets the reportFactory.
   * 
   * @return the reportFactory.
   */
  protected IReportFactory getReportFactory() {
    return reportFactory;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean execute(IActionHandler actionHandler,
      Map<String, Object> context) {
    IReport report = getReportToExecute(actionHandler, context);
    if (report == null) {
      return false;
    }
    report.getContext().putAll(getInitialReportContext(actionHandler, context));
    context.put(IReport.REPORT_ACTION_PARAM, report);
    return super.execute(actionHandler, context);
  }

  /**
   * Construct a contextual initial report context.
   * 
   * @param actionHandler
   *          the action handler.
   * @param context
   *          the action context.
   * @return the initial report context.
   */
  protected Map<String, Object> getInitialReportContext(
      IActionHandler actionHandler, Map<String, Object> context) {
    return new HashMap<String, Object>();
  }

  /**
   * Returns the report to execute out of the action context.
   * 
   * @param actionHandler
   *          the action handler.
   * @param context
   *          the action context.
   * @return the report to execute.
   */
  protected abstract IReport getReportToExecute(IActionHandler actionHandler,
      Map<String, Object> context);
}
