package com.clayoverwind.toolbox.sql;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wangweiwei on 2017/3/15.
 */
public class DataBaseImportUtil {

    private DataBaseImportUtil(){}

    public static class DatabaseField {
        private String fieldName;

        private String fieldType;

        private String fieldValue;

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getFieldType() {
            return fieldType;
        }

        public void setFieldType(String fieldType) {
            this.fieldType = fieldType;
        }

        public String getFieldValue() {
            return fieldValue;
        }

        public void setFieldValue(String fieldValue) {
            this.fieldValue = fieldValue;
        }
    }

    /**
     * load DatabaseField from a table in mysql database(useUnicode=true & characterEncoding=UTF-8)
     * @param databaseHost
     * @param databasePort
     * @param databaseName
     * @param databaseUserName
     * @param databasePassword
     * @param tableName
     * @return
     */
    public static List<DatabaseField> loadTableFields(String databaseHost, Integer databasePort, String databaseName, String databaseUserName, String databasePassword, String tableName) {
        List<DatabaseField> res = new LinkedList<DatabaseField>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            StringBuilder databaseAddress = new StringBuilder("jdbc:mysql://")
                    .append(databaseHost).append(":").append(databasePort)
                    .append("/").append(databaseName)
                    .append("?useUnicode=true&characterEncoding=UTF-8");
            java.sql.Connection connection = DriverManager.getConnection(databaseAddress.toString(), databaseUserName, databasePassword);
            DatabaseMetaData dbMetaData = connection.getMetaData();
            ResultSet colRet = dbMetaData.getColumns(null, "%", tableName, "%");
            res = findFieldsFromColSet(colRet);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return res;
    }

    private static List<DatabaseField> findFieldsFromColSet(ResultSet colRet) throws SQLException {
        List<DatabaseField> res = new LinkedList<DatabaseField>();
        while (colRet.next()) {
            String columnName = colRet.getString("COLUMN_NAME");
            String columnType = colRet.getString("TYPE_NAME");
            int datasize = colRet.getInt("COLUMN_SIZE");
            int digits = colRet.getInt("DECIMAL_DIGITS");
            int nullable = colRet.getInt("NULLABLE");
            DatabaseField dataMapField = new DatabaseField();
            dataMapField.setFieldName(columnName);
            dataMapField.setFieldType(columnType);
            res.add(dataMapField);
        }
        return res;
    }
}
