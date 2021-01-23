/**
 * 国家表
 */
package com.bean;

import com.Annotation.Column;
import com.Annotation.Table;
import lombok.Data;

@Data
@Table(tableName = "Country")
public class CountryBean {
    @Column(columnName = "CountryName",dataType = "varchar",dataLength = 50,constraint = "primary key")
    private String countryName;
    @Column(columnName = "CountryConfirmed",dataType = "int",constraint = "not null")
    private int countryConfirmed;
    @Column(columnName = "CountryRecovered",dataType = "int",constraint = "not null")
    private int countryRecovered;
    @Column(columnName = "CountryDeaths",dataType = "int",constraint = "not null")
    private int countryDeaths;
    @Column(columnName = "CountryPopulation",dataType = "int",constraint = "not null")
    private int countryPopulation;
    @Column(columnName = "CountryContinent",dataType = "varchar",constraint = "not null")
    private String countryContinent;
    @Column(columnName = "CountryArea",dataType = "int",constraint = "not null")
    private int countryArea;
    @Column(columnName = "CountryCapitalCity",dataType = "varchar")
    private String countryCapitalCity;



}
