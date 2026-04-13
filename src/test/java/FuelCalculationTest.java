import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class FuelCalculationTest {

    @Test
    void calculatesFuelAndCostCorrectly() {
        // Given
        double distance = 150.0;      // km
        double consumption = 5.0;     // L / 100 km
        double price = 2.0;           // €/L

        // When
        double totalFuel = (distance * consumption) / 100.0;
        double totalCost = totalFuel * price;

        // Then
        assertEquals(7.5, totalFuel, 0.0001);
        assertEquals(15.0, totalCost, 0.0001);
    }
}
