package com.example.virtualcameraapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log; // Added this import

public class VirtualCameraDriver {
    private Bitmap virtualImage;
    private Context context;

    public VirtualCameraDriver(Context context) {
        this.context = context;
        loadVirtualImageFromPreferences();
    }

    private void loadVirtualImageFromPreferences() {
        SharedPreferences prefs = context.getSharedPreferences("VirtualCameraPrefs", Context.MODE_PRIVATE);
        String imagePath = prefs.getString("selectedImagePath", null);
        if (imagePath != null) {
            try {
                virtualImage = BitmapFactory.decodeFile(imagePath);
            } catch (Exception e) {
                Log.e("VirtualCameraDriver", "Error loading virtual image: " + e.getMessage()); // Updated this line
            }
        }
    }

    public Bitmap getVirtualImage() {
        return virtualImage;
    }
}
