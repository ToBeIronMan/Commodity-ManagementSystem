package wust.commodity_management_system.utilBean;

import org.springframework.util.ClassUtils;

import java.io.File;

/**
 * 项目默认的一些常量
 */
public class DefaultSet {
    //获取项目classes/static的地址
    public static final String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
    // 图片存储目录及图片名称
    public static final String url_path = "images" + File.separator;
    //图片保存路径
    public static final String savePath = staticPath + File.separator + url_path;
    // 访问路径=静态资源路径+文件目录路径
    public static final String visitPath ="static/" + url_path;

    //相对地址
    public static final String relativePath="/file/image/";

    //绝对地址
    public static final String absolutePath="D:/file/image/";

    public static final String rootPath="D:";

}
