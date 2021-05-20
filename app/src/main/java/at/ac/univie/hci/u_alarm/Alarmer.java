package at.ac.univie.hci.u_alarm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
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
    Context alarmcontext;
    int mill_vibrations;
    int amp_vibrations;
    int vibcycles;
    int milli_flashes_sleep;
    int milli_cycle_sleep;
    boolean shouldContinue;

    public Alarmer(Context context, int milliseconds_vibration, int amplitude_vibration, int vibration_cycles, int milliseconds_sleep_between_flashes, int milliseconds_sleep_between_cycles){
        alarmcontext=context;
        mill_vibrations=milliseconds_vibration;
        amp_vibrations=amplitude_vibration;
        vibcycles=vibration_cycles;
        milli_flashes_sleep=milliseconds_sleep_between_flashes;
        milli_cycle_sleep=milliseconds_sleep_between_cycles;
        shouldContinue=true;

    }
    //Das ist jetzt sicher nicht elegant, mir gerade aber egal und schneller als einfach darauf zu warten, dass der Thread automatisch gekillt wird.
    public void stop_alarm(){
        this.shouldContinue=false;
    }
    public void start_alarm(){
    //Log.d("Boolean shouldContinue=",Boolean.toString(this.shouldContinue));
        //Kram fuer die Notification.
        //Fuer den Channel noch auf NotifcationChannelCompat umsteigen, der braucht einen extra Builder. Vorteil Compat vs "normal" ist backwards compatibility.
        //NotificationLED funktioniert bei mir noch nicht, keine Ahnung wieso. Liegt wahrscheinlich daran, dass ich das Telefon gleichzeitig ueber das USB Kabel lade während ich die App ausführe und deshalb die Notification LED eher das Laden anzeigt als die eingestellte Farbe.
            NotificationChannel channel = new NotificationChannel("111", "Testchannel", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Test for AlarmerChannel");
            channel.setLightColor(Color.parseColor("lime"));
            channel.enableLights(true);



            //Funktioniert an dieser Stelle nur mit NotificationManage, nicht mit der Compat-Variante, sehr interessant.
            //NotificationManager notificationManager = alarmcontext.getSystemService(NotificationManager.class);
            //notificationManager.createNotificationChannel(channel);


            Intent alarm_intent=new Intent(this.alarmcontext, MainActivity.class);
            alarm_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            //Hier noch den Context aendern um auf den AlarmScreen anstatt zum HomeFragment zu navigieren.
            PendingIntent pendingIntent = PendingIntent.getActivity(this.alarmcontext, 0, alarm_intent, 0);

            String textTitle="u:alert Test notification";
            String textContent="u:alert Test notification text";

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this.alarmcontext.getApplicationContext(), "111")
                    .setSmallIcon(R.drawable.alarm_icon) //Derzeit nocht einfach das Icon aus der Navigation Bar gestohlen.
                    .setContentTitle(textTitle)
                    .setContentText(textContent)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setLights(0xff00ff00,50,50) //bei niedrigeren Android-Versionen relevant, sonst ueber den Channel geregelt.
                    .setAutoCancel(true);

            NotificationManagerCompat notifmgr=NotificationManagerCompat.from(this.alarmcontext);
            notifmgr.createNotificationChannel(channel);
            builder.setChannelId("111");
            notifmgr.notify(0,builder.build());



    //Licht und Kamera

        Vibrator vibrator=(Vibrator)alarmcontext.getSystemService(Context.VIBRATOR_SERVICE);
        CameraManager flashCameraManager=(CameraManager)alarmcontext.getSystemService(Context.CAMERA_SERVICE);
        String camID=null;
        try {
            camID = flashCameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        //for(int j=0;j<vibcycles;++j){
        while(this.shouldContinue==true){
            vibrator.vibrate(VibrationEffect.createOneShot(mill_vibrations,amp_vibrations));
            try {
                flashCameraManager.setTorchMode(camID, true);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
            SystemClock.sleep(milli_flashes_sleep);
            try {
                flashCameraManager.setTorchMode(camID, false);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
            SystemClock.sleep(milli_cycle_sleep);
        }
    }


};
//Ein- und Ausschalten des Blitzes dürfte etwas overhead haben, deshalb lassen sich Pause zwischen den Blitzen*Iterationen und Dauer der Vibration nicht direkt gleichsetzen.
//Das ist nur ein Test der Muster, einfach denken dass es while loops wären die abbrechen wenn ein Knopf gedrückt wird.

//Getestete Patterns:
/*
    //"Normal"/Slow Alarm.
        for(int j=0;j<10;++j){
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
        for(int j=0;j<10;++j){
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
        for(int j=0;j<10;++j){
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
