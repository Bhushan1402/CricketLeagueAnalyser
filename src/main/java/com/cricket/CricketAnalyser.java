package com.cricket;
import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CricketAnalyser {
    public enum PlayerType {IPL_BATSMAN_RUNS, IPL_BOWLER_WICKETS}

    Map<String, CricketDAO> iplCsvMap = new HashMap<>();
    Map<Sorted.SortByField, Comparator<CricketDAO>> fieldComparatorMap = null;
    Map<String, CricketDAO> allRounderMap = null;

    private IPLAdapter iplAdapter;

    public void setIplAdapter(IPLAdapter iplAdapter) {
        this.iplAdapter = iplAdapter;
    }


    public CricketAnalyser() {
        iplCsvMap = new HashMap<>();
        fieldComparatorMap = new HashMap<>();
        allRounderMap = new HashMap<>();
        fieldComparatorMap.put(Sorted.SortByField.AVERAGE, Comparator.comparing(iplData -> iplData.average, Comparator.reverseOrder()));
        Comparator<CricketDAO> average = Comparator.comparing(iplData -> iplData.average);
        fieldComparatorMap.put(Sorted.SortByField.AVERAGE_WITH_STRIKE_RATE, average.thenComparing(iplData -> iplData.strikeRate).reversed());
        fieldComparatorMap.put(Sorted.SortByField.STRIKE_RATE, Comparator.comparing(iplData -> iplData.strikeRate, Comparator.reverseOrder()));
        fieldComparatorMap.put(Sorted.SortByField.TOTAL_SIX_AND_FOUR, Comparator.comparing(iplData->iplData.noOfFours+iplData.noOfSixes));
        Comparator<CricketDAO> csvDtoComparator = Comparator.comparing(iplData -> iplData.strikeRate);
        fieldComparatorMap.put(Sorted.SortByField.BEST_STRIKE_WITH_TOTAL_SIX_AND_FOUR, csvDtoComparator.thenComparing(iplData->iplData.noOfFours+iplData.noOfSixes).reversed());
        Comparator<CricketDAO> maxRun = Comparator.comparing(iplData -> iplData.runs);
        fieldComparatorMap.put(Sorted.SortByField.MAX_RUN_WITH_BEST_AVG, maxRun.thenComparing(iplData -> iplData.average).reversed());

        fieldComparatorMap.put(Sorted.SortByField.BEST_BOWLER_AVERAGE, Comparator.comparing(field -> field.Bowler_Average, Comparator.reverseOrder()));
        Comparator<CricketDAO> BestBowlingAvgWithStrikeRate = Comparator.comparing(compare -> compare.Bowler_Average);
        fieldComparatorMap.put(Sorted.SortByField.BEST_BOWLING_AVERAGE_WITH_STRIKE_RATE, BestBowlingAvgWithStrikeRate.thenComparing(field -> field.Bowler_strikeRate));
        fieldComparatorMap.put(Sorted.SortByField.TOP_BOWLERS_STRIKE_RATE, Comparator.comparing(field -> field.Bowler_strikeRate, Comparator.reverseOrder()));
        fieldComparatorMap.put(Sorted.SortByField.BEST_ECONOMY_RATE, Comparator.comparing(field -> field.Bowler_Economy));
        Comparator<CricketDAO> fourAndFiveWickets = Comparator.comparing(iplData -> iplData.fiveWicket+iplData.fourWicket);
        fieldComparatorMap.put(Sorted.SortByField.BEST_STRIKING_RATE_WITH_5W_AND_4W, fourAndFiveWickets.thenComparing(field -> field.Bowler_strikeRate).reversed());
        Comparator<CricketDAO> maximumWickets = Comparator.comparing(field -> field.wickets, Comparator.reverseOrder());
        fieldComparatorMap.put(Sorted.SortByField.MAXIMUM_WICKETS_WITH_BEST_AVERAGE_RATE, maximumWickets.thenComparing(field -> field.Bowler_Average));

        fieldComparatorMap.put(Sorted.SortByField.BEST_BATTING_BOWLING_AVERAGE, new CompareAverage().reversed());
        fieldComparatorMap.put(Sorted.SortByField.ALL_ROUNDER_PLAYER, new AllRounders().reversed());

    }

    public int loadIPLCSVData(PlayerType playerType, String... csvFilePath) throws CricketAnalyserException {
        this.iplCsvMap = this.iplAdapter.loadIPLCSVData(playerType, csvFilePath);
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

    public static IPLAdapter getAdapterObject(PlayerType playerType) {
        return IPLAdapterFactory.getIPLPlayer(playerType);
    }
}