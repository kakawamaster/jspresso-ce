/*
 * Copyright (c) 2005-2008 Vincent Vandenschrick. All rights reserved.
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
package org.jspresso.framework.application.frontend.action.swing.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import javax.swing.JFileChooser;

import org.jspresso.framework.action.IActionHandler;
import org.jspresso.framework.application.frontend.file.IFileOpenCallback;
import org.jspresso.framework.util.swing.SwingUtil;


/**
 * Initiates a file open action.
 * <p>
 * Copyright (c) 2005-2008 Vincent Vandenschrick. All rights reserved.
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
 * @version $LastChangedRevision$
 * @author Vincent Vandenschrick
 */
public class OpenFileAction extends ChooseFileAction {

  private IFileOpenCallback fileOpenCallback;

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean execute(IActionHandler actionHandler,
      Map<String, Object> context) {

    JFileChooser currentFileChooser = getFileChooser(context);

    int returnVal = currentFileChooser.showOpenDialog(SwingUtil
        .getVisibleWindow(getSourceComponent(context)));
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = currentFileChooser.getSelectedFile();
      if (file != null) {
        try {
          fileOpenCallback.fileChosen(new FileInputStream(file), file
              .getAbsolutePath(), actionHandler, context);
        } catch (FileNotFoundException ex) {
          fileOpenCallback.cancel(actionHandler, context);
        }
      } else {
        fileOpenCallback.cancel(actionHandler, context);
      }
    } else {
      fileOpenCallback.cancel(actionHandler, context);
    }
    return super.execute(actionHandler, context);
  }

  /**
   * Sets the fileOpenCallback.
   * 
   * @param fileOpenCallback
   *            the fileOpenCallback to set.
   */
  public void setFileOpenCallback(IFileOpenCallback fileOpenCallback) {
    this.fileOpenCallback = fileOpenCallback;
  }
}
