package com.cricket;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.HashMap;
import java.util.Map;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class AnalyserMockitoTest {
    public static final String ORIGINAL_RUNS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WKTS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Mock
    MockitoAdapter adapter;

    CricketAnalyser cricketAnalyser=null;
    Map<String,CricketDAO> battingMap=null;
    Map<String,CricketDAO> bowlingMap=null;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp()
    {
        adapter=mock(MockitoAdapter.class);
        cricketAnalyser = new CricketAnalyser();
        battingMap = new HashMap<>();
        battingMap.put("David Warner", new CricketDAO("David Warner", 692,69.2,143.86,57,21));
        battingMap.put("KL Rahul", new CricketDAO("KL Rahul", 593,53.9,135.38,49,25));
        battingMap.put("Quinton de kock", new CricketDAO("Quinton de kock", 529,35.26,132.91,45,25));
        bowlingMap = new HashMap<>();
        bowlingMap.put("David Warner", new CricketDAO("David Warner", 692,59.2,143.86,57,21));
        bowlingMap.put("KL Rahul", new CricketDAO("KL Rahul", 593,53.9,135.38,49,25));
        bowlingMap.put("Quinton de kock", new CricketDAO("Quinton de kock", 529,35.26,132.91,45,25));
    }

    @Test
    public void givenBatsManData_WhenCorrect_ReturnsCountOfThree(){
            cricketAnalyser.setAdapter(CricketAnalyser.PlayerType.IPL_BATSMAN_RUNS,adapter);
            when(this.adapter.loadData(ORIGINAL_RUNS_FILE_PATH)).thenReturn(battingMap);
           Map<String, CricketDAO> daoMap = cricketAnalyser.loadData(CricketAnalyser.PlayerType.IPL_BATSMAN_RUNS,ORIGINAL_RUNS_FILE_PATH);
    }

    @Test
    public void givenBowlersData_WhenCorrect_ReturnsCountOfThree(){
        cricketAnalyser.setAdapter(CricketAnalyser.PlayerType.IPL_BOWLER_WICKETS,adapter);
        when(this.adapter.loadData(WKTS_FILE_PATH)).thenReturn(bowlingMap);
        Map<String, CricketDAO> daoMap = cricketAnalyser.loadData(CricketAnalyser.PlayerType.IPL_BATSMAN_RUNS,WKTS_FILE_PATH);
    }
}
