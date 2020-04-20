package com.cricket;

import java.util.Map;

public class BowlerWicketAdapter extends IPLAdapter {
    @Override
    public Map<String, CricketDAO> loadIPLCSVData(CricketAnalyser.PlayerType playerType, String... csvFilePath) throws CricketAnalyserException {
        Map<String, CricketDAO> map = super.loadIPLCSVData(MostWicketsCSV.class, csvFilePath[0]);
        return map;
    }
}
