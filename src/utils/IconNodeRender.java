package utils;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

/**
 * Created by 99550 on 2017/11/19.
 */
public class IconNodeRender extends DefaultTreeCellRenderer{
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        Icon icon=((IconNode)value).getIcon();
        String txt=((IconNode)value).getTxt();
        setIcon(icon);
        setText(txt);
        return this;
    }


}
