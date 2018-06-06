package com.hzy.blobtest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    /**
     * ImageView 用于测试图片加载显示
     */
    private ImageView imv;
    /**
     * 上传的图片路径和名字
     */
    private String picName = "111.jpg";//测试上传，图片地址和名字暂时写死--到项目中根据拍照存放图片的地址及名称保存；


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imv = findViewById(R.id.imv);
        checkPermission();
    }

    private void checkPermission() {
        //检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
            }
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1111);

        } else {
            Toast.makeText(this, "授权成功！", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 上传图片
     */
    public void testUpload(View v) {
        checkPermission();
        new UploadPicTask(MainActivity.this, Constant.LOCAL_PIC_PATH, picName).execute();
    }

    /**
     * 显示图片
     */
    public void testShow(View v) {
        Glide.with(this).load(Constant.NET_PIC_PATH+picName).into(imv);
    }
}
