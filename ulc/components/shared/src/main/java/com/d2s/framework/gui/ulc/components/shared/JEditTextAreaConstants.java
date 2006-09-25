/*
 * Copyright (c) 2005 Design2see. All rights reserved.
 */
package com.d2s.framework.gui.ulc.components.shared;

/**
 * Constants shared by ULCJEditTextArea and UIJEditTextArea.
 * <p>
 * Copyright 2005 Design2See. All rights reserved.
 * <p>
 * 
 * @version $LastChangedRevision$
 * @author Vincent Vandenschrick
 */
public final class JEditTextAreaConstants {

  private JEditTextAreaConstants() {
    // Empty constructor for utility class
  }

  // request constants
  /**
   * <code>SET_TEXT_REQUEST</code>.
   */
  public static final String SET_TEXT_REQUEST     = "setText";

  /**
   * <code>SET_EDITABLE_REQUEST</code>.
   */
  public static final String SET_EDITABLE_REQUEST = "setEditable";

  // anything key constants
  /**
   * <code>VALUE_KEY</code>.
   */
  public static final String TEXT_KEY             = "text";

  /**
   * <code>LANGUAGE_KEY</code>.
   */
  public static final String LANGUAGE_KEY         = "language";

  /**
   * <code>EDITABLE_KEY</code>.
   */
  public static final String EDITABLE_KEY         = "editable";
}
