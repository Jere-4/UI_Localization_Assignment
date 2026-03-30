package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Controller controller;

    @BeforeEach
    void setup() {
        controller = new Controller();

        // Manually inject FXML fields
        controller.lblDistance = new Label();
        controller.lblConsumption = new Label();
        controller.lblPrice = new Label();
        controller.lblResult = new Label();

        controller.txtDistance = new TextField();
        controller.txtConsumption = new TextField();
        controller.txtPrice = new TextField();

        controller.btnCalculate = new Button();
        controller.btnEN = new Button();
        controller.btnFR = new Button();
        controller.btnJP = new Button();
        controller.btnIR = new Button();

        // Manually call initialize
        controller.initialize();
    }

    // -------------------------
    // Localization Tests
    // -------------------------

    @Test
    void testEnglishLocalization() {
        controller.btnEN.fire(); // triggers setLanguage(en_US)
        assertEquals("Distance (km)", controller.lblDistance.getText());
        assertEquals("Fuel consumption (L/100km)", controller.lblConsumption.getText());
        assertEquals("Fuel price", controller.lblPrice.getText());
        assertEquals("Calculate", controller.btnCalculate.getText());
    }

    @Test
    void testFrenchLocalization() {
        controller.btnFR.fire();
        assertEquals("Distance (km)", controller.lblDistance.getText()); // adjust based on your FR file
        assertEquals("Consommation (L/100km)", controller.lblConsumption.getText());
        assertEquals("Prix du carburant", controller.lblPrice.getText());
        assertEquals("Calculer", controller.btnCalculate.getText());
    }

    @Test
    void testInvalidLanguageFile() {
        controller.setLanguage(new Locale("xx", "YY"));
        assertEquals("Missing language file!", controller.lblResult.getText());
    }

    // -------------------------
    // Calculation Tests
    // -------------------------

    @Test
    void testValidCalculation() {
        controller.txtDistance.setText("100");
        controller.txtConsumption.setText("5");
        controller.txtPrice.setText("2");

        controller.btnCalculate.fire();

        // Expected:
        // totalFuel = (5/100) * 100 = 5
        // totalCost = 5 * 2 = 10
        String result = controller.lblResult.getText();

        assertTrue(result.contains("5.00"));
        assertTrue(result.contains("10.00"));
    }

    @Test
    void testInvalidInputShowsError() {
        controller.txtDistance.setText("abc"); // invalid
        controller.txtConsumption.setText("5");
        controller.txtPrice.setText("2");

        controller.btnCalculate.fire();

        assertEquals(controller.bundle.getString("invalid.input"), controller.lblResult.getText());
    }
}
