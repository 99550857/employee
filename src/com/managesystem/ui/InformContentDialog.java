package com.managesystem.ui;

import com.managesystem.model.Inform;

import javax.swing.*;
import java.awt.event.*;

public class InformContentDialog extends JDialog {
    private JPanel contentPane;
    private JTextArea textArea1;
    private JLabel label1;
    private JLabel titleLabel;
    private JButton button1;

    public InformContentDialog(Inform inform,String name) {
        setModal(true);
        add(contentPane);
        pack();
        setVisible(true);
        setSize(500, 300);
        setLocationRelativeTo(null);
        label1.setText(name + " "+ inform.getName() + " 于 "+inform.getDate()+"  发布");
        titleLabel.setText(inform.getTitle());
        textArea1.setText(inform.getContent());
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InformContentDialog.this.dispose();
            }
        });
    }
}
