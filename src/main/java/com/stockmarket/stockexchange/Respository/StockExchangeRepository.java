package com.stockmarket.stockexchange.Respository;

import com.stockmarket.stockexchange.DAO.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockExchangeRepository extends JpaRepository<StockExchange, Long> {

    boolean existsByName(String name);

    @Query("select se from StockExchange se order by se.id")
    List<StockExchange> getAll();
}
