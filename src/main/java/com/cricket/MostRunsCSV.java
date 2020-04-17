package com.cricket;

import com.opencsv.bean.CsvBindByName;

public class MostRunsCSV {

    @CsvBindByName(column = "PLAYER", required = true)
    public String playerName;

    @CsvBindByName(column = "Mat", required = true)
    public int matches;

    @CsvBindByName(column = "Inns", required = true)
    public int inings;

    @CsvBindByName(column = "NO", required = true)
    public int notOut;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "HS", required = true)
    public String highestScore;

    @CsvBindByName(column = "Avg", required = true)
    public String average;

    @CsvBindByName(column = "BF", required = true)
    public int ballFaced;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;

    @CsvBindByName(column = "100", required = true)
    public int centuries;

    @CsvBindByName(column = "50", required = true)
    public int halfCenturies;

    @CsvBindByName(column = "4s", required = true)
    public int fours;

    @CsvBindByName(column = "6s", required = true)
    public int sixes;

    public MostRunsCSV() {
    }

    public MostRunsCSV(String playerName, int matches,
                       int inings, int notOut,
                       int runs, String highestScore,
                       String average, int ballFaced,
                       double strikeRate, int centuries, int halfCenturies, int fours, int sixes) {
        this.playerName = playerName;
        this.matches = matches;
        this.inings = inings;
        this.notOut = notOut;
        this.runs = runs;
        this.highestScore = highestScore;
        this.average = average;
        this.ballFaced = ballFaced;
        this.strikeRate = strikeRate;
        this.centuries = centuries;
        this.halfCenturies = halfCenturies;
        this.fours = fours;
        this.sixes = sixes;
    }
}
