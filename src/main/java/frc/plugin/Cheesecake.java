package frc.plugin;

import edu.wpi.first.shuffleboard.api.data.MapData;
import edu.wpi.first.shuffleboard.api.prefs.Group;
import edu.wpi.first.shuffleboard.api.prefs.Setting;
import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.LinkedList;

@Description(dataTypes = {MapData.class}, name = "Cheesecake")
@ParametrizedController(value = "Cheesecake.fxml")
public class Cheesecake extends SimpleAnnotatedWidget<MapData> implements ChangeListener<MapData> {

    private final SimpleStringProperty robotLengthProperty = new SimpleStringProperty(this, "robotLength", "");
    private final SimpleStringProperty robotWidthProperty = new SimpleStringProperty(this, "robotWidth", "");
    private final SimpleStringProperty encoderValProperty = new SimpleStringProperty(this, "encoderVal", "");
    private final SimpleStringProperty gyroAngleProperty = new SimpleStringProperty(this, "gyroAngle", "");

    /*TODO: Find out how the next two lines cause the code to break*/
//    private final double encoderVal = (double) dataProperty().get().get("encoderVal");
//    private final double gyroAngle = (double) dataProperty().get().get("gyroAngle");
    private double prevEncoderVal;
    private double[] coordinates = new double[2];
    final double INCH_TO_PIXEL = 1.518714;
    final double PIXEL_TO_INCH = 0.658452;

    @FXML
    AnchorPane mapPane;
    @FXML
    Canvas mapLayer;
//    @FXML
//    ChoiceBox<StartingPos> chooseRobotStartPos;

    @FXML
    ChoiceBox<String> chooseRobotStartPos;

    private double[][] drawRobot(GraphicsContext gc, double x, double y, double angle) {
        double[][] val = new double[2][4];
        double length = (double) dataProperty().get().get("robotLength");
        double width = (double) dataProperty().get().get("robotWidth");
        val[0][0] = x;                  val[1][0] = y;
        val[0][1] = x - length;         val[1][1] = y + width / 2;
        val[0][2] = x - length * 3 / 4; val[1][2] = y;
        val[0][3] = x - length;         val[1][3] = y - width / 2;

        gc.fillPolygon(val[0], val[1], 4);
        gc.rotate(angle);
        return val;
    }

    @Override
    public java.util.List<Group> getSettings() {
        LinkedList<Group> propertyList = new LinkedList<>();

        propertyList.add(
            Group.of(
                "Map Key Values",
                Setting.of("Robot Length", robotLengthProperty, String.class),
                Setting.of("Robot Width", robotWidthProperty, String.class),
                Setting.of("Encoder Values", encoderValProperty, String.class),
                Setting.of("Gyro", gyroAngleProperty, String.class)
            )
        );

        return propertyList;
    }

    private void setCoordinates() {
        double encoderVal = (double) dataProperty().get().get("encoderVal");
        double gyroAngle = (double) dataProperty().get().get("gyroAngle");
        double x = (encoderVal - prevEncoderVal) * Math.cos(gyroAngle);
        double y = (encoderVal - prevEncoderVal) * Math.sin(gyroAngle);
        prevEncoderVal = encoderVal;
        coordinates[0] += x;
        coordinates[1] += y;
    }

    @Override
    public Pane getView() {
        Image fieldMap = new Image(getClass().getResourceAsStream("2019-FieldMap.png"));
        createChoiceBox();
        GraphicsContext gc = mapLayer.getGraphicsContext2D();
        gc.drawImage(fieldMap, 0, 0);
        gc.setFill(Color.RED);
//        drawRobot(gc, x.get(), y.get(), gyroAngleProperty.get());
        return mapPane;
    }

//    public void createChoiceBox() {
//        chooseRobotStartPos = new ChoiceBox<>();
//        chooseRobotStartPos.setItems(FXCollections.observableArrayList(
//                StartingPos.LEFT_CS, StartingPos.MID, StartingPos.RIGHT_CS
//        ));
//        TODO: Replace this with lambda :D
//        chooseRobotStartPos.getSelectionModel().selectedItemProperty()
//                .addListener(new ChangeListener<>() {
//                    @Override
//                    public void changed(ObservableValue<? extends StartingPos> observableValue, StartingPos startingPos, StartingPos t1) {
//                        drawRobot(mapLayer.getGraphicsContext2D(), t1.x, t1.y, 0);
//                    }
//                });
//        chooseRobotStartPos.setValue(StartingPos.RIGHT_CS);
//    }
//
    private void createChoiceBox() {
        chooseRobotStartPos = new ChoiceBox<>(FXCollections.observableArrayList("Right Cargo Ship", "Left Cargo Ship", "Middle"));
    }

    @Override
    public void changed(ObservableValue<? extends MapData> arg0, MapData arg1, MapData arg2) {
        setCoordinates();
        double angle = (double) dataProperty().get().get("gyroAngle");
        drawRobot(mapLayer.getGraphicsContext2D(), coordinates[0] * INCH_TO_PIXEL, coordinates[1] * INCH_TO_PIXEL, angle);
    }

}
