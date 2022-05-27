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

    private String account ;

    private String type ;

    private Double buyQuantity ;

    private Double sellQuantity ;

    private Double buyPrice ;

    private Double sellPrice ;

    private String benchmark ;

    private Timestamp tradeDate ;

    private String security ;

    private String status ;

    private String trader ;

    private String book ;

    private String creationName ;

    private Timestamp creationDate ;

    private String revisionName ;

    private Timestamp revisionDate ;

    private String dealName ;

    private String dealType ;

    private String sourceListId ;

    private String side ;

    public Trade(String trade_account, String type) {

    }
}
