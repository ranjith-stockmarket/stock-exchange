package com.stockmarket.stockexchange.Service;

import com.stockmarket.stockexchange.DTO.AddStockExchangeDTO;
import com.stockmarket.stockexchange.DTO.StockExchangeIdsDTO;
import com.stockmarket.stockexchange.Helper.ServiceResponse;


public interface StockExchangeService {

    ServiceResponse addStockExchange(AddStockExchangeDTO addStockExchangeDTO);

    ServiceResponse getStockExchangeById(Long id);

    ServiceResponse getAllStockExchanges();

    ServiceResponse doExist(StockExchangeIdsDTO stockExchangeIdsDTO);
}
