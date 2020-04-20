package com.cricket;

import csvjar.CSVBuilderException;
import csvjar.CSVBuilderFactory;
import csvjar.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class BatsManRunsAdapter extends IPLAdapter {

    @Override
    public Map<String, CricketDAO> loadIPLCSVData(CricketAnalyser.PlayerType playerType, String ... csvFilePath) throws CricketAnalyserException {

        try {
            Map<String, CricketDAO> censusStateMap = super.loadIPLCSVData(MostRunsCSV.class, csvFilePath[0]);
            if (csvFilePath.length == 2)
                this.loadBowlerData(censusStateMap, csvFilePath[1]);
            return censusStateMap;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CricketAnalyserException(e.getMessage(), CricketAnalyserException.ExceptionType.IPL_CSVFILE_PROBLEM);
        }

    }

    public int loadBowlerData(Map<String, CricketDAO> ipldaoMap, String csvFilePath) throws CricketAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<MostWicketsCSV> iplWicketsIterator = csvBuilder.getStateCSVFileIterator(reader,MostWicketsCSV.class);
            Iterable<MostWicketsCSV> iplWicketsIterable = () -> iplWicketsIterator;
            StreamSupport.stream(iplWicketsIterable.spliterator(), false)
                    .filter(iplWickets -> ipldaoMap.get(iplWickets.playerName) != null)

                    .forEach(iplBowlersCSV -> {
                        ipldaoMap.get(iplBowlersCSV.playerName).Bowler_Average= iplBowlersCSV.Bowler_Average;
                        ipldaoMap.get(iplBowlersCSV.playerName).wickets = iplBowlersCSV.wickets;
                    });
            return ipldaoMap.size();
        } catch (IOException | CSVBuilderException e) {
            throw new CricketAnalyserException(e.getMessage(), CricketAnalyserException.ExceptionType.IPL_CSVFILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CricketAnalyserException(e.getMessage(), CricketAnalyserException.ExceptionType.IPL_CSVFILE_PROBLEM);
        }
    }
}
