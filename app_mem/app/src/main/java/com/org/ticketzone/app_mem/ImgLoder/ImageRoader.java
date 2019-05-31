package com.org.ticketzone.app_mem.ImgLoder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ImageRoader {
    private final String serverUrl = "http://15.164.115.73:8080/";
    public ImageRoader(){

    }
    public Bitmap getBitmapImg(String imgStr){
        Bitmap bitampImg = null;
        try{
            URL url = new URL(serverUrl + URLEncoder.encode(imgStr,"utf-8"));
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitampImg = BitmapFactory.decodeStream(is);
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        return bitampImg;
    }
}
