package wust.commodity_management_system.util;


import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import wust.commodity_management_system.model.Image;

import java.io.*;

import java.util.UUID;

/**
 * 文件读取工具类
 */
public class FileUtil {

    /**
     * 读取文件内容，作为字符串返回
     */
    public static String readFileAsString(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        } 

        if (file.length() > 1024 * 1024 * 1024) {
            throw new IOException("File is too large");
        } 

        StringBuilder sb = new StringBuilder((int) (file.length()));
        // 创建字节输入流  
        FileInputStream fis = new FileInputStream(filePath);  
        // 创建一个长度为10240的Buffer
        byte[] bbuf = new byte[10240];  
        // 用于保存实际读取的字节数  
        int hasRead = 0;  
        while ( (hasRead = fis.read(bbuf)) > 0 ) {  
            sb.append(new String(bbuf, 0, hasRead));  
        }  
        fis.close();  
        return sb.toString();
    }

    /**
     * 根据文件路径读取byte[] 数组
     */
    public static byte[] readFileByBytes(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
            BufferedInputStream in = null;

            try {
                in = new BufferedInputStream(new FileInputStream(file));
                short bufSize = 1024;
                byte[] buffer = new byte[bufSize];
                int len1;
                while (-1 != (len1 = in.read(buffer, 0, bufSize))) {
                    bos.write(buffer, 0, len1);
                }

                byte[] var7 = bos.toByteArray();
                return var7;
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException var14) {
                    var14.printStackTrace();
                }

                bos.close();
            }
        }
    }

    /**
     * base64字符串转化成图片,并保存到本地
     *
     * @param image:
     * @return boolean:
     * @author haoran.xiao
     * @date 2020/3/5 14:30
     */
    public static boolean GenerateImage(Image image){
       return GenerateImage(image.getBase64ImgStr(), image.getName(),image.getPath());
    }

    /**
     * base64字符串转化成图片,并保存到本地
     *
     * @param imgStr: 图片的base64字符串
     * @param path: 保存路径
     * @return boolean: 是否成功
     * @author haoran.xiao
     * @date 2020/3/4 16:35
     */
    public static boolean GenerateImage(String imgStr,String path) {
        //将前端传过来的Base64解码并生成图片
        //判断图像数据为空
        if (imgStr == null)
        {
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try{
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i){
                if(b[i]<0){
                    b[i]+=256;
                }
            }

            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }catch (Exception e){
            //e.printStackTrace();
            return false;
        }
    }

    /**
     * base64字符串转化成图片,并保存到本地
     *
     * @param imgStr: 图片的base64字符串
	 * @param name: 图片名
     * @param path: 保存路径
     * @return boolean: 是否成功
     * @author haoran.xiao
     * @date 2020/3/4 16:35
     */
    public static boolean GenerateImage(String imgStr,String path,String name) {
        return  GenerateImage(imgStr,path+name);
    }

    /**
     * 根据路径删除图片
     *
     * @param path: 图片全路径
     * @return boolean:
     * @author haoran.xiao
     * @date 2020/3/8 17:23
     */
    public static boolean deleteFile(String path){
        if(path==null||"".equals(path)){
            return false;
        }
        File file = new File(path);
        Boolean flag = false;
        //判断文件是否存在
        if (file.exists() == true){
            System.out.println("图片存在，可执行删除操作");

            flag = file.delete();
            if (flag){
                System.out.println("成功删除图片"+file.getName());
            }else {
                System.out.println("删除失败");
            }
        }else {
            System.out.println("图片不存在，终止操作");
        }
        return flag;
    }

    /**
     * 根据路径删除图片
     *
     * @param path: 路径
	 * @param name: 图片名
     * @return boolean:
     * @author haoran.xiao
     * @date 2020/3/8 17:23
     */
    public static boolean deleteFile(String path,String name){
        return deleteFile(path+name);
    }

    /**
     * 更新指定路径文件
     *
     * @param multipartFile:
	 * @param fullPath: 要更新文件的全路径
	 * @param name: 要更新的新文件名,如果为空,则默认使用UUID作为文件名
     * @return boolean: 保存是否成功
     * @author haoran.xiao
     * @date 2020/3/9 17:14
     */
    public static boolean updateMultipartFile(MultipartFile multipartFile, String fullPath, String name) throws IOException {
        boolean result=false;
        //旧文件名
        String oldFileName=multipartFile.getOriginalFilename();
        String randomStr= UUID.randomUUID().toString();
        String suffix=oldFileName.substring(oldFileName.lastIndexOf("."));
        File file=new File(fullPath);
        if(name!=null&&!"".equals(name)){
            String newFileName=name+suffix;
            String newFullPath=new File(fullPath).getParent()+newFileName;
            if(!newFullPath.equals(fullPath)&&new File(newFullPath).exists()){
                throw new RuntimeException("该文件名已存在,请更换文件名");
            }
        }
        updateMultipartFileUseFullPath(multipartFile, fullPath);
        return true;
    }


    /**
     * 更新指定路径文件
     *
     * @param multipartFile:
	 * @param fullPath: 要更新文件的全路径
     * @return boolean:
     * @author haoran.xiao
     * @date 2020/3/9 18:30
     */
    public static boolean updateMultipartFileUseFullPath(MultipartFile multipartFile, String fullPath) throws IOException {
        boolean result=false;
        if(multipartFile==null||multipartFile.isEmpty()){
            System.err.println("文件不存在");
            return false;
        }

        File file=new File(fullPath);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        multipartFile.transferTo(file);
        return true;
    }

    /**
     * 添加文件到指定路径
     *
     * @param multipartFile:
	 * @param path: 要更新文件的目录
	 * @param name: 要更新的文件名,如果为空则使用UUID
     * @return boolean:
     * @author haoran.xiao
     * @date 2020/3/9 19:48
     */
    public static boolean addMultipartFileUseFullPath(MultipartFile multipartFile, String path,String name) throws IOException {
        boolean result=false;
        if(multipartFile==null||multipartFile.isEmpty()){
            System.err.println("文件不存在");
            return false;
        }
        //旧文件名
        String oldFileName=multipartFile.getOriginalFilename();
        String randomStr= UUID.randomUUID().toString();
        String suffix=oldFileName.substring(oldFileName.lastIndexOf("."));
        String newFileName="";
        File file=new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        if(name==null||"".equals(name)){
            newFileName=randomStr+suffix;
        }else{
            newFileName=name+suffix;
        }
        file=new File(file, newFileName);
        if(file.exists()){
            throw new RuntimeException("该文件名已存在");
        }
        multipartFile.transferTo(file);
        return true;
    }
}
