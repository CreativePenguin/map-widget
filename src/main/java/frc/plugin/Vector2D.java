package frc.plugin;

import org.bytedeco.javacpp.opencv_core.Mat;

public class Vector2D {
    public double x;
    public double y;

    Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    Vector2D(double[] in) {
        this.x = in[0];
        this.y = in[1];
    }

    Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Vector2D other) {
        double xDis = x - other.x;
        double yDis = y - other.y;
        xDis *= xDis; yDis *= yDis;
        return Math.sqrt(xDis + yDis);
    }

    public double distance() {
        return distance(new Vector2D(0, 0));
    }

    public void rotate(double angdeg) {
        final double radians = Math.toRadians(angdeg);
        final double sin = Math.sin(radians);
        final double cos = Math.cos(radians);
        final double oldx = this.x;
        final double oldy = this.y;
        
        this.x = oldx*cos - oldy*sin;
        this.y = oldy*cos + oldx*sin;
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D sub(Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public Vector2D mul(Vector2D other) {
        return new Vector2D(this.x * other.x, this.y * other.y);
    }

    public Vector2D div(Vector2D other) {
        return new Vector2D(this.x / other.x, this.y / other.y);
    }
}