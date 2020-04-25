package com.cricket;

public class CricketDAO {
    private int centuries;
    public String playerName;
    public int match;
    public double average;
    public double strikeRate;
    public int noOfFours;
    public int noOfSixes;
    public int runs;
    public double Bowler_Average;
    public double Bowler_strikeRate;
    public double Bowler_Economy;
    public int wickets;
    public double overs;
    public int fourWicket;
    public int fiveWicket;

    public CricketDAO(String abc, int i, double v) {
    }

    public CricketDAO(MostRunsCSV mostRunsCSV) {
        playerName = mostRunsCSV.playerName;
        match = mostRunsCSV.match;
        average = mostRunsCSV.average;
        strikeRate =mostRunsCSV.strikeRate;
        noOfFours=mostRunsCSV.noOfFours;
        noOfSixes=mostRunsCSV.noOfSixes;
        runs=mostRunsCSV.runs;
    }
    public CricketDAO(MostWicketsCSV mostWicketsCSV) {

        playerName = mostWicketsCSV.playerName;
        Bowler_Average= mostWicketsCSV.Bowler_Average;
        Bowler_strikeRate = mostWicketsCSV.Bowler_strikeRate;
        Bowler_Economy=mostWicketsCSV.Bowler_Economy;
        wickets=mostWicketsCSV.wickets;
        overs=mostWicketsCSV.overs;
        fourWicket=mostWicketsCSV.fourWicket;
        fiveWicket=mostWicketsCSV.fiveWicket;

    }

    public CricketDAO(String playerName, int runs, double average, double strikeRate,int noOfFours, int noOfSixes) {
        this.playerName = playerName;
        this.runs=runs;
        this.average=average;
        this.strikeRate = strikeRate;
        this.noOfFours = noOfFours;
        this.noOfSixes = noOfSixes;
        this.centuries=centuries;
    }
}