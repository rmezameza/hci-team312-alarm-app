package at.ac.univie.hci.u_alarm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
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

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

//Todo: Einzelne Alarmoptionen in enums o.Ä speichern um die Verbindung mit den Einstellungen zu ermöglichen.
//Klassenname sicher noch ausbaufähig
//Emulatoren werfen exceptions wenn versucht wird die Kamera und/oder die Vibration anzusprechen. Entweder die Teile in start_alarm() zum Testen der Notification auskommentieren oder am physischen Gerät testen.
public class Alarmer{
    //public boolean shouldContinue; AIDS
    Context alarmContext;
    int millVibrations;
    int ampVibrations;
    int vibrationCycles;
    int milliFlashesSleep;
    int milliCyclesSleep;
    boolean shouldContinue;

    public Alarmer(Context context, int milliseconds_vibration, int amplitude_vibration, int vibration_cycles, int milliseconds_sleep_between_flashes, int milliseconds_sleep_between_cycles){
        alarmContext=context;
        millVibrations=milliseconds_vibration;
        ampVibrations=amplitude_vibration;
        vibrationCycles =vibration_cycles;
        milliFlashesSleep =milliseconds_sleep_between_flashes;
        milliCyclesSleep =milliseconds_sleep_between_cycles;
        shouldContinue=true;

    }
    //Das ist jetzt sicher nicht elegant, mir gerade aber egal und schneller als einfach darauf zu warten, dass der Thread automatisch gekillt wird.
    public void stopAlarm(){
        this.shouldContinue=false;
    }
    public void startAlarm(){

        //Audio, creating MediaPlayer with the hardcoded alarm sound and starting it. Gets ended at the end Tail of this method.
        final MediaPlayer AlarmPlayer=MediaPlayer.create(this.alarmContext,R.raw.mytesttone);
        AlarmPlayer.start();


        //Kram fuer die Notification.
        //Fuer den Channel noch auf NotifcationChannelCompat umsteigen, der braucht einen extra Builder. Vorteil Compat vs "normal" ist backwards compatibility.
        //NotificationLED funktioniert bei mir noch nicht, keine Ahnung wieso. Liegt wahrscheinlich daran, dass ich das Telefon gleichzeitig ueber das USB Kabel lade während ich die App ausführe und deshalb die Notification LED eher das Laden anzeigt als die eingestellte Farbe.

        NotificationChannel alarmChannel = new NotificationChannel("122", "Testchannel", NotificationManager.IMPORTANCE_DEFAULT);
            alarmChannel.setDescription("AlarmChannel description");
            alarmChannel.setLightColor(Color.parseColor("lime"));
            alarmChannel.enableLights(true);
        /*
        //Compat Alarm Channel, cannot import
        NotificationChannelCompat.Builder alarmChannelBuilder= new NotificationChannelCompat.Builder("122",NotificationManagerCompat.IMPORTANCE_DEFAULT)
                .setDescription("AlarmChannelCompat for alarm notifications")
                .setLightColor(Color.parseColor("lime"))
                .setLightEnabled(true);
    */


            Intent alarmIntent=new Intent(this.alarmContext, MainActivity.class);
            alarmIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //Possibly dont need flags, test TODO

            //Todo: Change Context to navigate to List of past Alarms instead of the home fragment.
            PendingIntent pendingIntent = PendingIntent.getActivity(this.alarmContext, 0, alarmIntent, 0);

                //TODO: Should the Notification be built with Info from the AlarmPage or just a boilerplate Text? If yes, delegate to AlarmActivity.
            String textTitle="u:alert Test notification";
            String textContent="u:alert Test notification text";

            NotificationCompat.Builder alarmNotificationBuilder = new NotificationCompat.Builder(this.alarmContext.getApplicationContext(), "122")
                    .setSmallIcon(R.drawable.alarm_icon) //Derzeit nocht einfach das Icon aus der Navigation Bar gestohlen.
                    .setContentTitle(textTitle)
                    .setContentText(textContent)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setLights(0xff00ff00,500,100) //bei niedrigeren Android-Versionen relevant, sonst ueber den Channel geregelt.
                    .setAutoCancel(true);

            NotificationManagerCompat alarmNotificationManager=NotificationManagerCompat.from(this.alarmContext);
            //alarmNotificationManager.createNotificationChannel(alarmChannelBuilder.build()); for CompatChannel
            alarmNotificationManager.createNotificationChannel(alarmChannel);
            alarmNotificationBuilder.setChannelId("122"); //kann wsl weg
            alarmNotificationManager.notify(0,alarmNotificationBuilder.build());



        //Camera flash and vibration
        //Turning flash on/off appears to incur some overhead, so duration of vibration and pause between
        //camera on/off can't simply be set to equal. Could be fixed by threading but is sufficiently performant this way.


        Vibrator alarmVibrator=(Vibrator)alarmContext.getSystemService(Context.VIBRATOR_SERVICE);
        CameraManager flashCameraManager=(CameraManager)alarmContext.getSystemService(Context.CAMERA_SERVICE);
        String rearCamID=null;
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
            } catch (CameraAccessException e) {
                //e.printStackTrace();
                Log.d("startAlarm","Exception on enabling main camera flash, probably running on Emulator");
            }
            SystemClock.sleep(milliFlashesSleep);
            try {
                flashCameraManager.setTorchMode(rearCamID, false);
            } catch (CameraAccessException e) {
                //e.printStackTrace();
                Log.d("startAlarm","Exception on disabling main camera flash, probably running on Emulator");
            }
            SystemClock.sleep(milliCyclesSleep);
        }
        //Frees the MediaPlayer and associated ressources, more elegant to call here than on extra Function to avoid passing or creating every Alamer with its own MediaPlayer
         AlarmPlayer.release();
    }
}

//Tested Patterns, using fixed cycles instead of permanent vibration for convenience.
/*
    //"Normal"/Slow Alarm.
        while(shouldContinue==true){
            vibrator.vibrate(VibrationEffect.createOneShot(1000,255));
            //for(int i=0;i<5;++i){
                try {
                    flashCameraManager.setTorchMode(camID, true);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
                SystemClock.sleep(1000);
                try {
                    flashCameraManager.setTorchMode(camID, false);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            //}
            SystemClock.sleep(500);
        }

    */

        /*
        //Schnellerer Alarm
        while(shouldContinue==true){
            vibrator.vibrate(VibrationEffect.createOneShot(500,255));
            //for(int i=0;i<5;++i){
            try {
                flashCameraManager.setTorchMode(camID, true);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
            SystemClock.sleep(500);
            try {
                flashCameraManager.setTorchMode(camID, false);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
            //}
            SystemClock.sleep(10);
        }
*/
//Disco
        /*
        while(shouldContinue==true){
            vibrator.vibrate(VibrationEffect.createOneShot(500,255));
            for(int i=0;i<100;++i){
            try {
                flashCameraManager.setTorchMode(camID, true);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
            SystemClock.sleep(1);
            try {
                flashCameraManager.setTorchMode(camID, false);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
            }
            SystemClock.sleep(20);
        }
        */
