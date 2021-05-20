package at.ac.univie.hci.u_alarm.ui.alarmlist;

import java.io.Serializable;

public class Alarm implements Serializable {
    String alarmtype;
    String alarmort;
    String alarmdate;

    public Alarm(String alarmtype, String alarmort, String alarmdate) {
        this.alarmtype = alarmtype;
        this.alarmort = alarmort;
        this.alarmdate = alarmdate;
    }
}
