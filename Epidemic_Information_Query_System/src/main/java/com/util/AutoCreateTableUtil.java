/**
 * 自动创建表
 */
package com.util;

import com.Annotation.Column;
import com.Annotation.Table;
import com.Annotation.TableConstraint;

import java.lang.reflect.Field;

public class AutoCreateTableUtil {
    private AutoCreateTableUtil() {
    }

    /**
     * 生成单张表
     * @param className
     * @return
     */
    public static String createTable(String className) {
        StringBuffer s = new StringBuffer("create table ");
        Class objectClass = null;
        try {
            objectClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Table table = (Table) objectClass.getAnnotation(Table.class);
        s.append(table.tableName() + " (\n");
        Field[] fields = objectClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = (Column) field.getAnnotation(Column.class);
                String columnName = column.columnName();
                s.append(columnName + " ");
                String dataType = column.dataType();
                s.append(dataType + "");
                int dataLength = column.dataLength();
                s.append("(");
                s.append(dataLength);
                s.append(") ");
                String constraint = column.constraint();
                s.append(constraint);
                s.append(",\n");
            }

        }
        String sql = s.substring(0, s.length() - 2);
        if (objectClass.isAnnotationPresent(TableConstraint.class)) {
            TableConstraint tableConstraint = (TableConstraint) objectClass.getAnnotation(TableConstraint.class);

            sql = sql + ",\n" + tableConstraint.tableConstraint();
        }
        sql = sql + "\n);";
        return sql;

    }

    /**
     * 生成多张表
     * @param classname
     * @return
     */
    public static String createTables(String... classname) {
        String sql = "";
        for (String s : classname) {
            sql = sql + createTable(s) + "\n";
        }
        return sql;
    }
}
