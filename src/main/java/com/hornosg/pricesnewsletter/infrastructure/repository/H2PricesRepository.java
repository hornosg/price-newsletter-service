package com.hornosg.pricesnewsletter.infrastructure.repository;

import com.hornosg.pricesnewsletter.domain.dtos.FindPriceRequest;
import com.hornosg.pricesnewsletter.domain.entities.Price;
import com.hornosg.pricesnewsletter.domain.interfaces.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Repository
public class H2PricesRepository implements PricesRepository {
    private final DataSource dataSource;

    @Autowired
    public H2PricesRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Price findPrice(FindPriceRequest request) {
        String sql = buildSqlQuery();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = prepareStatement(connection, sql, request);
             ResultSet rs = preparedStatement.executeQuery()) {
            rs.next();
            return mapPrice(rs);
        } catch (Exception e) {
            throw new RuntimeException("Error executing SQL query", e);
        }
    }

    private String buildSqlQuery() {
        return "SELECT * FROM prices p1 WHERE ? BETWEEN validDateFrom AND validDateTo " +
                "AND brandId = ? AND productId = ? " +
                "AND priority = (SELECT MAX(p2.priority) FROM prices p2 " +
                "WHERE p1.brandId = p2.brandId " +
                "AND p1.productId = p2.productId " +
                "AND ? BETWEEN p2.validDateFrom AND p2.validDateTo)";
    }

    private PreparedStatement prepareStatement(Connection connection, String sql, FindPriceRequest request) throws SQLException {
        LocalDateTime dateTime = LocalDateTime.parse(request.applicationDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Timestamp timestamp = Timestamp.valueOf(dateTime);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setTimestamp(1, timestamp);
        preparedStatement.setInt(2, request.brandId());
        preparedStatement.setInt(3, request.productId());
        preparedStatement.setTimestamp(4, timestamp);
        return preparedStatement;
    }

    private Price mapPrice(ResultSet rs) throws SQLException {
        return new Price(
                rs.getString("id"),
                rs.getInt("brandId"),
                rs.getInt("productId"),
                rs.getTimestamp("validDateFrom").toLocalDateTime(),
                rs.getTimestamp("validDateTo").toLocalDateTime(),
                rs.getInt("priceRateId"),
                rs.getInt("priority"),
                rs.getBigDecimal("price"),
                rs.getString("currencyId")
        );
    }
}