package com.cricket;

import com.opencsv.bean.CsvBindByName;

public class MostWktsCSV {

    @CsvBindByName(column = "PLAYER", required = true)
    public String playerName;

    @CsvBindByName(column = "Mat", required = true)
    public int matches;

    @CsvBindByName(column = "Inns", required = true)
    public int inings;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "Wkts", required = true)
    public int wickets;

    @CsvBindByName(column = "Avg", required = true)
    public String average;

    @CsvBindByName(column = "Econ", required = true)
    public double economy;

    @CsvBindByName(column = "SR", required = true)
    public String strikeRate;

    @CsvBindByName(column = "Ov", required = true)
    public double overs;

    @CsvBindByName(column = "5w", required = true)
    public int fives;

    @CsvBindByName(column = "4w", required = true)
    public int fours;

    public MostWktsCSV() {
    }

    public MostWktsCSV(String playerName, int matches, int inings, int runs, String strikeRate, String average, int fours, int fives) {
        this.playerName = playerName;
        this.matches = matches;
        this.inings = inings;
        this.runs = runs;
        this.wickets = wickets;
        this.average = average;
        this.economy = economy;
        this.strikeRate = strikeRate;
        this.overs = overs;
        this.fives = fives;
        this.fours = fours;
    }
}
