package com.excel.demo.controller;

import com.baidu.aip.ocr.AipOcr;
import com.excel.demo.util.ImageConvertExcel;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.Base64;
import java.util.HashMap;

@RestController
public class UpdatePicController {

    static String APPID = "20034953";
    static String APIKEY = "ydLgpNLzCkrQLfpNvMpVsQEN";
    static String SecretKey = "mmpKUC0Lo08ozF4vOutxhf7dI2bAHrPZ";
    @RequestMapping("/upload")
    @ResponseBody
    public String updatePic(String pic){
        try {
            if(!StringUtils.isEmpty(pic)){
                AipOcr aipOcr = new AipOcr(APPID,APIKEY,SecretKey);
                String path = base64ToFile("pic",pic.split(";")[1],System.currentTimeMillis()+".jpeg");
                String url = tableRecognition(aipOcr, path);
                return url;
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return null;
    }

    public JSONObject sample(AipOcr client,byte[] file) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        // 参数为本地图片二进制数组
        JSONObject res = client.tableRecognitionAsync(file, options);
        System.out.println(res.toString(2));
        return res;
    }

    private byte[] readImageFile(String image) {

        byte[] buffer = null;
        try
        {
            File file = new File(image);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return buffer;
    }

    public String base64ToFile(String destPath,String base64, String fileName) {
        File file = null;
        //创建文件目录
        String filePath=destPath;
        File  dir=new File(filePath);
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        BufferedOutputStream bos = null;
        java.io.FileOutputStream fos = null;
        try {
            BASE64Decoder d = new BASE64Decoder();
            byte[] bytes = d.decodeBuffer(base64);
            file=new File(filePath+"/"+fileName);
            fos = new java.io.FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return filePath+"/"+fileName;
    }

    public String tableRecognition(AipOcr client, String file) {
        JSONObject excelres = client.tableRecognizeToExcelUrl(file, 20000);
        String url = (String)((JSONObject) excelres.get("result")).get("result_data");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println(url);
        return url;
    }
}
