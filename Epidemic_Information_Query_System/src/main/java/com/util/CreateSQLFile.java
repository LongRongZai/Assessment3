/**
 * 生成sql脚本
 */
package com.util;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.util.Map;
import java.util.Set;

public class CreateSQLFile {
    private CreateSQLFile() {

    }

    public static void createSQL() {
        File file = new File("CasesDB.sql");
        if (file.exists()) {
            System.out.println("SQL脚本：CasesDB.sql 已生成，无需再次生成");
        } else {
            BufferedReader in = null;
            BufferedWriter out = null;
            try {
                in = new BufferedReader(new FileReader("CasesData.txt"));
                out = new BufferedWriter(new FileWriter("CasesDB.sql"));
                out.write(AutoCreateTableUtil.createTables("com.bean.CountryBean", "com.bean.RegionBean", "com.bean.CountryRegionBean"));
                String s = null;
                String ts = "";
                while ((s = in.readLine()) != null) {
                    ts += s;
                }
                Map<String, Object> countryMap = JSON.parseObject(ts);
                String[] countryArray = {"China", "US", "United Kingdom", "Japan"};
                for (String country : countryArray) {
                    Map<String, Object> regionMap = (Map<String, Object>) countryMap.get(country);
                    Set<String> regionSet = regionMap.keySet();
                    for (String region : regionSet) {
                        Map<String, Object> dataMap = (Map<String, Object>) regionMap.get(region);
                        if (region.equals("All")) {
                            out.write("insert into Country(CountryName ,CountryConfirmed ,CountryRecovered ," +
                                    "CountryDeaths ,CountryPopulation ,CountryContinent ,CountryArea ,CountryCapitalCity ) " +
                                    "values('" + country + "'," + dataMap.get("confirmed") + "," + dataMap.get("recovered") + "," + dataMap.get("deaths") + "," +
                                    dataMap.get("population") + ",'" + dataMap.get("continent") + "'," + dataMap.get("sq_km_area") + ",'" + dataMap.get("capital_city") + "');\n");
                        } else if (region.equals("Unknown")) {

                        } else {
                            out.write("insert into Region(RegionName ,RegionConfirmed ,RegionRecovered ," + "RegionDeaths ) " +
                                    "values('" + region + "'," + dataMap.get("confirmed") + "," + dataMap.get("recovered") + "," + dataMap.get("deaths") + ");\n");
                            out.write("insert into CountryRegion(CountryName ,RegionName ) values ('" + country + "','" + region + "');\n");
                        }


                    }

                }
                out.flush();
                System.out.println("SQL脚本：CasesDB.sql 生成成功");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        }
    }
}
