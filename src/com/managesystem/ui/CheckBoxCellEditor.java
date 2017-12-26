package com.managesystem.ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by 99550 on 2017/12/24.
 */
public class CheckBoxCellEditor extends AbstractCellEditor implements TableCellEditor{
    private static final long serialVersionUID = 1L;

    //~ Instance fields ------------------------------------------------------------------------------------------------

    protected JCheckBox checkBox;

    //~ Constructors ---------------------------------------------------------------------------------------------------

    public CheckBoxCellEditor() {
        checkBox = new JCheckBox();
        checkBox.setHorizontalAlignment(SwingConstants.CENTER);
        // checkBox.setBackground( Color.white);
    }

    //~ Methods --------------------------------------------------------------------------------------------------------

    @Override public Object getCellEditorValue() {
        return Boolean.valueOf(checkBox.isSelected());
    }

    //~ ----------------------------------------------------------------------------------------------------------------

    @Override public Component getTableCellEditorComponent(
            JTable  table,
            Object  value,
            boolean isSelected,
            int     row,
            int     column) {
        checkBox.setSelected(((Boolean) value).booleanValue());

        return checkBox;

    }
} // end class CheckBoxCellEditor

