package frc.plugin;

public enum StartingPos {

    //NOTE: All of the measurements are for level 2 starting position

    RIGHT_CS(73, 326, "Right Cargo Ship"),
    LEFT_CS(73, 182, "Left Cargo Ship"),
    MID(127, 254, "Middle");

    double x; double y; String val;

    StartingPos(double x, double y, String val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }

    @Override
    public String toString() {return val;}

}
