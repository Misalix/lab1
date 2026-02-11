import java.awt.*;

public abstract class Truck extends Vehicle{
    public Truck (int nrDoors, double enginePower, Color color, String modelName, int size) {
        super(nrDoors, enginePower, color, modelName, size);
    }

    @Override
    protected double speedFactor () { return getEnginePower() * 0.01; }
}



