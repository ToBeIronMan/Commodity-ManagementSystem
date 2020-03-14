package wust.commodity_management_system.util.image_orc;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * 获取百度图像识别权限token类
 */
public class AuthService {
    /**
     * 官网获取的 API Key 更新为你注册的
     */
    static String clientId = "OtuDETZH9679xH1GGsPYeWTW";
    /**
     * 官网获取的 Secret Key 更新为你注册的
     */
    static String clientSecret = "80ISncf3FIRGOZ2Lhe9bo6qwUOxtO7Ib";
    /**
     * token 到期时间
     */
    public static long tokenTime = 0L;
    /**
     * token
     */
    public static String access_token;

    /**
     * @description 判断是否过期-获取token
     *
     * @return java.lang.String: 权限token
     * @author haoran.xiao
     * @date 2020/3/4 12:54
     */
    public static String getToken() {
        return getToken(clientId, clientSecret);
    }

    /**
     * @description 判断是否过期-获取token
     *
     * @param ak: 官网获取的 API Key 更新为你注册的
     * @param sk: 官网获取的 Secret Key 更新为你注册的
     * @return java.lang.String: 权限token
     * @author haoran.xiao
     * @date 2020/3/4 13:05
     */
    public static String getToken(String ak, String sk) {
        if (System.currentTimeMillis() > tokenTime) {
            access_token=getAuth(ak,sk);
        }
        return access_token;
    }

    /**
     * @description 获取权限token
     *
     * @return java.lang.String: 权限token
     * @author haoran.xiao
     * @date 2020/3/4 12:00
     */
    public static String getAuth() {
        return getAuth(clientId, clientSecret);
    }

    /**
     * @description 获取权限token
     *
     * @param ak: 官网获取的 API Key 更新为你注册的
     * @param sk: 官网获取的 Secret Key 更新为你注册的
     * @return java.lang.String: 权限token
     * @author haoran.xiao
     * @date 2020/3/4 13:07
     */
    public static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("POST");//百度推荐使用POST请求
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            System.out.println("result:" + result);
            JSONObject jsonObject = JSONObject.parseObject(result.toString());
            //获取过期时间段
            String expires_in = jsonObject.getString("expires_in");
            //token
            String access_token = jsonObject.getString("access_token");
            // 过期时间
            tokenTime = System.currentTimeMillis() + Long.valueOf(expires_in);
            return access_token;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }

        return null;
    }


}
