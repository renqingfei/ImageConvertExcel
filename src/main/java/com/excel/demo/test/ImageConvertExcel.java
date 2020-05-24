package com.excel.demo.test;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;

public class ImageConvertExcel {

    static String APPID = "20034953";
    static String APIKEY = "ydLgpNLzCkrQLfpNvMpVsQEN";
    static String SecretKey = "mmpKUC0Lo08ozF4vOutxhf7dI2bAHrPZ";

    public static void main(String[] args) {
        AipOcr aipOcr = new AipOcr(APPID,APIKEY,SecretKey);
        try {
            sample(aipOcr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sample(AipOcr client) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();


        // 参数为本地图片路径
        String image = "/Users/rqf/IdeaProjects/ImageConvertExcel/src/main/webapp/image/222.jpeg";
//        JSONObject res = client.tableRecognitionAsync(image, options);
//        System.out.println(res.toString(2));

        // 参数为本地图片二进制数组
        byte[] file = readImageFile(image);
        JSONObject res = client.tableRecognitionAsync(file, options);
        System.out.println(res.toString(2));
        getSample(client,res);
    }

    public static void getSample(AipOcr client,JSONObject res) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("result_type", "excel");
        JSONArray resultArray = (JSONArray)res.get("result");
        JSONObject s = (JSONObject)resultArray.get(0);
        String  request_id = String.valueOf(s.get("request_id"));


        String requestId = request_id;
        // 表格识别结果
        JSONObject result = client.tableResultGet(requestId, options);
        String url = (String)((JSONObject) result.get("result")).get("result_data");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println(url);

    }
    private static byte[] readImageFile(String image) {

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
}
