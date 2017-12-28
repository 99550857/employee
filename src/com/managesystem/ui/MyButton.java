package com.managesystem.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * @author 李爽
 * 17297 on 2017/12/18.
 */
public class MyButton extends JButton {
   public static final Dimension BT_SIZE1 = new Dimension(55,25);
    public static final Dimension BT_SIZE2  = new Dimension(85,35);
    public static final Color BT_BLUE = new Color(57,179,215);
    public static final Color BT_RED  = new Color(210,34,61);
    public static final Color BT_GREEN = new Color(72,201,176);
    public final static Font NOMAL_FONT = new Font("微软雅黑", Font.PLAIN, 20);


//    public static void setButton1(JButton button1){
//
//        //初始化透明按钮
//
//        button1.setBorder(null);
//        button1.setFocusable(false);
//
//       button1.setPreferredSize(BT_SIZE1);
//        //button1.setBackground(BT_BLUE);
//
//        button1.setBorder(BorderFactory.createRaisedBevelBorder());
//
//        button1.setFont(NOMAL_FONT);
//
//        button1.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                button1.setForeground(Color.CYAN);
//                button1.repaint();
//            }
//            @Override
//            public void mouseExited(MouseEvent e) {
//                button1.setForeground(BT_BLUE);
//                button1.repaint();
//            }
//        });
//    }
    public MyButton(String s) {
        super(s);
        //初始化透明按钮
        setBorder(null);
         setFocusable(false);
        // 是否显示外围矩形区域 选否
        setContentAreaFilled(false);
        //凸起效果
        setBorder(BorderFactory.createRaisedBevelBorder());
        setPreferredSize(BT_SIZE1);
        setBackground(BT_BLUE);
        setFont(NOMAL_FONT);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(Color.white);
                //revalidate();
            }
            @Override
            public void mouseExited(MouseEvent e) {
               setForeground(BT_RED);
               //revalidate();
            }
        });
}
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(BT_BLUE);
        //填充圆角矩形区域 也可以为其它的图形
        g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1,
                20, 20);
        //必须放在最后 否则画不出来
        super.paintComponent(g);
    }
    @Override
    public void paintBorder(Graphics g) {
        //画边界区域
        g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1,
                20, 20);
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame();
        MyButton myButton = new MyButton("ceshi");
        frame.getContentPane().add(myButton);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setSize(400,300);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }
}
