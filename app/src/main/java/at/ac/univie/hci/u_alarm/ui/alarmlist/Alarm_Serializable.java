package at.ac.univie.hci.u_alarm.ui.alarmlist;

import java.io.Serializable;

public class Alarm_Serializable implements Serializable {
    String alarm_type;
    String alarm_location;
    String alarm_date;

    public Alarm_Serializable(String alarm_type, String alarm_location, String alarm_date) {
        this.alarm_type = alarm_type;
        this.alarm_location = alarm_location;
        this.alarm_date = alarm_date;
    }
}