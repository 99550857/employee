package com.managesystem.ui;

import javax.swing.*;

/**
 *
 * @author lishuang
 * 2017-12-24
 * 将字符串转换成下拉框
 */
public class MyComboBoxEditor extends DefaultCellEditor {
        public MyComboBoxEditor(String[] items) {
            super(new JComboBox(items));
        }
    }
