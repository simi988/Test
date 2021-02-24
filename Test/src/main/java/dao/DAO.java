package dao;

import model.Aparat;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.err;

public class DAO {
    private static final String DB_URL = "jdbc:mysql://red77.ro:62273/brunersrl";
    private static final String USERNAME = "simion";
    private static final String PASSWORD = "simion7";
    Connection connection = null;

    public void startConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

        } catch (SQLException exception) {
            err.println(exception.getMessage());
        }
    }

    public void insert(Aparat aparat) {
        String insert = "INSERT INTO AUTORIZATII VALUES(";
        String insertAparat = getInsertAparat(insert, aparat);
        PreparedStatement preparedStatement = createPreparedStatement(insertAparat);
        executeUpdate(preparedStatement);
    }

    public void delete(int id) {
        String delete = "DELETE FROM AUTORIZATII WHERE idAutorizatie=" + id;
        PreparedStatement preparedStatement = createPreparedStatement(delete);
        executeUpdate(preparedStatement);
    }

    public void update(int id, Aparat aparat) {
        String update = "UPDATE AUTORIZATII SET clasa='" + aparat.getClasa() + "' WHERE idAutorizatie=" + id;
        PreparedStatement preparedStatement = createPreparedStatement(update);
        executeUpdate(preparedStatement);
    }

    public Map<Integer, List<Integer>> aparatListDtInceput(int an) throws SQLException {
        Map<Integer, List<Integer>> integerListMap = new HashMap<>();
        String select = "SELECT MONTH(dtInceputAutorizatie)  AS Month," +
                "  COUNT(CASE WHEN idOperator = 1 AND clasa = 'A' THEN 1 END) AS 'AmperaA'," +
                "  COUNT(CASE WHEN idOperator = 1 AND clasa = 'B' THEN 1 END) AS 'AmperaB'," +
                "  COUNT(CASE WHEN idOperator = 2 AND clasa = 'A' THEN 1 END) AS 'RedlongA'," +
                "  COUNT(CASE WHEN idOperator = 2 AND clasa = 'B' THEN 1 END) AS 'RedlongB'" +
                "FROM  autorizatii WHERE  YEAR(dtInceputAutorizatie) =" + an +
                " GROUP BY MONTH(dtInceputAutorizatie) ORDER BY dtInceputAutorizatie ASC";
        return getIntegerListMap(integerListMap, select);
    }

    public Map<Integer, List<Integer>> aparatListDtSfarsit(int an) throws SQLException {
        Map<Integer, List<Integer>> integerListMap = new HashMap<>();
        String select = "SELECT MONTH(dtSfarsitAutorizatie)  AS Month," +
                "  COUNT(CASE WHEN idOperator = 1 AND clasa = 'A' THEN 1 END) AS 'AmperaA'," +
                "  COUNT(CASE WHEN idOperator = 1 AND clasa = 'B' THEN 1 END) AS 'AmperaB'," +
                "  COUNT(CASE WHEN idOperator = 2 AND clasa = 'A' THEN 1 END) AS 'RedlongA'," +
                "  COUNT(CASE WHEN idOperator = 2 AND clasa = 'B' THEN 1 END) AS 'RedlongB'" +
                "FROM  autorizatii WHERE  YEAR(dtSfarsitAutorizatie) =" + an +
                " GROUP BY MONTH(dtSfarsitAutorizatie) ORDER BY dtSfarsitAutorizatie ASC";
        return getIntegerListMap(integerListMap, select);
    }

    public Aparat aparatSerie(String serie) throws SQLException {
        Aparat aparat = new Aparat();
        String select = "SELECT * FROM aparate WHERE seria='" + serie+"'";
        PreparedStatement preparedStatement = createPreparedStatement(select);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            aparat.setIdAparatFix(resultSet.getInt("idAparatFix"));
            aparat.setSerie(resultSet.getString("seria"));
        }
        else{
            aparat.setIdAparatFix(resultSet.getInt("idAparatFix"));
            aparat.setSerie(resultSet.getString("seria"));
        }
        return aparat;

    }

    public Aparat deleteSerie(String serie) throws SQLException {
        Aparat aparat = new Aparat();
        String select = "DELETE  FROM  WHERE seria='" + serie+"'";
        PreparedStatement preparedStatement = createPreparedStatement(select);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            aparat.setIdAparatFix(resultSet.getInt("idAparatFix"));
            aparat.setSerie(resultSet.getString("seria"));
        }
        else{
            aparat.setIdAparatFix(resultSet.getInt("idAparatFix"));
            aparat.setSerie(resultSet.getString("seria"));
        }
        return aparat;

    }

    private Map<Integer, List<Integer>> getIntegerListMap(Map<Integer, List<Integer>> integerListMap, String select) throws SQLException {
        PreparedStatement preparedStatement = createPreparedStatement(select);
        ResultSet resultSet = preparedStatement.executeQuery();


        while (resultSet.next()) {
            int month = resultSet.getInt("Month");
            List<Integer> list = new ArrayList<>();
            list.add(resultSet.getInt(2));
            list.add(resultSet.getInt(3));
            list.add(resultSet.getInt(4));
            list.add(resultSet.getInt(5));
            integerListMap.put(month, list);

        }
        return integerListMap;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException exception) {
            err.println(exception.getMessage());
        }
    }

    private String getInsertAparat(String insert, Aparat aparat) {
        insert += "'" + aparat.getIdAutorizatie() + "',";
        insert += "'" + aparat.getIdAparatFix() + "',";
        insert += "'" + aparat.getIdOperator() + "',";
        insert += "'" + aparat.getClasa() + "',";
        insert += "'" + aparat.getNrCerereAutorizatie() + "',";
        insert += "'" + aparat.getNrDecizie() + "',";
        insert += "'" + aparat.getDtSolicitareAutorizatie() + "',";
        insert += "'" + aparat.getDtDecizie() + "',";
        insert += "'" + aparat.getDtInceputAutorizatie() + "',";
        insert += "'" + aparat.getDtSfarsitAutorizatie() + "',";
        insert += "'" + aparat.getPrelungeste() + "',";
        insert += "'" + aparat.getDtServer() + "')";

        return insert;
    }

    private void executeUpdate(PreparedStatement preparedStatement) {
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            err.println(exception.getMessage());
        }
    }

    private PreparedStatement createPreparedStatement(String query) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
        } catch (SQLException exception) {
            err.println(exception.getMessage());
        }
        return preparedStatement;
    }
}
