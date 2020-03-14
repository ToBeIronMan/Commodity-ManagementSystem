package wust.commodity_management_system.util.image_orc;

import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 用来将图片转化base64后转UrlEncode
 */
public class BaseImg64 {
    /**
     * 将一张本地图片转化成Base64字符串
     *
     * @param imgPath: 图片的url路径，如D:\\photo\\1.png
     * @return java.lang.String: 
     * @author haoran.xiao
     * @date 2020/3/4 13:28
     */
    public static String getImageStrFromPath(String imgPath) throws UnsupportedEncodingException {
        InputStream in;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过再URLEncode的字节数组字符串
        return URLEncoder.encode(encoder.encode(data), "UTF-8");
    }

}
