package com.cricket;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toCollection;

public class CricketLeagueAnalyser {

    public Map<String, CricketDAO> cricketMap=null;

    public CricketLeagueAnalyser() {
        this.cricketMap = new HashMap<>();
    }

    public void loadCricketData(String csvFilePath){
       cricketMap = new CricketAdapterFactory().grtCricketData(csvFilePath);
        System.out.println(cricketMap);
    }

    public String getAverageWiseBySortedBatsManData(){
        Comparator<CricketDAO> cricketDAOComparator=Comparator.comparing(cricketDAO->cricketDAO.average);
        String jsonData = this.getDescendingOrder(cricketDAOComparator);
        return jsonData;
    }

    public String getDescendingOrder(Comparator<CricketDAO> comparator){
        ArrayList cricketDTOS= cricketMap.values().stream().sorted(comparator.reversed())
                .map(cricketDAO ->cricketDAO.getCricketDTO())
                .collect(toCollection(ArrayList::new));
        return new Gson().toJson(cricketDTOS);
    }
}
