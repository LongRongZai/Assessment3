/**
 * 地区表
 */
package com.bean;

import com.Annotation.Column;
import com.Annotation.Table;
import lombok.Data;

@Data
@Table(tableName = "Region")
public class RegionBean {
    @Column(columnName = "RegionName",dataType = "varchar",dataLength = 50,constraint = "primary key")
    private String regionName;
    @Column(columnName = "RegionConfirmed",dataType = "int",constraint = "not null")
    private int regionConfirmed;
    @Column(columnName = "RegionRecovered",dataType = "int",constraint = "not null")
    private int regionRecovered;
    @Column(columnName = "RegionDeaths",dataType = "int",constraint = "not null")
    private int regionDeaths;

}
