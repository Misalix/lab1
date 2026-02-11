import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScaniaTest {

    @Test
    void scania_movePlatform() {
        Scania scania = new Scania();
        scania.raisePlatform(10);
        scania.lowerPlatform(5);
        assertEquals(5, scania.getPlatformAngle(), 1e-9);
    }

    @Test
    void scania_exceptionAbove70() {
        Scania scania = new Scania();
        assertThrows(IllegalArgumentException.class, () -> scania.raisePlatform(71));
    }

    @Test
    void scania_exceptionMoveWhenUp() {
        Scania scania = new Scania();
        scania.setCurrentSpeed(10);
        assertThrows(IllegalArgumentException.class, () -> scania.raisePlatform(1));
    }
}
