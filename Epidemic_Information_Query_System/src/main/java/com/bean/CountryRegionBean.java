/**
 * 国家—地区表
 */
package com.bean;

import com.Annotation.Column;
import com.Annotation.Table;
import com.Annotation.TableConstraint;
import lombok.Data;

@Data
@Table(tableName = "CountryRegion")
@TableConstraint(tableConstraint = "foreign key(CountryName) references Country(CountryName),\n" +
        "foreign key(RegionName) references Region(RegionName),\n" +
        "primary key(CountryName,RegionName)")
public class CountryRegionBean {
    @Column(columnName = "CountryName",dataType = "varchar",dataLength = 50)
    private String countryName;
    @Column(columnName = "RegionName",dataType = "varchar",dataLength = 50)
    private String regionName;
}
