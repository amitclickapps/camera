package com.camera;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by amit on 24/8/17.
 */

public class CameraUtil {

    private Param param;
//    public Activity getActivity() {
//        return activity;
//    }
//
//    public int getFacing() {
//        return facing;
//    }
//
//    public int getFlash() {
//        return flash;
//    }
//
//    public int getFocus() {
//        return focus;
//    }
//
//    public int getMethod() {
//        return method;
//    }
//
//    public int getZoom() {
//        return zoom;
//    }
//
//    public int getPermissions() {
//        return permissions;
//    }
//
//    public int getJpegQuality() {
//        return jpegQuality;
//    }
//
//    public int getVideoQuality() {
//        return videoQuality;
//    }
//
//    public boolean isCropOutput() {
//        return cropOutput;
//    }
//
//    public boolean isAdjustViewBound() {
//        return adjustViewBound;
//    }

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

    public void setAdjustViewBound(boolean adjustViewBound) {
        param.adjustViewBound = adjustViewBound;
    }

    public void launchCamera() {
        Intent intent = new Intent(param.activity, CaptureActivity.class);
        intent.putExtra(CaptureActivity.CONFIGURATION, param);
        param.activity.startActivity(intent);
    }

    public void launchVideo() {
        Intent intent = new Intent(param.activity, CaptureActivity.class);
        intent.putExtra(CaptureActivity.CONFIGURATION, param);
        param.activity.startActivity(intent);
    }
}
