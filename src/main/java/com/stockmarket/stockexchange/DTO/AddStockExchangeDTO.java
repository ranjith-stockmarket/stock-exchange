package com.stockmarket.stockexchange.DTO;

import com.stockmarket.stockexchange.DAO.StockExchange;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
@NoArgsConstructor
public class AddStockExchangeDTO {
    @NotNull(message = "Name can't be null")
    @Size(max = 100, message = "Name be less than 100 Characters")
    private String name;
    private String brief;
    @NotNull(message = "Address can't be null")
    private String address;
    private String remarks;
}
