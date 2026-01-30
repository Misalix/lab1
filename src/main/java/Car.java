import java.awt.*;

public abstract class Car implements Movable {

    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name

    // How the car moves in x and y-coordinates
    private double x;
    private double y;


    public Car(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        stopEngine();
    }

    private enum Direction {
        NORTH, EAST, SOUTH, WEST
    }

    // The car's position at the start

    private Direction direction = Direction.NORTH;

    // Moves the car in current direction by currentSpeed
    @Override
    public void move() {
        switch (direction) {
            case NORTH:
                y += currentSpeed;
                break;
            case EAST:
                x += currentSpeed;
                break;
            case SOUTH:
                y -= currentSpeed;
                break;
            case WEST:
                x -= currentSpeed;
                break;

        }
    }

    // Rotates the car 90 degrees to the left (counter clock wise)
    @Override
    public void turnLeft() {
        switch (direction) {
            case NORTH:
                direction = Direction.WEST;
                break;
            case WEST:
                direction = Direction.SOUTH;
                break;
            case SOUTH:
                direction = Direction.EAST;
                break;
            case EAST:
                direction = Direction.NORTH;
                break;

        }
    }

    // Rotates the car 90 degrees to the right (clock wise)
    @Override
    public void turnRight() {
        switch (direction) {
            case NORTH:
                direction = Direction.EAST;
                break;
            case EAST:
                direction = Direction.SOUTH;
                break;
            case SOUTH:
                direction = Direction.WEST;
                break;
            case WEST:
                direction = Direction.NORTH;
                break;
        }
    }

    // Returns the car's x-coordinate
    public double getX() {
        return x;
    }

    // Returns the car's y-coordinate
    public double getY() {
        return y;
    }


    // Methods that were taken from the subclasses because they were identical


    // Returns number of doors
    public int getNrDoors() {
        return nrDoors;
    }

    // Returns engine power
    public double getEnginePower() {
        return enginePower;
    }

    // Returns current speed
    public double getCurrentSpeed() {
        return currentSpeed;
    }

    // Returns car color
    public Color getColor() {
        return color;
    }

    // Sets the car color
    public void setColor(Color clr) {
        color = clr;
    }

    public void startEngine() {
        currentSpeed = 0.1;
    }

    public void stopEngine() {
        currentSpeed = 0;
    }

    protected void setCurrentSpeed(double newSpeed) {
        currentSpeed = newSpeed;
    }

    protected abstract double speedFactor();

    public void incrementSpeed(double amount) {
        double newSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, getEnginePower());
        if (newSpeed < getCurrentSpeed())
            throw new RuntimeException("Speed was decreased when calling gas method");
        else
            setCurrentSpeed(newSpeed);
    }

    public void decrementSpeed(double amount) {
        double newSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
        if (newSpeed > getCurrentSpeed())
            throw new RuntimeException("Speed was increased when calling break method");
        else
            setCurrentSpeed(newSpeed);
    }

    public void gas(double amount) {
        if (amount < 0 || amount > 1)
            throw new IllegalArgumentException("Amount has to be between 0 and 1");
        else
            incrementSpeed(amount);
    }

    public void brake(double amount) {
        if (amount < 0 || amount > 1)
            throw new IllegalArgumentException("Amount has to be between 0 and 1");
        else
            decrementSpeed(amount);
    }
}