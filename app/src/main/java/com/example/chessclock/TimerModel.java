package com.example.chessclock;

import androidx.lifecycle.ViewModel;

public class TimerModel {

    int id;
    int clockValue;

    public TimerModel(int id, int clockValue) {
        this.id = id;
        this.clockValue = clockValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClockValue() {
        return clockValue;
    }

    public void setClockValue(int clockValue) {
        this.clockValue = clockValue;
    }
}
