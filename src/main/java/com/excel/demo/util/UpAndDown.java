package com.excel.demo.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Component
    public class UpAndDown {

        public static String upload(MultipartFile myFile, HttpServletRequest request) {
            String myFilePath = "";
            if (!myFile.isEmpty()) {
                try {
                    String filename = myFile.getOriginalFilename();
                    filename = UUID.randomUUID() + filename.substring(filename.lastIndexOf("."));

/**

 文件路径：绝对路径，或，取应用内部目录或图片服务器的物理路径
 以下是上传到服务器下（非图片服务器）

 request.getSession().getServletContext()是获取的servlet容器对象，
 相当于tomcat容器了。getRealPath("/") 获取实际路径，项目发布时，
 在容器中的实际路径。

 */
                    myFilePath = request.getSession().getServletContext().getRealPath("/pic")  + File.separator + filename;
                    //创建文件目录
                    String filePath=request.getSession().getServletContext().getRealPath("/pic")  + File.separator;
                    File  dir=new File(filePath);
                    if (!dir.exists() && !dir.isDirectory()) {
                        dir.mkdirs();
                    }
                    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(myFilePath)));
                    out.write(myFile.getBytes());
                    out.flush();
                    out.close();
                    myFilePath = filename;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return myFilePath;
        }

    }