package com.camera;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;

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
 * Created by amit on 25/8/17.
 */

public class Param implements Parcelable {

    int facing = DEFAULT_FACING;
    int flash = DEFAULT_FLASH;
    int focus = DEFAULT_FOCUS;
    int method = DEFAULT_METHOD;
    int zoom = DEFAULT_ZOOM;
    int permissions = DEFAULT_PERMISSIONS;
    int jpegQuality = DEFAULT_JPEG_QUALITY;
    int videoQuality = DEFAULT_VIDEO_QUALITY;
    boolean cropOutput = DEFAULT_CROP_OUTPUT;
    boolean adjustViewBound = DEFAULT_ADJUST_VIEW_BOUNDS;
    Activity activity;

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

    public Activity getActivity() {
        return activity;
    }

    public void setFacing(int facing) {
        this.facing = facing;
    }

    public void setFlash(int flash) {
        this.flash = flash;
    }

    public void setFocus(int focus) {
        this.focus = focus;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public void setPermissions(int permissions) {
        this.permissions = permissions;
    }

    public void setJpegQuality(int jpegQuality) {
        this.jpegQuality = jpegQuality;
    }

    public void setVideoQuality(int videoQuality) {
        this.videoQuality = videoQuality;
    }

    public void setCropOutput(boolean cropOutput) {
        this.cropOutput = cropOutput;
    }

    public void setAdjustViewBound(boolean adjustViewBound) {
        this.adjustViewBound = adjustViewBound;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(facing);
        dest.writeInt(flash);
        dest.writeInt(focus);
        dest.writeInt(method);
        dest.writeInt(zoom);
        dest.writeInt(permissions);
        dest.writeInt(jpegQuality);
        dest.writeInt(videoQuality);
        dest.writeByte((byte) (cropOutput ? 0x01 : 0x00));
        dest.writeByte((byte) (adjustViewBound ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Creator<Param> CREATOR = new Creator<Param>() {
        @Override
        public Param createFromParcel(Parcel in) {
            Param param = new Param();
            param.facing = in.readInt();
            param.flash = in.readInt();
            param.focus = in.readInt();
            param.method = in.readInt();
            param.zoom = in.readInt();
            param.permissions = in.readInt();
            param.jpegQuality = in.readInt();
            param.videoQuality = in.readInt();
            param.cropOutput = in.readByte() != 0x00;
            param.adjustViewBound = in.readByte() != 0x00;
            return param;
        }

        @Override
        public Param[] newArray(int size) {
            return new Param[size];
        }
    };
}
