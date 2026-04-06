package sample.service;

import sample.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LocalizationService {

    private final Map<String, String> cache = new HashMap<>();
    private String currentLanguage;

    public void loadStrings(String language) {
        cache.clear();
        currentLanguage = language;

        String sql = "SELECT `key`, value FROM localization_strings WHERE language = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, language);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cache.put(rs.getString("key"), rs.getString("value"));
            }

        } catch (Exception e) {
            System.err.println("Localization DB error: " + e.getMessage());
        }
    }

    public String getString(String key) {
        return cache.getOrDefault(key, "!" + key + "!");
    }

    public Set<String> getAllKeys() {
        return cache.keySet();
    }

    public String getCurrentLanguage() {
        return currentLanguage;
    }
}
