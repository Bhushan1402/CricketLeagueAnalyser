package com.cricket;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CricketLeagueAnalyserTest {

    private static final String MOST_RUNS_CSV ="./src/test/resources/csv/IPL2019FactsheetMostRuns.csv" ;
    private static final String MOST_BALL_CSV ="src/test/resources/csv/IPL2019FactsheetMostWkts.csv" ;

    @Test
    public void givenMostRunsCSVFile_WhenTopBattingAverage_ShouldreturnTopPlayerName() throws CricketAnalyserException {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        cricketLeagueAnalyser.loadCricketData(MOST_RUNS_CSV);
        String averageWiseBySortedBatsManData = cricketLeagueAnalyser.getAverageWiseBySortedBatsManData();
        MostRunsCSV[] mostRunsCSV= new Gson().fromJson(averageWiseBySortedBatsManData, MostRunsCSV[].class);
        Assert.assertEquals(83.2,Double.valueOf(mostRunsCSV[0].average),0.0);
    }

    @Test
    public void givenMostRunsCSVFile_WhenTopBattingStrikeRate_ShouldreturnTopPlayerName() throws CricketAnalyserException {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        cricketLeagueAnalyser.loadCricketData(MOST_RUNS_CSV);
        String strikeRate = cricketLeagueAnalyser.getStrikeRateWiseBySortedBatsManData();
        MostRunsCSV[] mostRunsCSV= new Gson().fromJson(strikeRate, MostRunsCSV[].class);
        Assert.assertEquals(333.33,mostRunsCSV[0].strikeRate, 0.0);
    }

    @Test
    public void givenMostRunsCSVFile_WhenTopBattingSixes_ShouldreturnTopPlayerName() throws CricketAnalyserException {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        cricketLeagueAnalyser.loadCricketData(MOST_RUNS_CSV);
        String strikeRate = cricketLeagueAnalyser.getSixesWiseBySortedBatsManData();
        MostRunsCSV[] mostRunsCSV= new Gson().fromJson(strikeRate, MostRunsCSV[].class);
        Assert.assertEquals(83,mostRunsCSV[0].sixes+mostRunsCSV[0].fours);
    }

    @Test
    public void givenMostRunsCSVFile_WhenTopBattingFourse_ShouldreturnTopPlayerName() throws CricketAnalyserException {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        cricketLeagueAnalyser.loadCricketData(MOST_RUNS_CSV);
        String strikeRate = cricketLeagueAnalyser.getFoursWiseBySortedBatsManData();
        MostRunsCSV[] mostRunsCSV= new Gson().fromJson(strikeRate, MostRunsCSV[].class);
        Assert.assertEquals("Shikhar Dhawan",mostRunsCSV[0].playerName);
    }

    @Test
    public void givenMostRunsCSVFile_WhenTopBattingSixesAndFours_ShouldReturnTopPlayerName() throws CricketAnalyserException {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        cricketLeagueAnalyser.loadCricketData(MOST_RUNS_CSV);
        String sortedData = cricketLeagueAnalyser.getSixesFoursWiseBySortedBatsManData();
        MostRunsCSV[] mostRunsCSV= new Gson().fromJson(sortedData, MostRunsCSV[].class);
        Assert.assertEquals(204.81,mostRunsCSV[0].strikeRate,0.0);
    }

    @Test
    public void givenMostRunsCSVFile_WhenMaximumAverage_ShouldreturnStrikeRate() throws CricketAnalyserException {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        cricketLeagueAnalyser.loadCricketData(MOST_RUNS_CSV);
        String averageWiseBySortedBatsManData = cricketLeagueAnalyser.getAverageWiseBySortedBatsManData();
        MostRunsCSV[] mostRunsCSV= new Gson().fromJson(averageWiseBySortedBatsManData, MostRunsCSV[].class);
        Assert.assertEquals(134.62,mostRunsCSV[0].strikeRate,0.0);
    }

    @Test
    public void givenMostRunsCSVFile_WhenTopBattingRuns_ShouldreturnAverage() throws CricketAnalyserException {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        cricketLeagueAnalyser.loadCricketData(MOST_RUNS_CSV);
        String averageWiseBySortedBatsManData = cricketLeagueAnalyser.getRunsWiseBySortedAverageBatsManData();
        MostRunsCSV[] mostRunsCSV= new Gson().fromJson(averageWiseBySortedBatsManData, MostRunsCSV[].class);
        Assert.assertEquals(69.2,Double.valueOf(mostRunsCSV[0].average),0.0);
    }


//    @Test
//    public void givenMostRunsCSVFile_WhenTopBowlingAverage_ShouldreturnTopPlayerName()  {
//        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
//        cricketLeagueAnalyser.loadCricketData(MOST_RUNS_CSV,MOST_BALL_CSV);
//        String averageWiseBySortedBatsManData = cricketLeagueAnalyser.getAverageWiseBySortedBowlerData();
//        MostWktsCSV[] mostBowlingCSVS= new Gson().fromJson(averageWiseBySortedBatsManData, MostWktsCSV[].class);
//        Assert.assertEquals("Krishnappa Gowtham",mostBowlingCSVS[0].playerName);
//    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Test//uc7
public void givenMostWicketsCSVFile_WhenTopBowlingAverage_ShouldReturnTopPlayerName(){
    CricketLeagueAnalyser cricketAnalyser=new CricketLeagueAnalyser();
    cricketAnalyser.loadCricketData(MOST_RUNS_CSV,MOST_BALL_CSV);
    String sortedData = cricketAnalyser.getAverageWiseSortedBowlersData();
    MostWktsCSV[] mostWktsCSV = new Gson().fromJson(sortedData, MostWktsCSV[].class);
    Assert.assertEquals("Krishnappa Gowtham",mostWktsCSV [0].playerName);
}
  @Test//uc8
    public void givenMostWicketsCSVFile_WhenTopBowlingStrikeRate_ShouldReturnTopPlayerStrikeRate(){
        CricketLeagueAnalyser cricketAnalyser=new CricketLeagueAnalyser();
        cricketAnalyser.loadCricketData(MOST_RUNS_CSV,MOST_BALL_CSV);
        String sortedData = cricketAnalyser.getStrikeRateWiseSortedBowlersData();
        MostWktsCSV[] mostWktsCSV = new Gson().fromJson(sortedData, MostWktsCSV[].class);
        Assert.assertEquals("Krishnappa Gowtham",mostWktsCSV[0].playerName);
   }
   @Test//uc9
    public void givenMostWicketsCSVFile_WhenTopBowlingStrikeRate_ShouldReturnTopPlayerEconomy(){
        CricketLeagueAnalyser cricketAnalyser=new CricketLeagueAnalyser();
        cricketAnalyser.loadCricketData(MOST_RUNS_CSV,MOST_BALL_CSV);
        String sortedData = cricketAnalyser.getBestEconomyRateWiseSortedBowlersData();
        MostWktsCSV[] mostWktsCSV = new Gson().fromJson(sortedData, MostWktsCSV[].class);
        Assert.assertEquals("Ben Cutting",mostWktsCSV[0].playerName);
    }
      @Test//uc10
    public void givenMostWicketsCSVFile_SortedByBestStrikeRateWithMore4wAnd5w_ShouldReturnTopPlayerName() {
        CricketLeagueAnalyser cricketAnalyser=new CricketLeagueAnalyser();
        cricketAnalyser.loadCricketData(MOST_RUNS_CSV,MOST_BALL_CSV);
        String sortedData = cricketAnalyser.getBestStrikeRateWith4wAnd5wWiseSortedBowlersData();
        MostRunsCSV[] mostRunsCSV = new Gson().fromJson(sortedData, MostRunsCSV[].class);
        Assert.assertEquals("Krishnappa Gowtham",mostRunsCSV[0].playerName);
    }
  /*  @Test//uc11
    public void givenMostWicketsCSVFile_SortedByBestAverageWithStrikeRate_ShouldReturnTopPlayerName() {
        CricketAnalyser cricketAnalyser=new CricketAnalyser();
        cricketAnalyser.loadCricketData(MOST_RUNS_CSV,MOST_WKTS_CSV);
        String sortedData = cricketAnalyser.getMaximumWicketsWithBestStrikeRateWiseSortedBowlersData();
        MostRunsCSV[] mostRunsCSV = new Gson().fromJson(sortedData, MostRunsCSV[].class);
        Assert.assertEquals("Krishnappa Gowtham",mostRunsCSV[0].playerName);
    }
    @Test//uc12
    public void givenMostWicketsCSVFile_SortedByBestWicketsWithAverage_ShouldReturnTopPlayerName() {
        CricketAnalyser cricketAnalyser=new CricketAnalyser();
        cricketAnalyser.loadCricketData(MOST_RUNS_CSV,MOST_WKTS_CSV);
        String sortedData = cricketAnalyser.getMaximumWicketsWithBestAverageWiseSortedBowlersData();
        MostRunsCSV[] mostRunsCSV = new Gson().fromJson(sortedData, MostRunsCSV[].class);
        Assert.assertEquals("Imran Tahir",mostRunsCSV[0].playerName);
    }
*/

}
