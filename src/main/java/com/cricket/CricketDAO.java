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
    public int fives;
    public int fours;
    public int sixes;
    public int centuries;
    public int wickets;
    public double overs;
    public double economy;

    public CricketDAO(MostRunsCSV batsmanCsv) {
        playerName = batsmanCsv.playerName;
        matches = batsmanCsv.matches;
        inings =  batsmanCsv.inings;
        notOut =  batsmanCsv.notOut;
        runs =  batsmanCsv.runs;
        highestScore =  batsmanCsv.highestScore;
        if (batsmanCsv.average.equals("-"))
        average =0;
        else
        average=Double.valueOf(batsmanCsv.average);
        ballFaced =  batsmanCsv.ballFaced;
        strikeRate =  batsmanCsv.strikeRate;
        centuries =  batsmanCsv.centuries;
        halfCenturies =  batsmanCsv.halfCenturies;
        fours =  batsmanCsv.fours;
        sixes =  batsmanCsv.sixes;
    }
    public CricketDAO(MostWktsCSV mostBowlingCSV) {
        playerName = mostBowlingCSV.playerName;
        matches = mostBowlingCSV.matches;
        inings = mostBowlingCSV.inings;
        runs = mostBowlingCSV.runs;
        wickets = mostBowlingCSV.wickets;
        if (mostBowlingCSV.average.equals("-"))
            average =0;
        else
            average=Double.valueOf(mostBowlingCSV.average);
        economy = mostBowlingCSV.economy;
        if (mostBowlingCSV.strikeRate.equals("-"))
            strikeRate =0;
        else
            strikeRate=Double.valueOf(mostBowlingCSV.strikeRate);
        overs = mostBowlingCSV.overs;
        fives = mostBowlingCSV.fives;
        fours = mostBowlingCSV.fours;
    }

    public Object getCricketDTO(String... type) {
        if (type.length==0){
            return new MostRunsCSV(playerName,matches,inings,notOut,runs,highestScore,String.valueOf(average),ballFaced,strikeRate,centuries,halfCenturies,fours,sixes);
        }
        return new MostWktsCSV(playerName,matches,inings,runs,String.valueOf(average),String.valueOf(strikeRate),fours,fives);
    }


}
