package com.gxj1228.customcamera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.gxj1228.customcamera.camera.CameraActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView = (ImageView) findViewById(R.id.main_image);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == CameraActivity.REQUEST_CODE) {
            //获取文件路径，显示图片
            if (data != null) {
                String path = data.getStringExtra("result");
                if (!TextUtils.isEmpty(path)) {
                    imageView.setImageBitmap(BitmapFactory.decodeFile(path));
                }
            }
        }
    }

    /**
     * 拍摄证件照片
     *
     * @param type 拍摄证件类型
     */
    private void takePhoto(int type) {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0x12);
            return;
        }
        CameraActivity.navToCamera(this, type);
    }

    /**
     * 身份证正面
     */
    public void frontIdCard(View view) {
        takePhoto(CameraActivity.TYPE_ID_CARD_FRONT);
    }

    /**
     * 身份证反面
     */
    public void backIdCard(View view) {
        takePhoto(CameraActivity.TYPE_ID_CARD_BACK);
    }
}
