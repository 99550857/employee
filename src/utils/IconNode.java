package utils;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by 99550 on 2017/11/19.
 */
public class IconNode extends DefaultMutableTreeNode{
    protected Icon icon;
    protected String txt;
    public IconNode(Icon icon,String txt){
        super();
        this.icon=icon;
        this.txt=txt;
    }

    public IconNode(String txt) {
        super();
        this.txt=txt;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
