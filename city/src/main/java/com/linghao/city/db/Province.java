package com.linghao.city.db;

import org.litepal.crud.DataSupport;

/**
 * Created by linghao on 2017/6/7.
 */

public class Province extends DataSupport {
    private  int id;
    private  String provinceName;
    private int provinceCode;
    public int getId(){
        return  id;
    }
    public  void setId(int id){
        this.id=id;
    }
    public int getProvinceCode(){
        return  provinceCode;
    }
    public  void setProvinceCode(int provinceCode){
        this.provinceCode=provinceCode;
    }
    public  String getProvinceName(){
        return  provinceName;
    }
    public void setProvinceName(String provinceName){this.provinceName=provinceName;}
}
