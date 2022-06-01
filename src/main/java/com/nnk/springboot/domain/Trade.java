package com.nnk.springboot.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trade")
public class Trade {
    // TODO: Map columns in data table TRADE with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tradeId ;

    @Column(name = "account")
    private String account ;

    @Column(name = "type")
    private String type ;

    @Column(name = "buy_quantity")
    private Double buyQuantity ;


    public Trade(String trade_account, String type) {

    }
}
