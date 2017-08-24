package com.camera;

import com.flurgle.camerakit.CameraKit;

/**
 * Created by amit on 24/8/17.
 */

public class Constants {

    public static final int FACING_BACK = CameraKit.Constants.FACING_BACK;
    public static final int FACING_FRONT = CameraKit.Constants.FACING_FRONT;

    public static final int FLASH_OFF = CameraKit.Constants.FLASH_OFF;
    public static final int FLASH_ON = CameraKit.Constants.FLASH_ON;
    public static final int FLASH_AUTO = CameraKit.Constants.FLASH_AUTO;

    public static final int FOCUS_OFF = CameraKit.Constants.FOCUS_OFF;
    public static final int FOCUS_CONTINUOUS = CameraKit.Constants.FOCUS_CONTINUOUS;
    public static final int FOCUS_TAP = CameraKit.Constants.FOCUS_TAP;
    public static final int FOCUS_TAP_WITH_MARKER = CameraKit.Constants.FOCUS_TAP_WITH_MARKER;

    public static final int ZOOM_OFF = CameraKit.Constants.ZOOM_OFF;
    public static final int ZOOM_PINCH = CameraKit.Constants.ZOOM_PINCH;

    public static final int METHOD_STANDARD = CameraKit.Constants.METHOD_STANDARD;
    public static final int METHOD_STILL = CameraKit.Constants.METHOD_STILL;
    public static final int METHOD_SPEED = CameraKit.Constants.METHOD_SPEED;

    public static final int PERMISSIONS_STRICT = CameraKit.Constants.PERMISSIONS_STRICT;
    public static final int PERMISSIONS_LAZY = CameraKit.Constants.PERMISSIONS_LAZY;
    public static final int PERMISSIONS_PICTURE = CameraKit.Constants.PERMISSIONS_PICTURE;

    public static final int VIDEO_QUALITY_480P = CameraKit.Constants.VIDEO_QUALITY_480P;
    public static final int VIDEO_QUALITY_720P = CameraKit.Constants.VIDEO_QUALITY_720P;
    public static final int VIDEO_QUALITY_1080P = CameraKit.Constants.VIDEO_QUALITY_1080P;
    public static final int VIDEO_QUALITY_2160P = CameraKit.Constants.VIDEO_QUALITY_2160P;
    public static final int VIDEO_QUALITY_HIGHEST = CameraKit.Constants.VIDEO_QUALITY_HIGHEST;
    public static final int VIDEO_QUALITY_LOWEST = CameraKit.Constants.VIDEO_QUALITY_LOWEST;



    static final int DEFAULT_FACING = CameraKit.Constants.FACING_BACK;
    static final int DEFAULT_FLASH = CameraKit.Constants.FLASH_OFF;
    static final int DEFAULT_FOCUS = CameraKit.Constants.FOCUS_CONTINUOUS;
    static final int DEFAULT_ZOOM = CameraKit.Constants.ZOOM_OFF;
    static final int DEFAULT_METHOD = CameraKit.Constants.METHOD_STANDARD;
    static final int DEFAULT_PERMISSIONS = CameraKit.Constants.PERMISSIONS_STRICT;
    static final int DEFAULT_VIDEO_QUALITY = CameraKit.Constants.VIDEO_QUALITY_480P;

    static final int DEFAULT_JPEG_QUALITY = 100;
    static final boolean DEFAULT_CROP_OUTPUT = false;
    static final boolean DEFAULT_ADJUST_VIEW_BOUNDS = false;
}
