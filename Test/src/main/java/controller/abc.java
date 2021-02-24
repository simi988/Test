package controller;

import dao.DAO;
import model.Aparat;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class abc {
    private LocalDate date = LocalDate.now();
    DAO dao = new DAO();

    public Aparat createAparat(String clasa) {
        return new Aparat(3, 3, clasa, date, date, date, date, date);
    }

    public Map<Integer, List<Integer>> ap(int an) throws SQLException {
        dao.startConnection();
        Map<Integer, List<Integer>> integerListMapInceput = dao.aparatListDtInceput(an);
        Map<Integer, List<Integer>> integerListMapSfarsit = dao.aparatListDtSfarsit(an);
        Map<Integer, List<Integer>> integerListMap = new HashMap<>();

        int year = Calendar.getInstance().get(Calendar.YEAR);
        if (year != an) {
            return integerListMapInceput;
        } else {
            int month = Calendar.MONTH;
            for (Map.Entry<Integer, List<Integer>> integerListEntryInceput : integerListMapInceput.entrySet()) {
                if (integerListEntryInceput.getKey() <= month) {
                    integerListMap.put(integerListEntryInceput.getKey(), integerListEntryInceput.getValue());
                }
            }
            for (Map.Entry<Integer, List<Integer>> integerListEntrySfarsit : integerListMapSfarsit.entrySet()) {
                if (integerListEntrySfarsit.getKey() > month) {
                    integerListMap.put(integerListEntrySfarsit.getKey(), integerListEntrySfarsit.getValue());
                }
            }
            return integerListMap;
        }
    }

    public Aparat serie(String serie) throws SQLException {
        dao.startConnection();
        return dao.aparatSerie(serie);
    }

    public Aparat deleteSerie(String serie) throws SQLException {
        dao.startConnection();
        return dao.deleteSerie(serie);
    }
}
