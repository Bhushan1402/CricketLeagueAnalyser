package com.cricket;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CricketLeagueAnalyserTest {

    private static final String MOST_RUNS_CSV ="./src/test/resources/csv/IPL2019FactsheetMostRuns.csv" ;

    @Test
    public void givenCSVFile_WhenTopBattingAverage_ShouldreturnTopPlayerName() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        cricketLeagueAnalyser.loadCricketData(MOST_RUNS_CSV);
        String averageWiseBySortedBatsManData = cricketLeagueAnalyser.getAverageWiseBySortedBatsManData();
        MostRunsCSV[] mostRunsCSV= new Gson().fromJson(averageWiseBySortedBatsManData, MostRunsCSV[].class);
        Assert.assertEquals(83.2,Double.valueOf(mostRunsCSV[0].average),0.0);
    }
}
