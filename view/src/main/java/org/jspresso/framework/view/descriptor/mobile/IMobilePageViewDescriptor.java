/*
 * Copyright (c) 2005-2014 Vincent Vandenschrick. All rights reserved.
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
package org.jspresso.framework.view.descriptor.mobile;

import org.jspresso.framework.view.action.IDisplayableAction;

/**
 * Marker interface for mobile page view descriptors.
 *
 * @author Vincent Vandenschrick
 * @version $LastChangedRevision$
 */
public interface IMobilePageViewDescriptor extends IMobilePageSectionViewDescriptor {

  /**
   * Gets back action. The back action will be triggered when the user requests back navigation.
   *
   * @return the back action
   */
  IDisplayableAction getBackAction();

  /**
   * Gets main action. The main action will be displayed at the up right corner of the page.
   *
   * @return the main action
   */
  IDisplayableAction getMainAction();
}
