package com.cricket;

public class CricketDAO {
    public String playerName,highestScore;
    public int matches;
    public int inings;
    public int notOut;
    public int runs;
    public int ballFaced;
    public double strikeRate,average;
    public int halfCenturies;
    public int fours;
    public int sixes;
    public int centuries;

    public CricketDAO(MostRunsCSV batsmanCsv) {
        this.playerName = batsmanCsv.playerName;
        this.matches = batsmanCsv.matches;
        this.inings =  batsmanCsv.inings;
        this.notOut =  batsmanCsv.notOut;
        this.runs =  batsmanCsv.runs;
        this.highestScore =  batsmanCsv.highestScore;
        if (batsmanCsv.average.equals("-"))
        this.average =0;
        else
        this.average=Double.valueOf(batsmanCsv.average);
        this.ballFaced =  batsmanCsv.ballFaced;
        this.strikeRate =  batsmanCsv.strikeRate;
        this.centuries =  batsmanCsv.centuries;
        this.halfCenturies =  batsmanCsv.halfCenturies;
        this.fours =  batsmanCsv.fours;
        this.sixes =  batsmanCsv.sixes;
    }

    public Object getCricketDTO() {
        return new MostRunsCSV(playerName,matches,inings,notOut,runs,highestScore,String.valueOf(average),ballFaced,strikeRate,centuries,halfCenturies,fours,sixes);
    }
}
