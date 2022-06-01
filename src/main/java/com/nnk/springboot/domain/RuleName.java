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
@Table(name = "rulename")
public class RuleName {
    // TODO: Map columns in data table RULENAME with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;

    @Column(name = "name")
    private String name ;

    @Column(name = "description")
    private String description ;

    @Column(name = "json")
    private String json ;

    @Column(name = "template")
    private String template ;

    @Column(name = "sql_str")
    private String sqlStr ;

    @Column(name = "sql_part")
    private String sqlPart ;


    public RuleName(String rule_name, String description, String json, String template, String sqlStr, String sql_part) {
    }
}
