package com.chs.app_jetpack.navigation;

import android.content.res.AssetManager;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.Utils;
import com.chs.app_jetpack.model.BottomBar;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * author：chs
 * date：2020/3/27
 * des：
 */
public class AppConfig {


    private static HashMap<String, Destination> sDestinationMap;
    private static BottomBar sBottomBar;


    public static HashMap<String, Destination> getDestinationMap() {
        if (sDestinationMap == null) {
            String jsonContent = parseFile("destination.json");
            sDestinationMap =  GsonUtils.fromJson(jsonContent,new TypeToken<HashMap<String, Destination>>(){}.getType());
        }
        return sDestinationMap;
    }

    public static BottomBar getsBottomBar(){
        if(sBottomBar == null){
            String jsonContent = parseFile("main_tabs_config.json");
            sBottomBar = GsonUtils.fromJson(jsonContent,BottomBar.class);
        }
        return sBottomBar;
    }

    private static String parseFile(String fileName) {
        AssetManager assets = Utils.getApp().getResources().getAssets();
        InputStream is = null;
        BufferedReader br = null;
        StringBuilder builder = new StringBuilder();
        try {
            is = assets.open(fileName);
            br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            if ((line = br.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }

}
