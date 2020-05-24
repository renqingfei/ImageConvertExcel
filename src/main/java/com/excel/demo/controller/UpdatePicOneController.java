package com.excel.demo.controller;

import com.baidu.aip.ocr.AipOcr;
import com.excel.demo.util.ImageConvertExcel;
import com.excel.demo.util.UpAndDown;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
public class UpdatePicOneController {
    static String APPID = "20034953";
    static String APIKEY = "ydLgpNLzCkrQLfpNvMpVsQEN";
    static String SecretKey = "mmpKUC0Lo08ozF4vOutxhf7dI2bAHrPZ";
    @RequestMapping("/uploadOne")
    public String uploadOne(MultipartFile file, HttpServletRequest request){
        if(file!=null){
            AipOcr aipOcr = new AipOcr(APPID,APIKEY,SecretKey);
            String path = UpAndDown.upload(file, request);
            String s = request.getSession().getServletContext().getRealPath("/pic") + File.separator + path;
            String url = ImageConvertExcel.tableRecognition(aipOcr, s);
            request.setAttribute("urls", url);
            return "URL";
        }
        return null;
    }
}
