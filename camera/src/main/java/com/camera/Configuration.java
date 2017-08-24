package com.camera;

import com.flurgle.camerakit.CameraKit;

import java.io.Serializable;

import static com.camera.Constants.DEFAULT_ADJUST_VIEW_BOUNDS;
import static com.camera.Constants.DEFAULT_CROP_OUTPUT;
import static com.camera.Constants.DEFAULT_FACING;
import static com.camera.Constants.DEFAULT_FLASH;
import static com.camera.Constants.DEFAULT_FOCUS;
import static com.camera.Constants.DEFAULT_JPEG_QUALITY;
import static com.camera.Constants.DEFAULT_METHOD;
import static com.camera.Constants.DEFAULT_PERMISSIONS;
import static com.camera.Constants.DEFAULT_VIDEO_QUALITY;
import static com.camera.Constants.DEFAULT_ZOOM;

/**
 * Created by amit on 24/8/17.
 */

public class Configuration implements Serializable {

    private int facing = DEFAULT_FACING, flash = DEFAULT_FLASH, focus = DEFAULT_FOCUS, method = DEFAULT_METHOD, zoom = DEFAULT_ZOOM, permissions = DEFAULT_PERMISSIONS, jpegQuality = DEFAULT_JPEG_QUALITY, videoQuality = DEFAULT_VIDEO_QUALITY;
    private boolean cropOutput = DEFAULT_CROP_OUTPUT, adjustViewBound = DEFAULT_ADJUST_VIEW_BOUNDS;

    public int getFacing() {
        return facing;
    }

    public int getFlash() {
        return flash;
    }

    public int getFocus() {
        return focus;
    }

    public int getMethod() {
        return method;
    }

    public int getZoom() {
        return zoom;
    }

    public int getPermissions() {
        return permissions;
    }

    public int getJpegQuality() {
        return jpegQuality;
    }

    public int getVideoQuality() {
        return videoQuality;
    }

    public boolean isCropOutput() {
        return cropOutput;
    }

    public boolean isAdjustViewBound() {
        return adjustViewBound;
    }

    public Configuration setFacing(int facing) {
        this.facing = facing;
        return this;
    }

    public Configuration setFlash(int flash) {
        this.flash = flash;
        return this;
    }

    public Configuration setFocus(int focus) {
        this.focus = focus;
        return this;
    }

    public Configuration setMethod(int method) {
        this.method = method;
        return this;
    }

    public Configuration setZoom(int zoom) {
        this.zoom = zoom;
        return this;
    }

    public Configuration setPermissions(int permissions) {
        this.permissions = permissions;
        return this;
    }


    public Configuration setCropOutput(boolean isCropOutput) {
        this.cropOutput = isCropOutput;
        return this;
    }


    public Configuration setJpegQuality(int jpegQuality) {
        this.jpegQuality = jpegQuality;
        return this;
    }

    public Configuration setVideoQuality(int videoQuality) {
        this.videoQuality = videoQuality;
        return this;
    }

    public void setAdjustViewBound(boolean adjustViewBound) {
        this.adjustViewBound = adjustViewBound;
    }
}
