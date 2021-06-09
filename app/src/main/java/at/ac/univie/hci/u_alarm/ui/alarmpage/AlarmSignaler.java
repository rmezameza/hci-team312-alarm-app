package at.ac.univie.hci.u_alarm.ui.alarmpage;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;

import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import at.ac.univie.hci.u_alarm.MainActivity;
import at.ac.univie.hci.u_alarm.R;

// Could change class name

public class AlarmSignaler {
    Context alarmContext;
    int millVibrations;
    int ampVibrations;
    int vibrationCycles;
    int milliFlashesSleep;
    int milliCyclesSleep;
    boolean shouldContinue;

    public AlarmSignaler(Context context,
                         int millisecondsVibration,
                         int amplitudeVibration,
                         int vibrationCycles,
                         int milliseconds_sleep_between_flashes,
                         int milliseconds_sleep_between_cycles) {
        this.alarmContext = context;
        this.millVibrations = millisecondsVibration;
        this.ampVibrations = amplitudeVibration;
        this.vibrationCycles = vibrationCycles;
        this.milliFlashesSleep = milliseconds_sleep_between_flashes;
        this.milliCyclesSleep = milliseconds_sleep_between_cycles;
        this.shouldContinue = true;

    }

    // Not elegant but good enough and more performant
    // than waiting for automatic thread kill, easier
    // than using thread.interrupt() in AlarmActivity
    public void stopAlarm(){
        this.shouldContinue=false;
    }

    public void startAlarm(){
        // Audio, creating MediaPlayer with the hardcoded alarm sound and starting it.
        // Gets ended at the tail end of this method.
        // 400hz, alternatively test with "alertsoundonekhz"
        final MediaPlayer AlarmPlayer =
                MediaPlayer.create(this.alarmContext, R.raw.alertstoundfourfourtyhz);

        AlarmPlayer.start();


        //Notification. Using Compat Versions to allow Builder patterns and compatibility to
        // older android versions (although not necessary for this project)
        Intent alarmIntent=new Intent(this.alarmContext, MainActivity.class);
        // First flag needed according to PendingIntent doc, second flag according to the
        // notification documentation.
        alarmIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Change Context to navigate to List of past Alarms instead of the home fragment.
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this.alarmContext, 0, alarmIntent, 0);

        // Should the Notification be built with Info from the AlarmPage or just a boilerplate Text?
        // If yes, delegate to AlarmActivity.
        NotificationChannelCompat.Builder alarmChannelBuilder =
                new NotificationChannelCompat.Builder("122",NotificationManagerCompat.IMPORTANCE_DEFAULT)
                        .setDescription("AlarmChannelCompat for alarm notifications")
                        .setLightColor(Color.parseColor("lime"))
                        .setName("AlarmCompatChannel test")
                        .setLightsEnabled(true);


        String textTitle=" Alarm ";
        String textContent="Immediately head to the nearest exit. ";

        NotificationCompat.Builder alarmNotificationBuilder =
                new NotificationCompat.Builder(this.alarmContext.getApplicationContext(), "122")
                        .setSmallIcon(R.drawable.alarm_icon)//should use Icon depending on selected theme.
                        .setContentTitle(textTitle)
                        .setContentText(textContent)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)
                        //Lime colour. Only relevant for lower Android versions, normally overruled by the
                        // channel. Channel sadly cannot set blinking frequency.
                        .setLights(0xff00ff00,500,100)
                        .setAutoCancel(true);

        NotificationManagerCompat alarmNotificationManager = NotificationManagerCompat.from(this.alarmContext);
        alarmNotificationManager.createNotificationChannel(alarmChannelBuilder.build());
        alarmNotificationBuilder.setChannelId("122");
        alarmNotificationManager.notify(0,alarmNotificationBuilder.build());

        // Camera flash and vibration
        // Turning flash on/off appears to incur some overhead, so duration of vibration and
        // pause between camera on/off can't simply be set to equal. Could be fixed by threading
        // but is sufficiently performant this way.
        // WARNING: Trying this out on an emulator WILL throw exceptions. Exception will be
        // catch and exception information will be wrote in log. Nevertheless the app continues.
        // They are currently suppressed and annotated by messages in the Debug Log.
        // Checking whether the App runs in an Emulator is rather cumbersome.
        Vibrator alarmVibrator = (Vibrator)alarmContext.getSystemService(Context.VIBRATOR_SERVICE);
        CameraManager flashCameraManager = (CameraManager)alarmContext.getSystemService(Context.CAMERA_SERVICE);
        String rearCamID = null;

        try {
            rearCamID = flashCameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            //e.printStackTrace();
            Log.d("startAlarm","Exception on getting CamerIdList, probably running on Emulator");
        }
        while(shouldContinue){
            alarmVibrator.vibrate(VibrationEffect.createOneShot(millVibrations,ampVibrations));
            try {
                flashCameraManager.setTorchMode(rearCamID, true);
            } catch (CameraAccessException | IllegalArgumentException e) {
                //e.printStackTrace();
                Log.d("startAlarm","Exception on enabling main camera flash, probably running on Emulator");
            }
            SystemClock.sleep(milliFlashesSleep);
            try {
                flashCameraManager.setTorchMode(rearCamID, false);
            } catch (CameraAccessException | IllegalArgumentException e) {
                //e.printStackTrace();
                Log.d("startAlarm","Exception on disabling main camera flash, probably running on Emulator");
            }
            SystemClock.sleep(milliCyclesSleep);
        }
        //Frees the MediaPlayer and associated resources, more elegant to call here than on extra
        // Function to avoid passing or creating every Alarmer with its own MediaPlayer
        AlarmPlayer.release();
    }
}