CREATE TABLE IF NOT EXISTS prices (
    id VARCHAR(255) PRIMARY KEY,
    brandId INT,
    validDateFrom TIMESTAMP,
    validDateTo TIMESTAMP,
    priceRateId INT,
    productId INT,
    priority INT,
    price DECIMAL,
    currencyId VARCHAR(255)
);

INSERT INTO prices (id, brandId, validDateFrom, validDateTo, priceRateId, productId, priority, price, currencyId)
SELECT * FROM (
    SELECT RANDOM_UUID() AS id, 1 AS brandId, TIMESTAMP '2020-06-14 00:00:00' AS validDateFrom, TIMESTAMP '2020-12-31 23:59:59' AS validDateTo, 1 AS priceRateId, 35455 AS productId, 0 AS priority, 35.50 AS price, 'EUR' AS currencyId UNION ALL
    SELECT RANDOM_UUID(), 1, TIMESTAMP '2020-06-14 15:00:00', TIMESTAMP '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR' UNION ALL
    SELECT RANDOM_UUID(), 1, TIMESTAMP '2020-06-15 00:00:00', TIMESTAMP '2020-06-15 11:00:00', 3, 35455, 1, 30.50, 'EUR' UNION ALL
    SELECT RANDOM_UUID(), 1, TIMESTAMP '2020-06-15 16:00:00', TIMESTAMP '2020-12-31 23:59:59', 4, 35455, 1, 38.95, 'EUR'
) AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM prices WHERE id = 1
);