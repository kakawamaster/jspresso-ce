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
package org.jspresso.framework.gui.ulc.components.client;

import java.awt.Component;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EventObject;
import java.util.Locale;

import javax.swing.AbstractCellEditor;
import javax.swing.ComboBoxEditor;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellEditor;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.tree.TreeCellEditor;

import org.jspresso.framework.gui.swing.components.JDateField;
import org.jspresso.framework.gui.ulc.components.shared.ActionFieldConstants;
import org.jspresso.framework.gui.ulc.components.shared.DateFieldConstants;
import org.jspresso.framework.util.format.NullableSimpleDateFormat;
import org.jspresso.framework.util.gui.GuiException;

import com.ulcjava.base.client.IEditorComponent;
import com.ulcjava.base.client.UIComponent;
import com.ulcjava.base.client.tabletree.TableTreeCellEditor;
import com.ulcjava.base.shared.IUlcEventConstants;
import com.ulcjava.base.shared.internal.Anything;

/**
 * ULC UI class responsible for handling date field client half object.
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
public class UIDateField extends UIComponent implements IEditorComponent {

  private TableCellEditor tableCellEditor;

  /**
   * {@inheritDoc}
   */
  @Override
  public JDateField getBasicObject() {
    return (JDateField) super.getBasicObject();
  }

  /**
   * {@inheritDoc}
   */
  public ComboBoxEditor getComboBoxEditor() {
    throw new UnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  public TableCellEditor getTableCellEditor() {
    if (tableCellEditor == null) {
      tableCellEditor = new DateFieldTableCellEditor();
    }
    return tableCellEditor;
  }

  /**
   * {@inheritDoc}
   */
  public TableTreeCellEditor getTableTreeCellEditor() {
    throw new UnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  public TreeCellEditor getTreeCellEditor() {
    throw new UnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void handleRequest(String request, Anything args) {
    if (request.equals(DateFieldConstants.SET_VALUE_REQUEST)) {
      handleSetValue(args);
    } else if (request.equals(ActionFieldConstants.SET_EDITABLE_REQUEST)) {
      handleSetEditable(args);
    } else {
      super.handleRequest(request, args);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void restoreState(Anything args) {
    super.restoreState(args);
    handleSetValue(args);
    getBasicObject().addChangeListener(new ChangeListener() {

      public void stateChanged(@SuppressWarnings("unused")
      ChangeEvent e) {
        notifyULCValueChange(getBasicObject().getValue());
      }
    });
    getBasicObject().getFormattedTextField().addPropertyChangeListener("value",
        new PropertyChangeListener() {

          public void propertyChange(PropertyChangeEvent evt) {
            notifyULCValueChange(evt.getNewValue());
          }
        });
    getBasicObject().setEditable(
        args.get(DateFieldConstants.EDITABLE_KEY, true));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Object createBasicObject(Anything args) {
    DateFormatter formatter = new DateFormatter(new NullableSimpleDateFormat(
        args.get(DateFieldConstants.FORMAT_PATTERN_KEY,
            ((SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT))
                .toPattern())));
    Locale locale = new Locale(args.get(DateFieldConstants.LANGUAGE_KEY, Locale
        .getDefault().getLanguage()));
    JDateField dateField = new JDateField(formatter, locale) {

      private static final long serialVersionUID = 6298852573281209804L;

      /**
       * Prevent ULC framework for installing bogus focus listeners on the
       * component.
       * <p>
       * {@inheritDoc}
       */
      @Override
      public synchronized void addFocusListener(FocusListener l) {
        if (l.getClass().getName()
            .startsWith("com.ulcjava.base.client.UITable")) {
          return;
        }
        super.addFocusListener(l);
      }
    };

    dateField.getFormattedTextField().setFormatterFactory(
        new DefaultFormatterFactory(formatter));
    return dateField;
  }

  private void handleSetEditable(Anything args) {
    getBasicObject().setEditable(
        args.get(DateFieldConstants.EDITABLE_KEY, true));
  }

  private void handleSetValue(Anything args) {
    long timeMillis = args.get(DateFieldConstants.VALUE_KEY, 0L);
    Date value = null;
    if (timeMillis != 0L) {
      value = new Date(timeMillis);
    }
    getBasicObject().setValue(value);
  }

  private void notifyULCValueChange(Object newValue) {
    Anything args = new Anything();
    valueToAnything((Date) newValue, args);
    sendULC(DateFieldConstants.SET_VALUE_REQUEST, args);
    sendOptionalEventULC(IUlcEventConstants.VALUE_CHANGED_EVENT,
        IUlcEventConstants.VALUE_CHANGED);
  }

  private void valueToAnything(Date value, Anything args) {
    long timeMillis = -0L;
    if (value != null) {
      timeMillis = value.getTime();
    }
    args.put(DateFieldConstants.VALUE_KEY, timeMillis);
  }

  private final class DateFieldTableCellEditor extends AbstractCellEditor
      implements TableCellEditor {

    private static final long      serialVersionUID = 2486701057600652062L;
    private PropertyChangeListener editingStopChangeListener;

    /**
     * Constructs a new <code>DateFieldTableCellEditor</code> instance.
     */
    public DateFieldTableCellEditor() {
      editingStopChangeListener = new PropertyChangeListener() {

        public void propertyChange(PropertyChangeEvent evt) {
          if (evt.getNewValue() == null && evt.getOldValue() == null) {
            return;
          }
          stopCellEditing();
        }
      };
    }

    /**
     * {@inheritDoc}
     */
    public Object getCellEditorValue() {
      // don't call getValue() due to bad focusevent delivery order of
      // JFormattedTextField.
      // return dateField.getValue();
      if (getBasicObject().getFormattedTextField().getText() == null
          || getBasicObject().getFormattedTextField().getText().length() == 0) {
        return null;
      }
      try {
        return getBasicObject().getFormattedTextField().getFormatter()
            .stringToValue(getBasicObject().getFormattedTextField().getText());
      } catch (ParseException ex) {
        throw new GuiException(ex);
      }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unused")
    public Component getTableCellEditorComponent(JTable table, Object value,
        boolean isSelected, int row, int col) {
      getBasicObject().getFormattedTextField().removePropertyChangeListener(
          "value", editingStopChangeListener);
      getBasicObject().setValue(value);
      getBasicObject().getFormattedTextField().selectAll();
      getBasicObject().getFormattedTextField().addPropertyChangeListener(
          "value", editingStopChangeListener);
      return getBasicObject();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCellEditable(EventObject evt) {
      if (evt instanceof MouseEvent) {
        MouseEvent me = (MouseEvent) evt;
        return (me.getClickCount() >= 2);
      }
      return super.isCellEditable(evt);
    }
  }
}
