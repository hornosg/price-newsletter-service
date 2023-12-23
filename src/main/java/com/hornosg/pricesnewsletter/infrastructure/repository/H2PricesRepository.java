package com.hornosg.pricesnewsletter.infrastructure;

import com.hornosg.pricesnewsletter.domain.FindPricesRequest;
import com.hornosg.pricesnewsletter.domain.Price;
import com.hornosg.pricesnewsletter.domain.PricesRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class H2PricesRepository implements PricesRepository {
    private Connection dbStatement;

    private Statement connect() throws SQLException {
        return dbStatement.createStatement();
    }


    @Override
    public List<Price> findPrices(FindPricesRequest request) {
        List<Price> prices = new ArrayList<>();

        try {
            Statement stmt = this.connect();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Price");// replace with your actual SQL query
            while (rs.next()) {
                prices.add(mapPrice(rs));
            }

        return prices;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
