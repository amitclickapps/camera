package com.camera;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.YuvImage;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flurgle.camerakit.CameraView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by amit on 24/8/17.
 */

public class CaptureActivity extends AppCompatActivity implements View.OnClickListener {
    private CameraView cameraView;
    private ImageView captureBtn, switchCameraBtn, galleryBtn, videoBtn;
    private TextView timerTxt;
    public static String CONFIGURATION = "CONFIGURATION";
    public static String DATA = "DATA";
    private final int ACTIVITY_SELECT_IMAGE = 1234;
    static final int TYPE = 0;
    private boolean isRecording = false;
    private Param configuration = null;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        setResult(RESULT_CANCELED);
        cameraView = (CameraView) findViewById(R.id.camera);
        captureBtn = (ImageView) findViewById(R.id.captureImage);
        switchCameraBtn = (ImageView) findViewById(R.id.switchCamera);
        galleryBtn = (ImageView) findViewById(R.id.gallery);
        videoBtn = (ImageView) findViewById(R.id.vedio);
        timerTxt = (TextView) findViewById(R.id.timer);
        Intent intent = getIntent();
        configuration = intent.getParcelableExtra(CONFIGURATION);
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
        captureBtn.setVisibility(View.INVISIBLE);
        videoBtn.setVisibility(View.INVISIBLE);
        timerTxt.setVisibility(View.INVISIBLE);
        if (configuration.getMode() == Constants.MODE_CAMERA) {
            captureBtn.setVisibility(View.VISIBLE);
        } else if (configuration.getMode() == Constants.MODE_VIDEO) {
            timerTxt.setVisibility(View.VISIBLE);
            videoBtn.setVisibility(View.VISIBLE);
        } else {
            captureBtn.setVisibility(View.VISIBLE);
            videoBtn.setVisibility(View.VISIBLE);
        }
    }

    private void clickListener() {
        captureBtn.setOnClickListener(this);
        switchCameraBtn.setOnClickListener(this);
        galleryBtn.setOnClickListener(this);
        videoBtn.setOnClickListener(this);
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
            timerTxt.setVisibility(View.INVISIBLE);
            videoBtn.setVisibility(View.INVISIBLE);
            cameraView.captureImage();
        } else if (i == R.id.switchCamera) {
            cameraView.toggleFacing();
        } else if (i == android.R.id.background) {
            cameraView.setFocus(Constants.FOCUS_TAP_WITH_MARKER);
        } else if (i == R.id.gallery) {
            Intent intent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(intent, ACTIVITY_SELECT_IMAGE);
        } else if (i == R.id.vedio) {
            timerTxt.setVisibility(View.VISIBLE);
            captureBtn.setVisibility(View.INVISIBLE);
            if (!isRecording) {
                isRecording = true;
                switchCameraBtn.setVisibility(View.INVISIBLE);
                videoBtn.setImageResource(R.drawable.ic_video_stop);
                cameraView.startRecordingVideo();
                if (configuration.getSecond() > 0) {
                    countDownTimer = new CountDownTimer(configuration.getSecond() * 1000, 1000) {

                        @Override
                        public void onTick(long millisUntilFinished) {
                            long countDown = millisUntilFinished / 1000;
                            long hrs = TimeUnit.MILLISECONDS.toHours(millisUntilFinished) % 60;
                            String text;
                            if (hrs == 0) {
                                text = String.format(Locale.getDefault(), "%02d:%02d",
                                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60);
                            } else {
                                text = String.format(Locale.getDefault(), "%02d:%02d:%02d",
                                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished) % 60,
                                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60);
                            }
                            Log.i(getLocalClassName(), "countDown = " + countDown);
                            timerTxt.setText(text);
                        }

                        @Override
                        public void onFinish() {
                            videoBtn.setClickable(true);
                            videoBtn.setImageResource(R.drawable.ic_vedio);
                            cameraView.stopRecordingVideo();
                        }
                    };
                    countDownTimer.start();
                }
            } else {
                isRecording = false;
                switchCameraBtn.setVisibility(View.VISIBLE);
                videoBtn.setImageResource(R.drawable.ic_vedio);
                cameraView.stopRecordingVideo();
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                }
                timerTxt.setText("");
            }


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ACTIVITY_SELECT_IMAGE:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    if (cursor != null) {
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String filePath = cursor.getString(columnIndex);
                        cursor.close();
                        Intent intent = new Intent();
                        intent.putExtra(DATA, filePath);
                        setResult(RESULT_OK, intent);
                    }
                }
        }
    }

    private class CameraListener extends com.flurgle.camerakit.CameraListener {

        public CameraListener() {
            super();
        }

        @Override
        public void onCameraOpened() {
            Log.i(getLocalClassName(), "onCameraOpened");
            captureBtn.setClickable(true);
            switchCameraBtn.setClickable(true);
            galleryBtn.setClickable(true);
            videoBtn.setClickable(true);
        }

        @Override
        public void onCameraClosed() {
            Log.i(getLocalClassName(), "onCameraClosed");
            captureBtn.setClickable(false);
            switchCameraBtn.setClickable(false);
            galleryBtn.setClickable(false);
            videoBtn.setClickable(false);
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

            if (configuration.getMode() == Constants.MODE_NONE) {
                captureBtn.setVisibility(View.VISIBLE);
                videoBtn.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPictureTaken(YuvImage yuv) {
            Log.i(getLocalClassName(), "onPictureTaken");
        }

        @Override
        public void onVideoTaken(File video) {
            Log.i(getLocalClassName(), "onVideoTaken" + video.getPath());
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            timerTxt.setText("");
            if (configuration.getMode() == Constants.MODE_NONE) {
                captureBtn.setVisibility(View.VISIBLE);
                videoBtn.setVisibility(View.VISIBLE);
            }
            switchCameraBtn.setVisibility(View.VISIBLE);
        }
    }


    public void writeFile(byte[] data, String fileName) throws IOException {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(fileName);
            out.write(data);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
