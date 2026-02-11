import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarTransporterTest {

    @Test
    void carTransport_rampStartRaised() {
        CarTransporter transporter = new CarTransporter();
        assertTrue(transporter.isPlatformRaised());
    }

    @Test
    void carTransport_rampIsLowered() {
        CarTransporter transporter = new CarTransporter();
        transporter.setPlatformDown();
        assertFalse(transporter.isPlatformRaised());
    }

    @Test
    void carTransport_cannotMoveWithRampDown() {
        CarTransporter transporter = new CarTransporter();
        transporter.setCurrentSpeed(10);
        assertThrows(IllegalStateException.class, transporter :: setPlatformDown);
    }

    @Test
    void carTransport_loadingFailsWithRampUp() {
        CarTransporter transporter = new CarTransporter();
        assertThrows(IllegalStateException.class, () -> transporter.loadCar(new Volvo240()));
    }

    @Test
    void carTransport_loadingFailIfTooFar () {
        CarTransporter transporter = new CarTransporter();
        Car volvo240 = new Volvo240();
        transporter.setPlatformDown();
        transporter.setPosition(1,1);
        volvo240.setPosition(10,10);
        assertThrows(IllegalStateException.class, () -> transporter.loadCar(volvo240));
    }

    @Test
    void carTransport_loadingFailIfTooBig() {
        CarTransporter transporter = new CarTransporter();
        transporter.setPlatformDown();
        Car volvo240 = new Volvo240();
        volvo240.setSize(6);
        assertThrows(IllegalStateException.class, () -> transporter.loadCar(volvo240));
    }

    @Test
    void carTransport_failIfMaxCapacity() {
        CarTransporter transporter = new CarTransporter();
        transporter.setPlatformDown();
        for (int i=0; i <10; i++)
            transporter.loadCar(new Volvo240());
        assertThrows(IllegalStateException.class, () -> transporter.loadCar(new Volvo240()));
    }

    @Test
    void  carTransport_loadingWorks() {
        CarTransporter transporter = new CarTransporter();
        transporter.setPlatformDown();
        assertDoesNotThrow(() -> transporter.loadCar(new Volvo240()));
    }

    @Test
    void carTransport_unloadFailsWithRampUp() {
        CarTransporter transporter = new CarTransporter();
        Car car = new Volvo240();

        transporter.setPosition(0,0);
        transporter.setPlatformDown();
        transporter.loadCar(car);

        transporter.setPlatformRaised();

        assertThrows(IllegalStateException.class, transporter::unloadCar);
    }

    @Test
     void carTransport_unloadIsLifo(){
        CarTransporter transporter = new CarTransporter();
        Car a = new Volvo240();
        Car b = new Saab95();

        transporter.setPosition(0,0);
        a.setPosition(0,0);
        b.setPosition(0,0);
        transporter.setPlatformDown();
        transporter.loadCar(a);
        transporter.loadCar(b);

        Car firstOut = transporter.unloadCar();
        Car secondOut = transporter.unloadCar();

        assertSame(b, firstOut);
        assertSame(a, secondOut);
    }


    @Test
    void carTransport_unloadedCarEndsUpNearTransporter(){
        CarTransporter transporter = new CarTransporter();
        Car car = new Saab95();

        transporter.setPosition(5,5);
        car.setPosition(5,5);
        transporter.setPlatformDown();
        transporter.loadCar(car);

        transporter.unloadCar();

        assertEquals(5.0, car.getX(), 1e-9);
        assertEquals(4.0, car.getY(), 1e-9);
    }

    @Test
    void carTransport_loadedCarFollowsTransporterPositionWhenMoving(){
        CarTransporter transporter = new CarTransporter();
        Car car = new Volvo240();

        transporter.setPosition(0,0);
        car.setPosition(0,0);
        transporter.setPlatformDown();
        transporter.loadCar(car);
        transporter.setPlatformRaised();

        transporter.startEngine();
        transporter.gas(1.0);
        transporter.move();

        assertEquals(transporter.getX(), car.getX(), 1e-9);
        assertEquals(transporter.getY(), car.getY(), 1e-9);


    }


}
