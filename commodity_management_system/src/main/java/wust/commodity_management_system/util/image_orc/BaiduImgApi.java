package wust.commodity_management_system.util.image_orc;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Encoder;
import wust.commodity_management_system.util.FileUtil;
import wust.commodity_management_system.util.HttpUtil;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class BaiduImgApi {
    /**
     * 百度图片识别api接口地址
     */
    private static final String BASE_URL = "https://aip.baidubce.com/rest/2.0/image-classify/v2/advanced_general";
    /**
     * 百度图片识别api接口地址带token
     */
    private static final String POST_URL = "https://aip.baidubce.com/rest/2.0/image-classify/v2/advanced_general?access_token="
            + AuthService.getToken();

    /**
     * 识别本地图片并返回json数据
     *
     * @param path: 图片本地路径
     * @return java.lang.String:
     * @author haoran.xiao
     * @date 2020/3/4 13:42
     */
    public static String checkFile(String path) throws URISyntaxException, IOException {
        File file = new File(path);
        if (!file.exists()) {
            throw new NullPointerException("图片不存在");
        }
        String image = BaseImg64.getImageStrFromPath(path);
        String param = "image=" + image;
        return post(param);
    }

    /**
     * 根据图片url识别结果，为json格式
     *
     * @param url: 图片url地址
     * @return java.lang.String:
     * @author haoran.xiao
     * @date 2020/3/4 13:44
     */
    public static String checkUrl(String url) throws IOException, URISyntaxException {
        String param = "url=" + url;
        return post(param);
    }

    /**
     * 通过传递参数：url和image进行文字识别
     *
     * @param param: url中的参数,如img=UrlEncode字符串
     * @return java.lang.String:
     * @author haoran.xiao
     * @date 2020/3/4 13:51
     */
    private static String post(String param) throws URISyntaxException, IOException {
        // 开始搭建post请求
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost();
        URI url = new URI(POST_URL);
        post.setURI(url);
        // 设置请求头，请求头必须为application/x-www-form-urlencoded，因为是传递一个很长的字符串，不能分段发送
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        StringEntity entity = new StringEntity(param);
        post.setEntity(entity);
        HttpResponse response = httpClient.execute(post);
        //System.out.println(response.toString());
        if (response.getStatusLine().getStatusCode() == 200) {
            String str;
            try {
                // 读取服务器返回过来的json字符串数据
                str = EntityUtils.toString(response.getEntity());
                System.out.println("--------------------------------------------------------");
                System.out.println(str);
                return str;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /**
     * 识别图片,返回json
     *
     * @param path: 本地图片路径
     * @return java.lang.String:
     * @author haoran.xiao
     * @date 2020/3/8 17:28
     */
    public static String advancedGeneral(String path) {

        // 本地文件路径
        String filePath = path;
        byte[] imgData = new byte[0];
        try {
            imgData = FileUtil.readFileByBytes(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();

        String imgStr = encoder.encode(imgData);

        return advancedGeneralCore(imgStr);
    }

    /**
     * 根据图片的base64编码字符串进行图像识别,返回json数据
     *
     * @param imgStr: 图片的base64字符串
     * @return java.lang.String:
     * @author haoran.xiao
     * @date 2020/3/8 17:35
     */
    public static String advancedGeneralCore(String imgStr) {

        try {
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            String param = "image=" + imgParam;
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getToken();
            String result = HttpUtil.post(BASE_URL, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
