package org.jspresso.framework.util.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * A file resource.
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
public class FileResource extends AbstractResource {

  private File resourceFile;

  /**
   * Constructs a new <code>FileResource</code> instance.
   * 
   * @param mimeType
   *            the resource mime type.
   * @param resourceFile
   *            the resource content.
   */
  public FileResource(String mimeType, File resourceFile) {
    super(mimeType);
    this.resourceFile = resourceFile;
  }

  /**
   * {@inheritDoc}
   */
  public InputStream getContent() throws IOException {
    return new FileInputStream(resourceFile);
  }

  /**
   * {@inheritDoc}
   */
  public int getLength() {
    return (int) resourceFile.length();
  }
}
