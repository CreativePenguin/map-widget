# FRC-2019 MAP WIDGET CHEESECAKE

## Necessary Variables

Create a Network Table that includes the values for the enceer distance, gyro angle, original gyro angle, robot length, and robot width

Default code on Stuypulse 694 (Robot.java):

```java
//Global Variables
NetworkTable fieldMap;
NetworkTableEntry encoderDist;
NetworkTableEntry gyroAngle;
NetworkTableEntry origGyroAngle;
NetworkTableEntry robotLength;
NetworkTableEntry robotWidth;

@Override
public void robotInit() {
    fieldMap = NetworkTableInstance.getDefault().getTable("FieldMap");
    encoderDist = fieldMap.getEntry("EncoderValues");
    gyroAngle = fieldMap.getEntry("GyroAngle");
    origGyroAngle = fieldMap.getEntry("OriginalGyroAngle"); //Gyro angle at the start of match
    robotLength = fieldMap.getEntry("RobotLength");
    robotWidth = fieldMap.getEntry("RobotWidth");
    origGyroAngle.setDefaultDouble(drivetrain.getGyroAngle());
    robotLength.setDefaultDouble(35.0);
    robotWidth.setDefaultDouble(24.0);
}

@Override
public void robotPeriodic() {
    encoderDist.setDouble(drivetrain.getGreyhillDistance());
    gyroAngle.setDouble(drivetrain.getGyroAngle());
}
```
