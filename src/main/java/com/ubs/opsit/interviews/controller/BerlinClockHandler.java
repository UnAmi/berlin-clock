package com.ubs.opsit.interviews.controller;

import com.ubs.opsit.interviews.model.BerlinClock;

import static com.ubs.opsit.interviews.model.BerlinClock.Color;
import static com.ubs.opsit.interviews.model.BerlinClock.HOURS_MAJOR;
import static com.ubs.opsit.interviews.model.BerlinClock.HOURS_MINOR;
import static com.ubs.opsit.interviews.model.BerlinClock.MINUTES_MAJOR;
import static com.ubs.opsit.interviews.model.BerlinClock.MINUTES_MINOR;
import static com.ubs.opsit.interviews.model.BerlinClock.SECONDS;

public class BerlinClockHandler implements ClockHandler {

    private BerlinClock clock;

    public BerlinClockHandler(BerlinClock clock) {
        this.clock = clock;
        init();
    }

    @Override
    public void updateClockTime(int hours, int minutes, int seconds) {
        convertAndUpdateSeconds(seconds);
        convertAndUpdateMinutes(minutes);
        convertAndUpdateHours(hours);
    }

    @Override
    public String showClockTime() {
        return clock.toString();
    }

    private void init() {
        turnOffSecondsLamps();
        turnOffMinutesLamps();
        turnOffHoursLamps();
    }

    private void turnOffSecondsLamps() {
        offLine(SECONDS);
    }

    private void turnOffMinutesLamps() {
        offLine(MINUTES_MAJOR);
        offLine(MINUTES_MINOR);
    }

    private void turnOffHoursLamps() {
        offLine(HOURS_MAJOR);
        offLine(HOURS_MINOR);
    }

    private void convertAndUpdateHours(int hours) {
        turnOffHoursLamps();
        String[] hoursMajorLamps = clock.getLines().get(HOURS_MAJOR);
        String[] hoursMinorLamps = clock.getLines().get(HOURS_MINOR);

        for (int i = 0; i < hours / 5; i++) {
            hoursMajorLamps[i] = BerlinClock.Color.RED.code();
        }

        int minorLineHours = hours % 5;

        if (minorLineHours != 0) {
            for (int i = 0; i < minorLineHours; i++) {
                hoursMinorLamps[i] = BerlinClock.Color.RED.code();
            }
        }
    }

    private void convertAndUpdateMinutes(int minutes) {
        turnOffMinutesLamps();
        String[] minutesMinorLamps = clock.getLines().get(BerlinClock.MINUTES_MINOR);
        String[] minutesMajorLamps = clock.getLines().get(BerlinClock.MINUTES_MAJOR);
        for (int i = 0; i < minutes / 5; i++) {
            if ((i + 1) % 3 == 0 && i != 0) {
                minutesMajorLamps[i] = BerlinClock.Color.RED.code();
            } else {
                minutesMajorLamps[i] = BerlinClock.Color.YELLOW.code();
            }
        }
        int minorMinutes = minutes % 5;

        if (minorMinutes != 0) {
            for (int i = 0; i < minorMinutes; i++) {
                minutesMinorLamps[i] = BerlinClock.Color.YELLOW.code();
            }
        }
    }

    private void convertAndUpdateSeconds(int seconds) {
        turnOffSecondsLamps();
        String[] secondsLamp = clock.getLines().get(BerlinClock.SECONDS);
        if (seconds % 2 == 0) {
            secondsLamp[0] = BerlinClock.Color.YELLOW.code();
        }
    }

    private void offLine(int value) {
        String[] lampsLine = clock.getLines().get(value);
        for (int i = 0; i < lampsLine.length; i++) {
            lampsLine[i] = Color.OFF.code();
        }
    }
}
