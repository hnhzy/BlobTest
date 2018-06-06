package com.hzy.blobtest;

import android.os.Environment;

public class Constant {

    /**
     * DefaultEndpointsProtocol  默认协议
     * AccountName   存储账户名称
     * AccountKey    存储密钥
     * EndpointSuffix  指定服务器为core.chinacloudapi.cn
     */
    public static final String storageConnectionString = "DefaultEndpointsProtocol=https;" + "AccountName=customvision;" + "AccountKey=ZELCszKoEJCzO3kNqOc6NgJKAfJUa8q0Li1rZiY2ivWsgb5Ab7++OeIEq2j55XxcG+/cqRT5ErpSbYUM1Qh/xw==;" + "EndpointSuffix=core.chinacloudapi.cn";

    /**
     * 容器的网络地址
     */
    public static final String NET_PIC_PATH="https://customvision.blob.core.chinacloudapi.cn/testcontainer/";

    /**
     * 上传图片的目录--//测试上传，图片地址和名字暂时写死--到项目中根据拍照存放图片的地址及名称保存；
     */
    public static final String LOCAL_PIC_PATH= Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera/";



}
