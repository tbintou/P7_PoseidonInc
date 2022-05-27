package com.nnk.springboot.domain;

import lombok.*;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.sql.Timestamp;
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bidlist")
public class BidList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bidListId ;

    @Pattern(regexp="^[A-Za-z]*$", message = "Input has to be text")
    @NotBlank(message = "Account is mandatory")
    @Column(name = "account", unique = true)
    private String account ;

    @Pattern(regexp="^[A-Za-z]*$", message = "Input has to be text")
    @NotBlank(message = "Account is mandatory")
    private String type ;

    @Digits(integer = 15, fraction = 2)
    @Min(value = 0, message = "The value must be positive")
    @NotNull(message = "This Field cannot be empty")
    private Double bidQuantity ;

    private Double askQuantity ;

    private Double bid ;

    private Double ask ;

    private String benchmark ;

    private Timestamp bidListDate ;

    private String commentary ;

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

    public BidList(String account_test, String type_test, double bidQuantity) {
    }

    public BidList(String account, String type, Double bidQuantity) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }
}
