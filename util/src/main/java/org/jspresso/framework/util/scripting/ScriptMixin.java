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
package org.jspresso.framework.util.scripting;

/**
 * Utility class helping to bring scripting capability in an object.
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
public class ScriptMixin implements IScript {

  private String language;
  private String script;
  private Object scriptedObject;

  /**
   * Constructs a new <code>ScriptMixin</code> instance.
   * 
   * @param scriptedObject
   *            the object this mixin is introduced in.
   */
  public ScriptMixin(Object scriptedObject) {
    this.scriptedObject = scriptedObject;
  }

  /**
   * Gets the language.
   * 
   * @return the language.
   */
  public String getLanguage() {
    return language;
  }

  /**
   * Gets the script.
   * 
   * @return the script.
   */
  public String getScript() {
    return script;
  }

  /**
   * Gets the scriptedObject.
   * 
   * @return the scriptedObject.
   */
  public Object getScriptedObject() {
    return scriptedObject;
  }

  /**
   * Sets the language.
   * 
   * @param language
   *            the language to set.
   */
  public void setLanguage(String language) {
    this.language = language;
  }

  /**
   * Sets the script.
   * 
   * @param script
   *            the script to set.
   */
  public void setScript(String script) {
    this.script = script;
  }
}
