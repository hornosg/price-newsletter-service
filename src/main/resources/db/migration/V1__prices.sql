CREATE TABLE IF NOT EXISTS prices (
    id INT PRIMARY KEY,
    brandId INT,
    productId INT,
    validDateFrom TIMESTAMP,
    validDateTo TIMESTAMP,
    priceRateId INT,
    priority INT,
    price DECIMAL,
    currencyId VARCHAR(255)
);

INSERT INTO prices (id, brandId, productId, validDateFrom, validDateTo, priceRateId, priority, price, currencyId)
SELECT * FROM (
    SELECT RANDOM_UUID(), 1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR' UNION ALL
    SELECT RANDOM_UUID(), 1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR' UNION ALL
    SELECT RANDOM_UUID(), 1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1, 30.50, 'EUR' UNION ALL
    SELECT RANDOM_UUID(), 1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 1, 38.95, 'EUR'
) AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM prices WHERE id = 1
);