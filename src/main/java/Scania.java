import java.awt.*;

public  class Scania extends Truck {

    private double platformAngle;

    public Scania() {
        super(2, 80, Color.white, "lab2.Scania", 6);
        platformAngle = 0;
    }

    public void raisePlatform(double amount) {
        if (platformAngle + amount > 70)
            throw new IllegalArgumentException("The angle of the platform can't go above 70");
        platformChecks(amount);
        platformAngle = platformAngle + amount;
    }

    public void lowerPlatform(double amount) {
        if (platformAngle - amount < 0)
            throw new IllegalArgumentException("The angle of the platform can't go below 0");
        platformChecks(amount);
        platformAngle = platformAngle - amount;
    }

    private void platformChecks(double amount) {
        if (getCurrentSpeed() != 0)
            throw new IllegalArgumentException("Vehicle can't be moving if you want to move the platform");
        if (amount < 0) {
            throw new IllegalArgumentException("No negative numbers allowed as input");
        }
    }

    public double getPlatformAngle() {
        return platformAngle;
    }

    @Override
    public void move() {
        if (platformAngle > 0)
            throw new IllegalArgumentException("Truck can't move if the platform is off the ground");
        super.move();
    }

    @Override
    public void gas(double amount) {
        if (platformAngle > 0)
            throw new IllegalStateException("Truck can't change speeds if the platform is off the ground");
        super.gas(amount);
    }
}