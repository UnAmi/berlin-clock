package com.ubs.opsit.interviews;

import com.ubs.opsit.interviews.controller.ClockHandler;
import com.ubs.opsit.interviews.converter.BerlinClockTimeConverter;
import com.ubs.opsit.interviews.converter.TimeConverter;
import com.ubs.opsit.interviews.model.BerlinClock;
import com.ubs.opsit.interviews.controller.BerlinClockHandler;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;

import static com.ubs.opsit.interviews.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Acceptance test class that uses the JBehave (Gerkin) syntax for writing stories.  You should not need to
 * edit this class to complete the exercise, this is your definition of done.
 */
public class BerlinClockFixture {

    private TimeConverter berlinClock;
    private String theTime;

    public BerlinClockFixture() {
        ClockHandler clockHandler = new BerlinClockHandler(new BerlinClock());
        berlinClock = new BerlinClockTimeConverter(clockHandler);
    }

    @Test
    public void berlinClockAcceptanceTests() throws Exception {
        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("berlin-clock.story")
                .run();
    }

    @When("the time is $time")
    public void whenTheTimeIs(String time) {
        theTime = time;
    }

    @Then("the clock should look like $")
    public void thenTheClockShouldLookLike(String theExpectedBerlinClockOutput) {
        assertThat(berlinClock.convertTime(theTime)).isEqualTo(theExpectedBerlinClockOutput);
    }
}
