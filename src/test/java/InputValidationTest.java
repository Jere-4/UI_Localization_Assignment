import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidationTest {

    @Test
    void nonNumericInputThrowsException() {
        assertThrows(NumberFormatException.class, () -> {
            Double.parseDouble("abc");
        });
    }

    @Test
    void zeroDistanceIsInvalid() {
        double distance = 0;
        double fuel = 10;

        assertThrows(ArithmeticException.class, () -> {
            if (distance == 0) {
                throw new ArithmeticException("Distance cannot be zero");
            }
            double consumption = (fuel / distance) * 100;
        });
    }

    @Test
    void negativeValuesAreInvalid() {
        double distance = -100;
        double fuel = 10;

        assertTrue(distance < 0 || fuel < 0);
    }
}
