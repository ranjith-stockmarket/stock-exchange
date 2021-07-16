package com.stockmarket.stockexchange.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter @Setter
public class StockExchangeIdsDTO {
    private List<Long> stockExchangeIds;
}
