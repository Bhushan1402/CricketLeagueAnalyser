package com.cricket;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Sorted {
    public enum SortByField {

        AVERAGE,STRIKE_RATE,TOTAL_SIX_AND_FOUR,BEST_STRIKE_WITH_TOTAL_SIX_AND_FOUR,AVERAGE_WITH_STRIKE_RATE,
        MAX_RUN_WITH_BEST_AVG,BEST_BOWLER_AVERAGE,TOP_BOWLERS_STRIKE_RATE,BEST_ECONOMY_RATE,BEST_STRIKING_RATE_WITH_5W_AND_4W,
        BEST_BOWLING_AVERAGE_WITH_STRIKE_RATE,MAXIMUM_WICKETS_WITH_BEST_AVERAGE_RATE, BEST_BATTING_BOWLING_AVERAGE,ALL_ROUNDER_PLAYER
    }


    public Map<SortByField, Comparator<CricketDAO>> getFieldComparator() {
         Map<Sorted.SortByField, Comparator<CricketDAO>> fieldComparatorMap = new HashMap<>();


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
        return fieldComparatorMap;
    }
}
