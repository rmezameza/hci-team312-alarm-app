package at.ac.univie.hci.u_alarm;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.SystemClock;
import android.os.VibrationEffect;
import android.os.Vibrator;

//Klassenname sicher noch ausbaufähig
public class Alarmer{
    Context alarmcontext;
    int mill_vibrations;
    int amp_vibrations;
    int vibcycles;
    int milli_flashes_sleep;
    int milli_cycle_sleep;

    Alarmer(Context context,int milliseconds_vibration,int amplitude_vibration,int vibration_cycles,int milliseconds_sleep_between_flashes,int milliseconds_sleep_between_cycles){
        alarmcontext=context;
        mill_vibrations=milliseconds_vibration;
        amp_vibrations=amplitude_vibration;
        vibcycles=vibration_cycles;
        milli_flashes_sleep=milliseconds_sleep_between_flashes;
        milli_cycle_sleep=milliseconds_sleep_between_cycles;

    }

    public void start_alarm(){
        Vibrator vibrator=(Vibrator)alarmcontext.getSystemService(Context.VIBRATOR_SERVICE);
        CameraManager flashCameraManager=(CameraManager)alarmcontext.getSystemService(Context.CAMERA_SERVICE);
        String camID=null;
        try {
            camID = flashCameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        for(int j=0;j<vibcycles;++j){
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
}
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
