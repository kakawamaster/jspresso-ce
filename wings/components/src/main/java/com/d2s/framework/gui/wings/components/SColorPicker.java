/*
 * Copyright (c) 2005 Design2see. All rights reserved.
 */
package com.d2s.framework.gui.wings.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

import org.wings.SBorderLayout;
import org.wings.SBoxLayout;
import org.wings.SButton;
import org.wings.SDialog;
import org.wings.SPanel;
import org.wingx.XColorPicker;

/**
 * A wings component used to choose or reset to null a color.
 * <p>
 * Copyright 2005 Design2See. All rights reserved.
 * <p>
 *
 * @version $LastChangedRevision$
 * @author Vincent Vandenschrick
 */
public class SColorPicker extends SPanel {

  private static final long     serialVersionUID = -9175630518246041139L;

  private Color                 value;
  private Color                 resetValue;
  private SButton               chooseButton;
  private SButton               resetButton;

  private transient ChangeEvent changeEvent      = null;
  private EventListenerList     listenerList     = new EventListenerList();

  /**
   * Constructs a new <code>SColorPicker</code> instance.
   */
  public SColorPicker() {
    setLayout(new SBoxLayout(SBoxLayout.X_AXIS));
    chooseButton = new SButton(" ");
    chooseButton.addActionListener(new ActionListener() {

      public void actionPerformed(@SuppressWarnings("unused")
      ActionEvent e) {
        showColorPickerDialog();
      }
    });

    resetButton = new SButton("X");
    resetButton.addActionListener(new ActionListener() {

      public void actionPerformed(@SuppressWarnings("unused")
      ActionEvent e) {
        setValue(resetValue);
      }
    });

    add(chooseButton);
    add(resetButton);
  }

  /**
   * Adds a <code>ChangeListener</code> to the SColorPicker.
   *
   * @param l
   *          the listener to add
   */
  public void addChangeListener(ChangeListener l) {
    listenerList.add(ChangeListener.class, l);
  }

  /**
   * Removes a <code>ChangeListener</code> from the SColorPicker.
   *
   * @param l
   *          the listener to remove
   */
  public void removeChangeListener(ChangeListener l) {
    listenerList.remove(ChangeListener.class, l);
  }

  /**
   * Returns an array of all the change listeners registered on this
   * <code>SColorPicker</code>.
   *
   * @return all of this model's <code>ChangeListener</code>s or an empty
   *         array if no change listeners are currently registered
   * @see #addChangeListener
   * @see #removeChangeListener
   */
  public ChangeListener[] getChangeListeners() {
    return listenerList.getListeners(ChangeListener.class);
  }

  /**
   * Notifies all listeners that have registered interest for notification on
   * this event type. The event instance is created lazily.
   *
   * @see EventListenerList
   */
  protected void fireStateChanged() {
    // Guaranteed to return a non-null array
    Object[] listeners = listenerList.getListenerList();
    // Process the listeners last to first, notifying
    // those that are interested in this event
    for (int i = listeners.length - 2; i >= 0; i -= 2) {
      if (listeners[i] == ChangeListener.class) {
        // Lazily create the event:
        if (changeEvent == null) {
          changeEvent = new ChangeEvent(this);
        }
        ((ChangeListener) listeners[i + 1]).stateChanged(changeEvent);
      }
    }
  }

  /**
   * Gets the value.
   *
   * @return the value.
   */
  public Color getValue() {
    return value;
  }

  /**
   * Sets the value.
   *
   * @param value
   *          the value to set.
   */
  public void setValue(Color value) {
    this.value = value;
    chooseButton.setBackground(value);
    fireStateChanged();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled) {
    super.setEnabled(enabled);
    chooseButton.setEnabled(isEnabled());
    resetButton.setEnabled(isEnabled());
  }

  private void showColorPickerDialog() {
    final XColorPicker colorPicker = new XColorPicker();
    final SDialog colorPickerDialog = new SDialog(getParentFrame());
    SButton okButton = new SButton("OK");
    SButton cancelButton = new SButton("Cancel");
    SButton restoreButton = new SButton("Restore");

    if (getValue() != null) {
      colorPicker.setSelectedColor(getValue().getRed(), getValue().getGreen(),
          getValue().getBlue());
    }

    okButton.addActionListener(new ActionListener() {

      public void actionPerformed(@SuppressWarnings("unused")
      ActionEvent e) {
        setValue(colorPicker.getSelectedColor());
        colorPickerDialog.dispose();
      }
    });

    cancelButton.addActionListener(new ActionListener() {

      public void actionPerformed(@SuppressWarnings("unused")
      ActionEvent e) {
        colorPickerDialog.dispose();
      }
    });

    restoreButton.addActionListener(new ActionListener() {

      public void actionPerformed(@SuppressWarnings("unused")
      ActionEvent e) {
        if (getValue() != null) {
          colorPicker.setSelectedColor(getValue().getRed(), getValue()
              .getGreen(), getValue().getBlue());
        }
      }
    });

    SPanel actionPanel = new SPanel();
    actionPanel.setLayout(new SBoxLayout(SBoxLayout.X_AXIS));
    actionPanel.add(okButton);
    actionPanel.add(cancelButton);
    actionPanel.add(restoreButton);

    SPanel mainPanel = new SPanel(new SBorderLayout());
    mainPanel.add(colorPicker, SBorderLayout.CENTER);
    mainPanel.add(actionPanel, SBorderLayout.SOUTH);

    colorPickerDialog.setClosable(false);
    colorPickerDialog.setDefaultButton(okButton);
    colorPickerDialog.add(mainPanel);

    colorPickerDialog.setVisible(true);
  }


  /**
   * Gets the resetValue.
   *
   * @return the resetValue.
   */
  public Color getResetValue() {
    return resetValue;
  }


  /**
   * Sets the resetValue.
   *
   * @param resetValue the resetValue to set.
   */
  public void setResetValue(Color resetValue) {
    this.resetValue = resetValue;
  }
}
