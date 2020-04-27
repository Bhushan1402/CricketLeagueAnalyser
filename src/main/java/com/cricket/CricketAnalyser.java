package com.cricket;
import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CricketAnalyser {

    private MockitoAdapter adapter;

    public void setAdapter(PlayerType playerType, MockitoAdapter adapter){
        this.adapter=adapter;
    }
    public enum PlayerType {
        IPL_BATSMAN_RUNS, IPL_BOWLER_WICKETS
    }

    public Map<String, CricketDAO> loadData(PlayerType playerType, String csvFilePath){
        iplCsvMap=this.adapter.loadData(csvFilePath);
        return iplCsvMap;
    }

    Map<String, CricketDAO> iplCsvMap = new HashMap<>();
    Map<Sorted.SortByField, Comparator<CricketDAO>> fieldComparatorMap = null;
    Map<String, CricketDAO> allRounderMap = null;

    private IPLAdapter iplAdapter;

    public void setIplAdapter(IPLAdapter iplAdapter) {
        this.iplAdapter = iplAdapter;
    }

    public CricketAnalyser() {
        iplCsvMap = new HashMap<>();
        fieldComparatorMap = new Sorted().getFieldComparator();


    }

    public int loadIPLCSVData(PlayerType playerType, String... csvFilePath) throws CricketAnalyserException {
        this.iplCsvMap = IPLAdapterFactory.loadIPLCSVData(playerType, csvFilePath);
        return iplCsvMap.size();
    }

    public String sortByFields(Sorted.SortByField fields) throws CricketAnalyserException {
        if (iplCsvMap == null || iplCsvMap.size() == 0) {
            throw new CricketAnalyserException("No IPl Data", CricketAnalyserException.ExceptionType.NO_IPL_DATA);
        }

        ArrayList list = this.iplCsvMap.values().stream()
                .sorted(fieldComparatorMap.get(fields))
                .collect(Collectors.toCollection(ArrayList::new));
        String sortIplCsvToJson = new Gson().toJson(list);
        return sortIplCsvToJson;
    }
}