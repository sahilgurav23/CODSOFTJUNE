package com.example.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.sax.TextElementListener;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton toggleButton;
    CameraManager mCameraManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton = (ToggleButton) findViewById(R.id.togglebuttonid);
        flash();
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                flash();
            }
        });
    }

    void flash()  {
        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            if (toggleButton.isChecked())
                try{
                    mCameraManager.setTorchMode(mCameraManager.getCameraIdList()[0],true);
                    Toast.makeText(this, "Torch is on", Toast.LENGTH_SHORT).show();
                }
                catch (CameraAccessException e) {
                    throw new RuntimeException(e);
                }
            else
                try{
                    mCameraManager.setTorchMode(mCameraManager.getCameraIdList()[0],false);
                    Toast.makeText(this, "Torch is off", Toast.LENGTH_SHORT).show();
                }
                catch (CameraAccessException e) {
                    throw new RuntimeException(e);
            }
        }
        else
            Toast.makeText(this, "Device not support the Flashlight", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        toggleButton.setChecked(false);
        flash();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        toggleButton.setChecked(false);
        flash();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        toggleButton.setChecked(true);
        flash();
    }
}