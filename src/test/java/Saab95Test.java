import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Saab95Test {
    @Test
    void saab_startEngineSpeedToPointOne(){
        Saab95 saab95 = new Saab95();
        saab95.startEngine();
        saab95.setTurboOn();

        saab95.gas(1.0);
        assertEquals(1.725, saab95.getCurrentSpeed(), 1e-9);
    }


      @Test
      void saab_stopEngineSpeedToPointZero(){
        Saab95 saab95 = new Saab95();
        saab95.stopEngine();
        saab95.setTurboOff();
        assertEquals(0.0, saab95.getCurrentSpeed(), 1e-9);
    }

    @Test
    void saab_moveDirectionEastAndSouth(){
        Saab95 saab = new Saab95();
        saab.startEngine();
        saab.gas(1.0);
        double x0 = saab.getX();
        double y0 = saab.getY();
        double s = saab.getCurrentSpeed();

        // East
        saab.turnRight();
        double x1 = saab.getX();
        double y1 = saab.getY();
        saab.move();
        assertEquals(x1 + s, saab.getCurrentSpeed(), 1e-9);
        assertEquals(y1, saab.getCurrentSpeed(), 1e-9);

        // South
        saab.turnRight();
        double x2 = saab.getX();
        double y2 = saab.getY();
        saab.move();
        assertEquals(x2, saab.getCurrentSpeed(), 1e-9);
        assertEquals(y2-s, saab.getCurrentSpeed(), 1e-9);

    }
    @Test
    void saab_brakeTest(){
        Saab95 saab = new Saab95();
        saab.setCurrentSpeed(10);
        saab.setTurboOff();
        saab.brake(0.5);
        assertEquals(9.375, saab.getCurrentSpeed(), 1e-9);

    }

    @Test
    void saab_turnRightTest(){
        //From NORTH to EAST
        Saab95 saab = new Saab95();
        saab.startEngine();
        double xBefore = saab.getX();
        double yBefore = saab.getY();
        saab.turnRight();
        assertEquals(xBefore + saab.getCurrentSpeed(), saab.getX(), 1e-9);
        assertEquals(yBefore, saab.getY(), 1e-9);

        // From EAST to SOUTH

        saab.turnRight();
        double xBefore1 = saab.getX();
        double yBefore1 = saab.getY();
        saab.move();
        assertEquals(xBefore, saab.getX(), 1e-9);
        assertEquals(yBefore1 - saab.getCurrentSpeed(), saab.getY(), 1e-9);
    }

    @Test
    void saab_turnLeftTest(){

        // From NORTH to WEST
        Saab95 saab = new Saab95();
        saab.startEngine();
        double xBefore = saab.getX();
        double yBefore = saab.getY();
        saab.move();
        assertEquals(xBefore - saab.getCurrentSpeed(), saab.getX(), 1e-9);
        assertEquals(yBefore, saab.getY(), 1e-9);

        
        // From WEST to SOUTH
        saab.turnLeft();
        double xBefore1 = saab.getX();
        double yBefore1 = saab.getY();
        saab.move();
        assertEquals(xBefore, saab.getX(), 1e-9);
        assertEquals(yBefore1 - saab.getCurrentSpeed(), saab.getY(), 1e-9);
    }

    @Test
    void testSaabWorkshop(){
        Workshop <Saab95> saabGarage = new Workshop<>(10);
        saabGarage.load(new Saab95());
        Saab95 saab = saabGarage.unload(0);
    }

}
