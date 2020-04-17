package com.cricket;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CricketLeagueAnalyserTest {

    private static final String MOST_RUNS_CSV ="./src/test/resources/csv/IPL2019FactsheetMostRuns.csv" ;

    @Test
    public void givenMostRunsCSVFile_WhenTopBattingAverage_ShouldreturnTopPlayerName() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        cricketLeagueAnalyser.loadCricketData(MOST_RUNS_CSV);
        String averageWiseBySortedBatsManData = cricketLeagueAnalyser.getAverageWiseBySortedBatsManData();
        MostRunsCSV[] mostRunsCSV= new Gson().fromJson(averageWiseBySortedBatsManData, MostRunsCSV[].class);
        Assert.assertEquals(83.2,Double.valueOf(mostRunsCSV[0].average),0.0);
    }

    @Test
    public void givenMostRunsCSVFile_WhenTopBattingStrikeRate_ShouldreturnTopPlayerName() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        cricketLeagueAnalyser.loadCricketData(MOST_RUNS_CSV);
        String strikeRate = cricketLeagueAnalyser.getStrikeRateWiseBySortedBatsManData();
        MostRunsCSV[] mostRunsCSV= new Gson().fromJson(strikeRate, MostRunsCSV[].class);
        Assert.assertEquals(333.33,mostRunsCSV[0].strikeRate, 0.0);
    }

    @Test
    public void givenMostRunsCSVFile_WhenTopBattingSixes_ShouldreturnTopPlayerName() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        cricketLeagueAnalyser.loadCricketData(MOST_RUNS_CSV);
        String strikeRate = cricketLeagueAnalyser.getSixesWiseBySortedBatsManData();
        MostRunsCSV[] mostRunsCSV= new Gson().fromJson(strikeRate, MostRunsCSV[].class);
        Assert.assertEquals(83,mostRunsCSV[0].sixes+mostRunsCSV[0].fours);
    }

    @Test
    public void givenMostRunsCSVFile_WhenTopBattingFourse_ShouldreturnTopPlayerName() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        cricketLeagueAnalyser.loadCricketData(MOST_RUNS_CSV);
        String strikeRate = cricketLeagueAnalyser.getFoursWiseBySortedBatsManData();
        MostRunsCSV[] mostRunsCSV= new Gson().fromJson(strikeRate, MostRunsCSV[].class);
        Assert.assertEquals("Shikhar Dhawan",mostRunsCSV[0].playerName);
    }

    @Test
    public void givenMostRunsCSVFile_WhenTopBattingSixesAndFours_ShouldReturnTopPlayerName() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        cricketLeagueAnalyser.loadCricketData(MOST_RUNS_CSV);
        String sortedData = cricketLeagueAnalyser.getSixesFoursWiseBySortedBatsManData();
        MostRunsCSV[] mostRunsCSV= new Gson().fromJson(sortedData, MostRunsCSV[].class);
        Assert.assertEquals(204.81,mostRunsCSV[0].strikeRate,0.0);
    }

    @Test
    public void givenMostRunsCSVFile_WhenMaximumAverage_ShouldreturnStrikeRate() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        cricketLeagueAnalyser.loadCricketData(MOST_RUNS_CSV);
        String averageWiseBySortedBatsManData = cricketLeagueAnalyser.getAverageWiseBySortedBatsManData();
        MostRunsCSV[] mostRunsCSV= new Gson().fromJson(averageWiseBySortedBatsManData, MostRunsCSV[].class);
        Assert.assertEquals(134.62,mostRunsCSV[0].strikeRate,0.0);
    }

}
