import  java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CarTransporter extends Car {

    private boolean platformRaised;
    private  int maxCars;
    private  List<Car> loadedCars;

    public CarTransporter() {
        super(2, 70, Color.gray, "lab2.CarTransporter", 5);
        platformRaised = true;
        maxCars = 10;
        loadedCars = new ArrayList<>();
    }

    public  void setPlatformRaised() {
        platformRaised = true;
    }

    public void setPlatformDown() {
        if (getCurrentSpeed() != 0)
            throw  new IllegalStateException("Vehicle has to be stationary to lower the ramp");
        platformRaised = false;
    }

    public boolean isPlatformRaised() {
        return platformRaised;
    }

    @Override
    public  void move() {
        if (!platformRaised)
            throw new IllegalArgumentException("Truck can't move if the platform is off the ground");
        super.move();

        syncCarPos();
    }

    public void syncCarPos() {
        for (Car c : loadedCars) {
            c.setPosition(this.getX(), this.getY());
        }
    }


    @Override
    public void gas(double amount) {
        if (!platformRaised)
            throw new IllegalStateException("Truck can't change speeds if the platform is off the ground");
        super.gas(amount);
    }

    public  void loadCar(Car car) {

        if (car == null)
            throw new IllegalStateException("Car can't be null");
        if (car instanceof CarTransporter)
            throw new IllegalStateException("Can't load another car transporter");
        if (getCurrentSpeed() != 0)
            throw  new IllegalStateException("Car transporter has to be stationary to load a car");
        if (loadedCars.size() >= maxCars)
            throw new IllegalStateException("Max amount of cars has been reached");
        if (car.getSize() >= 5)
            throw new IllegalStateException("Vehicle is too big");
        if (platformRaised)
            throw new IllegalStateException("Can't load vehicles if platform is raised");

        double distance = Math.abs(this.getX() - car.getX()) + Math.abs(this.getY() - car.getY());

        if (distance > 2.0)
            throw new IllegalStateException("Car is too far away");
        loadedCars.add(car);
        car.setPosition(this.getX(), this.getY());

    }

    public Car unloadCar() {
        if (platformRaised)
            throw new IllegalStateException("Can't unload vehicles if platform is raised");
        if (getCurrentSpeed() != 0)
            throw new IllegalStateException("Car transporter has to be stationary to unload a car");
        if (loadedCars.isEmpty())
            throw new IllegalStateException("There are no cars in the transport car");
        Car car = loadedCars.removeLast();
        car.setPosition(this.getX(), this.getY() - 1);

        return car;
    }
}
