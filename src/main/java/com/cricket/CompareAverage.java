package com.cricket;

import java.util.Comparator;

public class CompareAverage implements Comparator<CricketDAO> {
    @Override
    public int compare(CricketDAO cricketDAO1, CricketDAO cricketDAO2) {
        return (int) ((cricketDAO1.average-(1d/cricketDAO1.Bowler_Average))-(cricketDAO2.average-(1d/cricketDAO2.Bowler_Average)));
    }
}
