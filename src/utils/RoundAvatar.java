package utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 99550 on 2017/12/17.
 */
public class RoundAvatar {
    public static void getRoundAvatar(JLabel jLabel,byte []bytes){
        try {
            InputStream is=new ByteArrayInputStream(bytes);
            BufferedImage url = ImageIO.read(is);
            //处理图片将其压缩成正方形的小图
            BufferedImage  convertImage= scaleByPercentage(url, 100,100);
            //裁剪成圆形 （传入的图像必须是正方形的 才会 圆形 如果是长方形的比例则会变成椭圆的）
            convertImage = convertCircular(convertImage);
            //生成的图片位置
            ImageIcon imageIcon=new ImageIcon(convertImage);

            jLabel.setIcon(imageIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private  static BufferedImage scaleByPercentage(BufferedImage inputImage, int newWidth, int newHeight) throws Exception {
        //获取原始图像透明度类型
        int type = inputImage.getColorModel().getTransparency();
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        //开启抗锯齿
        RenderingHints renderingHints=new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        //使用高质量压缩
        renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        BufferedImage img = new BufferedImage(newWidth, newHeight, type);
        Graphics2D graphics2d =img.createGraphics();
        graphics2d.setRenderingHints(renderingHints);
        graphics2d.drawImage(inputImage, 0, 0, newWidth, newHeight, 0, 0, width, height, null);
        graphics2d.dispose();
        return img;
    }
    private static   BufferedImage convertCircular(BufferedImage bi1) throws IOException {
        //这种是黑色底的
        //BufferedImage bi2 = new BufferedImage(bi1.getWidth(),bi1.getHeight(),BufferedImage.TYPE_INT_RGB);
        //透明底的图片
        BufferedImage bi2 = new BufferedImage(bi1.getWidth(),bi1.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
        Ellipse2D.Double shape = new Ellipse2D.Double(0,0,bi1.getWidth(),bi1.getHeight());
        Graphics2D g2 = bi2.createGraphics();
        g2.setClip(shape);
        // 使用 setRenderingHint 设置抗锯齿
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(bi1,0,0,null);
        //设置颜色
        g2.setBackground(Color.green);
        g2.dispose();
        return bi2;
    }
}
