package com.cricket;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
public class CricketAnalyserTest {
    private static final String IPL2019_RUNS_CSV_FILE_PATH = "./src/test/resources/csv/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String DELIMETER_MISSING_IPL_MOST_RUN_FILE = "./src/test/resources/delimeterMissingIPL2019MostRuns.csv";
    private static final String HEADER_MISSING_IPL_MOST_RUN_FILE = "./src/test/resources/delimeterMissingIPL2019MostRuns.csv";
    private static final String IPL2019_WICKET_CSV_FILE_PATH = "./src/test/resources/csv/IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenIPLRunsData_ShouldReturnExactCount() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            int count = cricketAnalyser.loadIPLCSVData(CricketAnalyser.PlayerType.IPL_BATSMAN_RUNS,IPL2019_RUNS_CSV_FILE_PATH);
            Assert.assertEquals(100, count);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenIPLRunsData_WithWrongFile_ShouldThrowException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadIPLCSVData(CricketAnalyser.PlayerType.IPL_BATSMAN_RUNS,WRONG_CSV_FILE_PATH);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }
    @Test
    public void givenIPLRunsData_WithIncorrectDelimiter_ShouldThrowException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadIPLCSVData(CricketAnalyser.PlayerType.IPL_BATSMAN_RUNS,DELIMETER_MISSING_IPL_MOST_RUN_FILE);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }
    @Test
    public void givenIPLRunsData_WithIncorrectHeader_ShouldThrowException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadIPLCSVData(CricketAnalyser.PlayerType.IPL_BATSMAN_RUNS,HEADER_MISSING_IPL_MOST_RUN_FILE);
        } catch ( CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }
    //UC 1
    @Test
    public void givenIPLRunsData_IfSortedByBattingAverage_ShouldReturn_HighestBattingAverage_Player() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadIPLCSVData(CricketAnalyser.PlayerType.IPL_BATSMAN_RUNS,IPL2019_RUNS_CSV_FILE_PATH);
            String sortedResult = cricketAnalyser.sortByFields(Sorted.SortByField.AVERAGE);
            MostRunsCSV[] iplRuns = new Gson().fromJson(sortedResult, MostRunsCSV[].class);
            Assert.assertEquals("MS Dhoni", iplRuns[0].playerName);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }
    //UC 2
    @Test
    public void givenIPLRunsData_IfSortedByBattingWithStrike_Rate_ShouldReturn_TopStriking_Rate_Player() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadIPLCSVData(CricketAnalyser.PlayerType.IPL_BATSMAN_RUNS,IPL2019_RUNS_CSV_FILE_PATH);
            String sortedResult = cricketAnalyser.sortByFields(Sorted.SortByField.STRIKE_RATE);
            MostRunsCSV[] iplRuns = new Gson().fromJson(sortedResult, MostRunsCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplRuns[0].playerName);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
        }

    }

    //UC 3
    @Test
    public void givenIPLRunsData_IfSortedBy_HitMaximum4sAnd6s_ShouldReturn_Player() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadIPLCSVData(CricketAnalyser.PlayerType.IPL_BATSMAN_RUNS,IPL2019_RUNS_CSV_FILE_PATH);
            String sortedResult = cricketAnalyser.sortByFields(Sorted.SortByField.TOTAL_SIX_AND_FOUR);
            MostRunsCSV[] iplRuns = new Gson().fromJson(sortedResult, MostRunsCSV[].class);
            Assert.assertEquals("Andre Russell", iplRuns[iplRuns.length - 1].playerName);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
        }

    }
    //UC 4
    @Test
    public void givenIPLRunsData_IfSortedBestStrikingRateWith_4sAnd6s_ShouldReturnPlayer() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadIPLCSVData(CricketAnalyser.PlayerType.IPL_BATSMAN_RUNS,IPL2019_RUNS_CSV_FILE_PATH);
            String sortedResult = cricketAnalyser.sortByFields(Sorted.SortByField.BEST_STRIKE_WITH_TOTAL_SIX_AND_FOUR);
            MostRunsCSV[] iplRuns = new Gson().fromJson(sortedResult, MostRunsCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplRuns[0].playerName);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
        }

    }
//UC 5
    @Test
    public void givenIPLRunsData_IfSortedBestAverageWith_bestStrikingRateWith_ShouldReturnPlayer() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadIPLCSVData(CricketAnalyser.PlayerType.IPL_BATSMAN_RUNS,IPL2019_RUNS_CSV_FILE_PATH);
            String sortedResult = cricketAnalyser.sortByFields(Sorted.SortByField.AVERAGE_WITH_STRIKE_RATE);
            MostRunsCSV[] iplRuns = new Gson().fromJson(sortedResult, MostRunsCSV[].class);
            Assert.assertEquals("MS Dhoni", iplRuns[0].playerName);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
        }

    }
//UC 6
    @Test
    public void givenIPLRunsData_IfSortedMaximumRuns_WithBestAverage_ShouldReturnPlayer() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadIPLCSVData(CricketAnalyser.PlayerType.IPL_BATSMAN_RUNS,IPL2019_RUNS_CSV_FILE_PATH);
            String sortedResult = cricketAnalyser.sortByFields(Sorted.SortByField.MAX_RUN_WITH_BEST_AVG);
            MostRunsCSV[] iplRuns = new Gson().fromJson(sortedResult, MostRunsCSV[].class);
            Assert.assertEquals("David Warner ", iplRuns[0].playerName);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
        }

    }

    //bowler Testcases
    @Test
    public void givenIPLWicketData_ShouldReturnExactCount() {

        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            int playercount = cricketAnalyser.loadIPLCSVData(CricketAnalyser.PlayerType.IPL_BOWLER_WICKETS,IPL2019_WICKET_CSV_FILE_PATH);
            System.out.println(playercount);
            Assert.assertEquals(99, playercount);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }
    //UC 7
    @Test
    public void givenIPLWicketData_IfSortedByBowlingAverage_ShouldReturn_BowlingAverage_Player() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadIPLCSVData(CricketAnalyser.PlayerType.IPL_BOWLER_WICKETS,IPL2019_WICKET_CSV_FILE_PATH);
            String sortedResult = cricketAnalyser.sortByFields(Sorted.SortByField.BEST_BOWLER_AVERAGE);
            MostWicketsCSV[] iplwicket = new Gson().fromJson(sortedResult, MostWicketsCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplwicket[0].playerName);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }
    //UC 8
    @Test
    public void givenIPLWicketData_IfSortedByBowlingStrikeRate_ShouldReturn_BowlingTopStrikeRate_Player() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadIPLCSVData(CricketAnalyser.PlayerType.IPL_BOWLER_WICKETS,IPL2019_WICKET_CSV_FILE_PATH);
            String sortedResult = cricketAnalyser.sortByFields(Sorted.SortByField.TOP_BOWLERS_STRIKE_RATE);
            MostWicketsCSV[] iplwicket = new Gson().fromJson(sortedResult, MostWicketsCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplwicket[0].playerName);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }


//UC 9
    @Test
    public void givenIPLWicketData_IfSortedByEconomy_ShouldReturn_BestEconomy_Player() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadIPLCSVData(CricketAnalyser.PlayerType.IPL_BOWLER_WICKETS,IPL2019_WICKET_CSV_FILE_PATH);
            String sortedResult = cricketAnalyser.sortByFields(Sorted.SortByField.BEST_ECONOMY_RATE);
            MostWicketsCSV[] iplwicket = new Gson().fromJson(sortedResult, MostWicketsCSV[].class);
            Assert.assertEquals("Shivam Dube", iplwicket[0].playerName);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }

   //UC10
    @Test
    public void givenIPLWicketData_IfSortedByBestStriking_Rate_with_5w_and_5w_ShouldReturn_TopStriking_Player() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadIPLCSVData(CricketAnalyser.PlayerType.IPL_BOWLER_WICKETS,IPL2019_WICKET_CSV_FILE_PATH);
            String sortedResult = cricketAnalyser.sortByFields(Sorted.SortByField.BEST_STRIKING_RATE_WITH_5W_AND_4W);
            MostWicketsCSV[] iplwicket = new Gson().fromJson(sortedResult, MostWicketsCSV[].class);
            Assert.assertEquals("Lasith Malinga", iplwicket[0].playerName);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }
//UC 11
    @Test
    public void givenIPLWicketData_IfSortedByBowlingAverageWithStrikeRate_shouldReturn_Player() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadIPLCSVData(CricketAnalyser.PlayerType.IPL_BOWLER_WICKETS,IPL2019_WICKET_CSV_FILE_PATH);
            String sortedResult = cricketAnalyser.sortByFields(Sorted.SortByField.BEST_BOWLING_AVERAGE_WITH_STRIKE_RATE);
            MostWicketsCSV[] iplwicket = new Gson().fromJson(sortedResult, MostWicketsCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplwicket[iplwicket.length - 1].playerName);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }

//UC 12
    @Test
    public void givenIPLWicketData_IfSortedByMaximumWicketWithBestBowlingAverage_shouldReturnRecord() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadIPLCSVData(CricketAnalyser.PlayerType.IPL_BOWLER_WICKETS,IPL2019_WICKET_CSV_FILE_PATH);
            String sortedResult = cricketAnalyser.sortByFields(Sorted.SortByField.MAXIMUM_WICKETS_WITH_BEST_AVERAGE_RATE);
            MostWicketsCSV[] iplwicket = new Gson().fromJson(sortedResult, MostWicketsCSV[].class);
            Assert.assertEquals("Imran Tahir", iplwicket[0].playerName);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }
//uc 13
    @Test
    public void givenCsvdata_IfSortedByBestBattingAndBollwingAverage_ShouldReturnRecord() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadIPLCSVData(CricketAnalyser.PlayerType.IPL_BATSMAN_RUNS,IPL2019_RUNS_CSV_FILE_PATH,IPL2019_WICKET_CSV_FILE_PATH);
            String sortedResult = cricketAnalyser.sortByFields(Sorted.SortByField.BEST_BATTING_BOWLING_AVERAGE);
            MostWicketsCSV[] iplwicket = new Gson().fromJson(sortedResult, MostWicketsCSV[].class);
            Assert.assertEquals("Andre Russell",iplwicket[0].playerName);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }
//UC 14
    @Test
    public void givenCsvdata_IfSortedofAllRounder_ShouldReturnRecord() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadIPLCSVData(CricketAnalyser.PlayerType.IPL_BATSMAN_RUNS,IPL2019_RUNS_CSV_FILE_PATH,IPL2019_WICKET_CSV_FILE_PATH);
            String sortedResult = cricketAnalyser.sortByFields(Sorted.SortByField.ALL_ROUNDER_PLAYER);
            MostWicketsCSV[] iplwicket = new Gson().fromJson(sortedResult, MostWicketsCSV[].class);
            Assert.assertEquals("Hardik Pandya",iplwicket[0].playerName);

        } catch (CricketAnalyserException e) {

            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }

}
