package com.ubs.opsit.interviews;

import com.ubs.opsit.interviews.controller.ClockHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BerlinClockTimeConverter implements TimeConverter {
    private static final Logger LOG = LoggerFactory.getLogger(BerlinClockTimeConverter.class);
    private static final String TIME_PATTERN = "\\d\\d:\\d\\d:\\d\\d";

    private ClockHandler clockHandler;

    public BerlinClockTimeConverter(ClockHandler clockHandler) {
        this.clockHandler = clockHandler;
    }

    @Override
    public String convertTime(String aTime) {
        if (aTime == null || !aTime.matches(TIME_PATTERN)) {
            LOG.error("Unable to convert. Incorrect time = " + aTime);
            return null;
        }
        String[] stringTime = aTime.split(":");
        int hours = Integer.valueOf(stringTime[0]);
        int minutes = Integer.valueOf(stringTime[1]);
        int seconds = Integer.valueOf(stringTime[2]);

        clockHandler.updateClockTime(hours, minutes, seconds);

        return clockHandler.showClockTime();
    }
}
