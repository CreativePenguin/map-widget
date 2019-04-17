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

@Override
protected void robotInit() {
    fieldMap = NetworkTableInstace.getDefault().getTable("FieldMap");
    encoderDist = fieldMap.getEntry("EncoderDistance");
    gyroAngle = fieldMap.getEntry("GyroAngle");
    origGyroAngle = fieldMap.getEntry("OriginalGyroAngle"); //Gyro angle at the start of match
    robotLength = fieldMap.getEntry("RobotLength");
    origGyroAngle.setDefaultDouble(drivetrain.getGyroAngle());
}

@Override
protected void robotPeriodic() {
    encoderDist.setDouble(drivetrain.getGreyhillDistance());
    gyroAngle.setDouble(drivetrain.getGyroAngle());
    robotLength.setDefaultDouble(35.0);
    robotWidth.setDefaultDouble(24.0);
}
