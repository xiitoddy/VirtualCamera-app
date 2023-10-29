package com.example.virtualcameraapp;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.app.PendingIntent;
import android.app.Notification;

public class VirtualCameraService extends Service {
    private VirtualCameraDriver virtualCameraDriver;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForegroundService();
        virtualCameraDriver = new VirtualCameraDriver(this);
        return START_STICKY;
    }

    private void startForegroundService() {
        Intent stopIntent = new Intent(this, VirtualCameraService.class);
        PendingIntent stopPendingIntent = PendingIntent.getService(this, 0, stopIntent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("Virtual Camera Active")
                .setContentText("Tap to stop the virtual camera.")
                .setSmallIcon(R.drawable.ic_camera)
                .addAction(R.drawable.ic_stop, "Stop", stopPendingIntent)
                .build();

        startForeground(1, notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    // ... other methods ...
}
