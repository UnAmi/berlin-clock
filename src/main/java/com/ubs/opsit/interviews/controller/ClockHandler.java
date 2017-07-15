package com.ubs.opsit.interviews.controller;

public interface ClockHandler {
    /**
     * Update Clock
     * @param hours hours
     * @param minutes minutes
     * @param seconds seconds
     */
    void updateClockTime(int hours, int minutes, int seconds);

    /**
     * Show Clock time
     * @return String representation
     */
    String showClockTime();
}
