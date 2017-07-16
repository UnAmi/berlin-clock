package com.ubs.opsit.interviews.controller.fx;


import com.ubs.opsit.interviews.controller.BerlinClockHandler;
import com.ubs.opsit.interviews.converter.BerlinClockTimeConverter;
import com.ubs.opsit.interviews.converter.TimeConverter;
import com.ubs.opsit.interviews.model.BerlinClock;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class MainController implements Initializable {

    private static final String TIME = "HH:mm:ss";

    @FXML
    private Circle secondsCircle;
    @FXML
    private HBox majorHoursLabels;
    @FXML
    private HBox minorHoursLabels;
    @FXML
    private HBox majorMinutesLabels;
    @FXML
    private HBox minorMinutesLabels;
    @FXML
    private Label digitalClock;

    private TimeConverter converter;
    private Map<String, Color> colorsMap = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        converter = new BerlinClockTimeConverter(new BerlinClockHandler(new BerlinClock()));
        colorsMap.put(BerlinClock.Color.RED.code(), Color.RED);
        colorsMap.put(BerlinClock.Color.YELLOW.code(), Color.YELLOW);
        colorsMap.put(BerlinClock.Color.OFF.code(), Color.GRAY);
        createTimeline();
    }

    private void createTimeline() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        actionEvent -> {
                            Calendar time = Calendar.getInstance();
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME);
                            String aTime = simpleDateFormat.format(time.getTime());
                            digitalClock.setText(aTime);
                            updateBerlinClock(aTime);
                        }
                ),
                new KeyFrame(Duration.ONE)
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void updateBerlinClock(String aTime) {
        String stringColors = converter.convertTime(aTime);
        String[] stringColorsLines = stringColors.split(System.lineSeparator());
        updateSecondsLine(stringColorsLines[BerlinClock.SECONDS]);
        updateLine(stringColorsLines[BerlinClock.HOURS_MAJOR], majorHoursLabels);
        updateLine(stringColorsLines[BerlinClock.HOURS_MINOR], minorHoursLabels);
        updateLine(stringColorsLines[BerlinClock.MINUTES_MAJOR], majorMinutesLabels);
        updateLine(stringColorsLines[BerlinClock.MINUTES_MINOR], minorMinutesLabels);
    }

    private void updateSecondsLine(String seconds) {
        List<Color> colors = stringCodeToColors(seconds);
        secondsCircle.setFill(colors.get(0));
    }

    private void updateLine(String stringColorLine, HBox hBox) {
        List<Color> colorList = stringCodeToColors(stringColorLine);
        ObservableList<Node> children = hBox.getChildren();
        for (int i = 0; i < colorList.size(); i++) {
            Rectangle rectangle = (Rectangle) children.get(i);
            rectangle.setFill(colorList.get(i));
        }
    }

    private List<Color> stringCodeToColors(String stringColorLine) {
        List<Color> colorList = new ArrayList<>();
        for (char sColor : stringColorLine.toCharArray()) {
            colorList.add(colorsMap.get(Character.toString(sColor)));
        }
        return colorList;
    }
}
