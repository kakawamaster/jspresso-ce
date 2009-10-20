package org.jspresso.framework.util.resources;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.jspresso.framework.util.url.UrlHelper;


/**
 * An URL resource.
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
 * @version $LastChangedRevision$
 * @author Vincent Vandenschrick
 */
public class UrlResource extends AbstractResource {

  private URL url;

  /**
   * Constructs a new <code>UrlResource</code> instance.
   * 
   * @param mimeType
   *            the resource mime type.
   * @param urlSpec
   *            the url spec.
   */
  public UrlResource(String mimeType, String urlSpec) {
    super(mimeType);
    url = UrlHelper.createURL(urlSpec);
  }

  /**
   * {@inheritDoc}
   */
  public InputStream getContent() throws IOException {
    return url.openStream();
  }

  /**
   * {@inheritDoc}
   */
  public long getSize() {
    return -1; // unknown.
  }
}
