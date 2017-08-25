package com.camera;

import android.app.Activity;
import android.content.Intent;

import static com.camera.CaptureActivity.REQUEST_CODE;
import static com.camera.Constants.MODE_NONE;

/**
 * Created by amit on 24/8/17.
 */

public class CameraUtil {

    private Param param;

    private CameraUtil() {

    }

    public CameraUtil(Activity context) {
        param = new Param();
        param.activity = context;
    }

    public CameraUtil setFacing(int facing) {
        param.facing = facing;
        return this;
    }

    public CameraUtil setFlash(int flash) {
        param.flash = flash;
        return this;
    }

    public CameraUtil setFocus(int focus) {
        param.focus = focus;
        return this;
    }

    public CameraUtil setMethod(int method) {
        param.method = method;
        return this;
    }

    public CameraUtil setZoom(int zoom) {
        param.zoom = zoom;
        return this;
    }

    public CameraUtil setPermissions(int permissions) {
        param.permissions = permissions;
        return this;
    }


    public CameraUtil setCropOutput(boolean isCropOutput) {
        param.cropOutput = isCropOutput;
        return this;
    }


    public CameraUtil setJpegQuality(int jpegQuality) {
        param.jpegQuality = jpegQuality;
        return this;
    }

    public CameraUtil setVideoQuality(int videoQuality) {
        param.videoQuality = videoQuality;
        return this;
    }

    public CameraUtil setAdjustViewBound(boolean adjustViewBound) {
        param.adjustViewBound = adjustViewBound;
        return this;
    }

    public CameraUtil setMaxRecording(int second) {
        param.second = second;
        return this;
    }

    public void launch() {
        Intent intent = new Intent(param.activity, CaptureActivity.class);
        param.mode = MODE_NONE;
        intent.putExtra(CaptureActivity.CONFIGURATION, param);
        param.activity.startActivityForResult(intent, REQUEST_CODE);
    }

    public void launchCamera() {
        Intent intent = new Intent(param.activity, CaptureActivity.class);
        param.mode = Constants.MODE_CAMERA;
        intent.putExtra(CaptureActivity.CONFIGURATION, param);
        param.activity.startActivityForResult(intent, REQUEST_CODE);
    }

    public void launchVideo() {
        Intent intent = new Intent(param.activity, CaptureActivity.class);
        param.mode = Constants.MODE_VIDEO;
        intent.putExtra(CaptureActivity.CONFIGURATION, param);
        param.activity.startActivityForResult(intent, REQUEST_CODE);
    }
}
