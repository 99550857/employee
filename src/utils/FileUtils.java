package utils;

import java.io.*;

/**
 * 文件工具类
 */
public class FileUtils {
    public static byte[] fileToBytes(File file) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] b = new byte[(int) file.length()];
        try {
            inputStream.read(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }
}
