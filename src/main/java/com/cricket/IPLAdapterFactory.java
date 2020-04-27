package com.cricket;

import java.util.Map;

public class IPLAdapterFactory {

    public static <E> Map<String, CricketDAO> loadIPLCSVData(CricketAnalyser.PlayerType playerType, String... filePath) throws CricketAnalyserException {
        if (playerType == CricketAnalyser.PlayerType.IPL_BATSMAN_RUNS)
            return new BatsManRunsAdapter().loadIPLCSVData(playerType,filePath);
        return new BowlerWicketAdapter().loadIPLCSVData(playerType,filePath);
    }
}
