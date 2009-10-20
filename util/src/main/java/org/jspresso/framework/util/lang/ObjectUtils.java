package org.jspresso.framework.util.lang;

import java.sql.Timestamp;

/**
 * Helper class for objects operations.
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
public final class ObjectUtils {

  private ObjectUtils() {
    // Helper class constructor
  }

  /**
   * Compares two objects for equality.
   * 
   * @param object1
   *            the first object, may be <code>null</code>
   * @param object2
   *            the second object, may be <code>null</code>
   * @return <code>true</code> if the values of both objects are the same
   */
  public static boolean equals(Object object1, Object object2) {
    if (object1 == object2) {
      return true;
    }
    if ((Boolean.FALSE.equals(object1) && object2 == null)
        || (Boolean.FALSE.equals(object2) && object1 == null)) {
      // Special handling for null == Boolean.FALSE on models.
      return true;
    }
    if ((object1 == null) || (object2 == null)) {
      return false;
    }
    if (object1 instanceof Number && object2 instanceof Number) {
      return ((Number) object1).doubleValue() == ((Number) object2)
          .doubleValue();
    }
    if (object1 instanceof Timestamp) {
      // there is a bug where a date equals a timestamp but not reflexively !
      return object2.equals(object1);
    }
    return object1.equals(object2);
  }
}
