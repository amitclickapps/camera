package com.camera;

import android.content.Intent;
import android.graphics.YuvImage;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.flurgle.camerakit.CameraView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by amit on 24/8/17.
 */

public class CaptureActivity extends AppCompatActivity implements View.OnClickListener {
    private CameraView cameraView;
    private ImageView captureBtn, switchCameraBtn;
    public static String CONFIGURATION = "CONFIGURATION";
    public static String DATA = "DATA";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        cameraView = (CameraView) findViewById(R.id.camera);
        captureBtn = (ImageView) findViewById(R.id.captureImage);
        switchCameraBtn = (ImageView) findViewById(R.id.switchCamera);
        Intent intent = getIntent();
        Param configuration = intent.getParcelableExtra(CONFIGURATION);
        if (configuration == null) {
            configuration = new Param();
        }
        cameraView.setFacing(configuration.getFacing());
        cameraView.setFlash(configuration.getFlash());
        cameraView.setFocus(configuration.getFocus());
        cameraView.setMethod(configuration.getMethod());
        cameraView.setZoom(configuration.getZoom());
        cameraView.setPermissions(configuration.getPermissions());
        cameraView.setCropOutput(configuration.isCropOutput());
        cameraView.setJpegQuality(configuration.getJpegQuality());
        cameraView.setVideoQuality(configuration.getVideoQuality());
        clickListener();
    }

    private void clickListener() {
        captureBtn.setOnClickListener(this);
        switchCameraBtn.setOnClickListener(this);
        cameraView.setCameraListener(new CameraListener());
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraView.start();
    }

    @Override
    protected void onPause() {
        cameraView.stop();
        super.onPause();
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.captureImage) {
//            cameraView.captureImage();
            cameraView.startRecordingVideo();
            cameraView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    cameraView.stopRecordingVideo();
                }
            }, 1 * 60 * 1000);
        } else if (i == R.id.switchCamera) {
            cameraView.toggleFacing();
        }
    }

    private class CameraListener extends com.flurgle.camerakit.CameraListener {

        public CameraListener() {
            super();
        }

        @Override
        public void onCameraOpened() {
            Log.i(getLocalClassName(), "onCameraOpened");
        }

        @Override
        public void onCameraClosed() {
            Log.i(getLocalClassName(), "onCameraClosed");
        }

        @Override
        public void onPictureTaken(byte[] jpeg) {
            Log.i(getLocalClassName(), "onPictureTaken");
            try {
                String file = new File(Environment.getExternalStorageDirectory() + "/Android/data/" + getPackageName() + "/files/", "image.jpg").getPath();
                writeFile(jpeg, file);
                Intent intent = new Intent();
                intent.putExtra(DATA, file);
                setResult(RESULT_OK, intent);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            Bitmap bitmap = BitmapFactory.decodeByteArray(jpeg, 0, jpeg.length);
        }

        @Override
        public void onPictureTaken(YuvImage yuv) {
            Log.i(getLocalClassName(), "onPictureTaken");
        }

        @Override
        public void onVideoTaken(File video) {
            Log.i(getLocalClassName(), "onVideoTaken" + video.getPath());
        }
    }


    public void writeFile(byte[] data, String fileName) throws IOException {
        FileOutputStream out = new FileOutputStream(fileName);
        out.write(data);
        out.close();
    }


}
