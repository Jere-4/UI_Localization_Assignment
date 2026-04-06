-- =====================================================
-- Database: Fuel Calculator Localization
-- =====================================================

DROP DATABASE IF EXISTS fuel_calculator_localization;

CREATE DATABASE fuel_calculator_localization
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE fuel_calculator_localization;

-- =====================================================
-- Table: calculation_records
-- =====================================================

CREATE TABLE calculation_records (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     distance DOUBLE NOT NULL,
                                     consumption DOUBLE NOT NULL,
                                     price DOUBLE NOT NULL,
                                     total_fuel DOUBLE NOT NULL,
                                     total_cost DOUBLE NOT NULL,
                                     language VARCHAR(10) NOT NULL,
                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =====================================================
-- Table: localization_strings
-- =====================================================

CREATE TABLE localization_strings (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      `key` VARCHAR(100) NOT NULL,
                                      value VARCHAR(255) NOT NULL,
                                      language VARCHAR(10) NOT NULL,
                                      UNIQUE KEY unique_key_lang (`key`, `language`)
);

-- =====================================================
-- ENGLISH (en)
-- =====================================================

INSERT INTO localization_strings (`key`, value, language) VALUES
                                                              ('title', 'Fuel Calculator', 'en'),
                                                              ('distance', 'Distance (km)', 'en'),
                                                              ('consumption', 'Fuel consumption (L / 100 km)', 'en'),
                                                              ('price', 'Fuel price (€)', 'en'),
                                                              ('calculate', 'Calculate', 'en'),
                                                              ('result', 'Total fuel: %.2f L | Total cost: %.2f €', 'en');

-- =====================================================
-- FRENCH (fr)
-- =====================================================

INSERT INTO localization_strings (`key`, value, language) VALUES
                                                              ('title', 'Calculateur de carburant', 'fr'),
                                                              ('distance', 'Distance (km)', 'fr'),
                                                              ('consumption', 'Consommation (L / 100 km)', 'fr'),
                                                              ('price', 'Prix du carburant (€)', 'fr'),
                                                              ('calculate', 'Calculer', 'fr'),
                                                              ('result', 'Carburant total : %.2f L | Coût total : %.2f €', 'fr');

-- =====================================================
-- JAPANESE (jp)
-- =====================================================

INSERT INTO localization_strings (`key`, value, language) VALUES
                                                              ('title', '燃料計算機', 'jp'),
                                                              ('distance', '距離 (km)', 'jp'),
                                                              ('consumption', '燃費 (L / 100 km)', 'jp'),
                                                              ('price', '燃料価格 (€)', 'jp'),
                                                              ('calculate', '計算', 'jp'),
                                                              ('result', '使用燃料: %.2f L | 総費用: %.2f €', 'jp');

-- =====================================================
-- PERSIAN (fa)
-- =====================================================

INSERT INTO localization_strings (`key`, value, language) VALUES
                                                              ('title', 'محاسبه مصرف سوخت', 'fa'),
                                                              ('distance', 'مسافت (کیلومتر)', 'fa'),
                                                              ('consumption', 'مصرف سوخت (لیتر / ۱۰۰ کیلومتر)', 'fa'),
                                                              ('price', 'قیمت سوخت (€)', 'fa'),
                                                              ('calculate', 'محاسبه', 'fa'),
                                                              ('result', 'سوخت کل: %.2f لیتر | هزینه کل: %.2f €', 'fa');
