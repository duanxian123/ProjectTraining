package com.linghao.projecttraining.utils;

import android.text.TextUtils;


import com.linghao.projecttraining.model.db.City;
import com.linghao.projecttraining.model.db.County;
import com.linghao.projecttraining.model.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by linghao on 2017/6/7.
 */

public class Utility {
    //解析和处理服务器返回的省级数据
    public static  boolean handleProvinceResponse(String reponse){
        if (!TextUtils.isEmpty(reponse)){
            try {
                JSONArray allProvinces=new JSONArray(reponse);
                for (int i=0;i<allProvinces.length();i++){
                    JSONObject provinceObject=allProvinces.getJSONObject(i);
                    Province province=new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));

                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    return false;
    }
    //解析和处理服务器返回的市级数据
    public static  boolean handleCityResponse(String reponse,int provinceId){
        if (!TextUtils.isEmpty(reponse)){
            try {
                JSONArray allCities=new JSONArray(reponse);
                for (int i=0;i<allCities.length();i++){
                    JSONObject cityObject=allCities.getJSONObject(i);
                    City city=new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static  boolean handleCountyResponse(String reponse,int cityId){
        if (!TextUtils.isEmpty(reponse)){
            try {
                JSONArray allCounties=new JSONArray(reponse);
                for (int i=0;i<allCounties.length();i++){
                    JSONObject countyObject=allCounties.getJSONObject(i);
                    County county=new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
