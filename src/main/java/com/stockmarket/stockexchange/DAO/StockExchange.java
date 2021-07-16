package com.stockmarket.stockexchange.DAO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "stock_exchange")
public class StockExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "brief")
    private String brief;

    @Column(name = "address")
    private String address;

    @Column(name = "remarks")
    private String remarks;

}
