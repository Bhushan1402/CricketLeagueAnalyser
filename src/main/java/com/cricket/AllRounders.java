package com.cricket;

import java.util.Comparator;
import java.util.Iterator;

public class AllRounders implements Comparator<CricketDAO> {
    @Override
    public int compare(CricketDAO cricketDAO1, CricketDAO cricketDAO2) {
        return ((cricketDAO1.runs*(cricketDAO1.wickets))- (cricketDAO2.runs*(cricketDAO2.wickets)));
    }
}
