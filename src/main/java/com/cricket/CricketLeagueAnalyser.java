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

    public void loadCricketData(String... csvFilePath) {
       cricketMap = new CricketAdapterFactory().getCricketData(csvFilePath);
    }
/*
public String getJsonInDescendingOrder(Comparator<CricketDAO> comparator,String...type){
        if (type.length==0){
            ArrayList cricketDTOS=cricketMap.values().stream().sorted(comparator.reversed())
                    .map(cricketDAO -> cricketDAO.getCricketDTO())
                    .collect(toCollection(ArrayList::new));
            return new Gson().toJson(cricketDTOS);
        }*/
    public String getAverageWiseBySortedBatsManData(){
        Comparator<CricketDAO> cricketDAOComparator=Comparator.comparing(cricketDAO->cricketDAO.average);
        String jsonData = this.getDescendingOrder(cricketDAOComparator);
        return jsonData;
    }

    public String getStrikeRateWiseBySortedBatsManData(){
        Comparator<CricketDAO> cricketDAOComparator=Comparator.comparing(cricketDAO->cricketDAO.strikeRate);
        String jsonData = this.getDescendingOrder(cricketDAOComparator);
        return jsonData;
    }

    public String getSixesWiseBySortedBatsManData() {
        Comparator<CricketDAO> cricketDAOComparator = Comparator.comparing(cricketDAO -> cricketDAO.sixes);
        String jsonData = this.getDescendingOrder(cricketDAOComparator);
        return jsonData;
    }

    public String getFoursWiseBySortedBatsManData() {
        Comparator<CricketDAO> cricketDAOComparator = Comparator.comparing(cricketDAO -> cricketDAO.fours);
        String jsonData = this.getDescendingOrder(cricketDAOComparator);
        return jsonData;
    }

    public String getSixesFoursWiseBySortedBatsManData() {
        Comparator<CricketDAO> cricketDAOComparator = Comparator.comparing(cricketDAO -> cricketDAO.fours+cricketDAO.sixes);
        String jsonData = this.getDescendingOrder(cricketDAOComparator);
        return jsonData;
    }
    public String getRunsWiseBySortedAverageBatsManData() {
        Comparator<CricketDAO> cricketDAOComparator = Comparator.comparing(cricketDAO -> cricketDAO.runs);
        String jsonData = this.getDescendingOrder(cricketDAOComparator);
        return jsonData;
    }

    public String getDescendingOrder(Comparator<CricketDAO> comparator,String... type){
        if (type.length==0) {
            ArrayList cricketDTOS = cricketMap.values().stream().sorted(comparator.reversed())
                    .map(cricketDAO -> cricketDAO.getCricketDTO())
                    .collect(toCollection(ArrayList::new));
            return new Gson().toJson(cricketDTOS);
        }
        ArrayList cricketDTOS = cricketMap.values().stream().sorted(comparator.reversed())
                .map(cricketDAO -> cricketDAO.getCricketDTO("type"))
                .collect(toCollection(ArrayList::new));
        return new Gson().toJson(cricketDTOS);
    }

//    public String getAverageWiseBySortedBowlerData(){
//        Comparator<CricketDAO> cricketDAOComparator=Comparator.comparing(cricketDAO->cricketDAO.average);
//        String jsonData = this.getDescendingOrder(cricketDAOComparator,"Wickets");
//        return jsonData;
//    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //uc7

    public String getAverageWiseSortedBowlersData() {
        Comparator<CricketDAO> cricketComparator=Comparator.comparing(cricketDAO ->cricketDAO.average);
        String jsonData = this.getDescendingOrder(cricketComparator,"Wickets");
        return jsonData;
    }//uc8
   /* public String getStrikeRateWiseSortedBowlersData() {
        Comparator<CricketDAO> cricketComparator=Comparator.comparing(cricketDAO ->cricketDAO.strikeRate);
        String jsonData = this.getJsonInDescendingOrder(cricketComparator,"wickets");
        return jsonData;
    }//uc9
    public String getBestEconomyRateWiseSortedBowlersData() {
        Comparator<CricketDAO> cricketComparator=Comparator.comparing(cricketDAO ->cricketDAO.economy);
        String jsonData = this.getJsonInDescendingOrder(cricketComparator,"wickets");
        return jsonData;
    }//uc10
    public String getBestStrikeRateWith4wAnd5wWiseSortedBowlersData() {
        Comparator<CricketDAO> cricketComparator=Comparator.comparing(CricketDAO::getStrikeRate).
                thenComparing(cricketDAO ->cricketDAO.fourWickets+cricketDAO.fiveWickets);
        String jsonData = this.getJsonInDescendingOrder(cricketComparator,"bhushan");
        return jsonData;
    }//uc11
    public String getMaximumWicketsWithBestStrikeRateWiseSortedBowlersData() {
        Comparator<CricketDAO> cricketComparator=Comparator.comparing(CricketDAO::getAverage).
                thenComparing(cricketDAO ->cricketDAO.strikeRate);
        String jsonData = this.getJsonInDescendingOrder(cricketComparator,"bhushan");
        return jsonData;
    }//uc12
    public String getMaximumWicketsWithBestAverageWiseSortedBowlersData() {
        Comparator<CricketDAO> cricketComparator=Comparator.comparing(CricketDAO::getWickets).
                thenComparing(cricketDAO ->cricketDAO.average);
        String jsonData = this.getJsonInDescendingOrder(cricketComparator,"bhushan");
        return jsonData;
    }
*/

}
