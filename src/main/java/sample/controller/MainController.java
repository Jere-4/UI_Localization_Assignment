package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.model.CalculationRecord;
import sample.service.CalculationService;
import sample.service.LocalizationService;

public class MainController {

    // Labels
    @FXML private Label titleLabel;
    @FXML private Label distanceLabel;
    @FXML private Label consumptionLabel;
    @FXML private Label priceLabel;
    @FXML private Label resultLabel;

    // Text fields
    @FXML private TextField distanceField;
    @FXML private TextField consumptionField;
    @FXML private TextField priceField;

    // Buttons
    @FXML private Button calculateButton;
    @FXML private Button enButton;
    @FXML private Button frButton;
    @FXML private Button jpButton;
    @FXML private Button irButton;

    // Services
    private final LocalizationService localizationService = new LocalizationService();
    private final CalculationService calculationService = new CalculationService();

    // ---------------- INITIALIZATION ----------------

    @FXML
    public void initialize() {

        // Default language
        setLanguage("en");

        // Button wiring
        calculateButton.setOnAction(e -> handleCalculate());
        enButton.setOnAction(e -> setLanguage("en"));
        frButton.setOnAction(e -> setLanguage("fr"));
        jpButton.setOnAction(e -> setLanguage("jp"));
        irButton.setOnAction(e -> setLanguage("fa"));
    }

    // ---------------- LANGUAGE ----------------

    private void setLanguage(String lang) {
        localizationService.loadStrings(lang);

        titleLabel.setText(localizationService.getString("title"));
        distanceLabel.setText(localizationService.getString("distance"));
        consumptionLabel.setText(localizationService.getString("consumption"));
        priceLabel.setText(localizationService.getString("price"));
        calculateButton.setText(localizationService.getString("calculate"));

        resultLabel.setText("");
    }


    // ---------------- CALCULATION ----------------

    private void handleCalculate() {

        try {
            double distance =
                    Double.parseDouble(distanceField.getText());
            double consumption =
                    Double.parseDouble(consumptionField.getText());
            double price =
                    Double.parseDouble(priceField.getText());

            if (distance <= 0 || consumption <= 0 || price <= 0) {
                resultLabel.setText("Invalid input");
                return;
            }

            double totalFuel = distance * consumption / 100.0;
            double totalCost = totalFuel * price;

            String resultTemplate =
                    localizationService.getString("result");

            resultLabel.setText(
                    String.format(resultTemplate, totalFuel, totalCost)
            );

            CalculationRecord record =
                    new CalculationRecord(
                            distance,
                            consumption,
                            price,
                            totalFuel,
                            totalCost,
                            localizationService.getCurrentLanguage()
                    );

            calculationService.saveCalculation(record);

        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input");
        }
    }
}
