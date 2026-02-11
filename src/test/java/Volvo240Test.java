import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Volvo240Test {
    @Test

    void volvo_startEngineSpeedToPointOne(){
        Volvo240 car = new Volvo240();
        car.startEngine();
        car.gas(1.0);
        assertEquals(1.35, car.getCurrentSpeed(), 1e-9);
    }
    @Test
    void volvo_stopEngineSpeedToPointZero(){
        Volvo240 car = new Volvo240();
        car.startEngine();
        car.stopEngine();
        assertEquals(0.0, car.getCurrentSpeed(), 1e-9);
    }

    @Test
    void volvo_move_coversEastAndSouth(){
        Volvo240 car = new Volvo240();
        car.startEngine();
        car.gas(1.0);
        double x0 = car.getX();
        double y0 = car.getY();
        double s = car.getCurrentSpeed();

        //East
        car.turnRight();
        double x1 = car.getX();
        double y1 = car.getY();
        car.move();
        assertEquals(x1 + s, car.getX(), 1e-9);
        assertEquals(y1, car.getY(), 1e-9);

        //South
        car.turnRight();
        double x2 = car.getX();
        double y2 = car.getY();
        car.move();
        assertEquals(x2, car.getX(), 1e-9);
        assertEquals(y2-s, car.getY(), 1e-9);
    }

    @Test
    void volvo_brakeTest(){
        Volvo240 car = new Volvo240();
        car.setCurrentSpeed(15);
        car.brake(0.8);
        assertEquals(14, car.getCurrentSpeed(), 1e-9);
    }

    @Test
    void volvo_turnRightTest(){
        // From NORTH to EAST
        Volvo240 car = new Volvo240();
        car.startEngine();
        double xBefore = car.getX();
        double yBefore = car.getY();
        car.turnRight();
        car.move();
        assertEquals(xBefore + car.getCurrentSpeed(), car.getX(), 1e-9);
        assertEquals(yBefore, car.getY(), 1e-9);

        //From EAST to SOUTH
        car.turnRight();
        double xBefore2 = car.getX();
        double yBefore2 = car.getY();
        car.move();
        assertEquals(xBefore2, car.getX(), 1e-9);
        assertEquals(yBefore2 - car.getCurrentSpeed(), car.getY(), 1e-9);


    }

    @Test
    void volvo_turnLeftTest(){
        // From NORTH to WEST

        Volvo240 car = new Volvo240();
        car.startEngine();
        double xBefore = car.getX();
        double yBefore = car.getY();
        car.turnLeft();
        car.move();
        assertEquals(xBefore - car.getCurrentSpeed(), car.getX(), 1e-9);
        assertEquals(yBefore, car.getY(), 1e-9);

        //From WEST to SOUTH
        car.turnLeft();
        double xBefore1 = car.getX();
        double yBefore1 = car.getY();
        car.move();
        assertEquals(xBefore1, car.getX(), 1e-9);
        assertEquals(yBefore1 - car.getCurrentSpeed(), car.getY(), 1e-9);
    }

    @Test
    void testWorkshop(){
        Workshop <Volvo240> volvoGarage = new Workshop<>(2);
        volvoGarage.load(new Volvo240());
        Volvo240 v = volvoGarage.unload(0);

       Workshop <Car> allCars = new Workshop<>(4);
       allCars.load(new Volvo240());
       allCars.load(new Saab95());
    }

}