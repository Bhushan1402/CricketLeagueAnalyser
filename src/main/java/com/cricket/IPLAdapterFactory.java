package com.cricket;

public class IPLAdapterFactory {
    public static IPLAdapter getIPLPlayer(CricketAnalyser.PlayerType playerType) {
        if(playerType.equals(CricketAnalyser.PlayerType.IPL_BATSMAN_RUNS))
            return new BatsManRunsAdapter();
        return new BowlerWicketAdapter();
    }
}
