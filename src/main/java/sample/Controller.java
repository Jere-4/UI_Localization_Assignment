package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller {

    @FXML private Label lblDistance;
    @FXML private Label lblConsumption;
    @FXML private Label lblPrice;
    @FXML private Label lblResult;

    @FXML private TextField txtDistance;
    @FXML private TextField txtConsumption;
    @FXML private TextField txtPrice;

    @FXML private Button btnCalculate;
    @FXML private Button btnEN, btnFR, btnJP, btnIR;

    private ResourceBundle bundle;

    @FXML
    public void initialize() {

        setLanguage(new Locale("en", "US"));

        btnEN.setOnAction(e -> setLanguage(new Locale("en", "US")));
        btnFR.setOnAction(e -> setLanguage(new Locale("fr", "FR")));
        btnJP.setOnAction(e -> setLanguage(new Locale("ja", "JP")));
        btnIR.setOnAction(e -> setLanguage(new Locale("fa", "IR")));

        btnCalculate.setOnAction(e -> calculate());
    }

    private void setLanguage(Locale locale) {
        try {
            bundle = ResourceBundle.getBundle("sample.messages", locale);

            lblDistance.setText(bundle.getString("distance.label"));
            lblConsumption.setText(bundle.getString("consumption.label"));
            lblPrice.setText(bundle.getString("price.label"));
            btnCalculate.setText(bundle.getString("calculate.button"));
            lblResult.setText("");

        } catch (Exception ex) {
            lblResult.setText("Missing language file!");
            ex.printStackTrace();
        }
    }

    private void calculate() {
        try {
            double distance = Double.parseDouble(txtDistance.getText());
            double consumption = Double.parseDouble(txtConsumption.getText());
            double price = Double.parseDouble(txtPrice.getText());

            double totalFuel = (consumption / 100.0) * distance;
            double totalCost = totalFuel * price;

            String msg = bundle.getString("result.label");
            lblResult.setText(MessageFormat.format(
                    msg,
                    String.format("%.2f", totalFuel),
                    String.format("%.2f", totalCost)
            ));

        } catch (Exception ex) {
            lblResult.setText(bundle.getString("invalid.input"));
        }
    }
}
