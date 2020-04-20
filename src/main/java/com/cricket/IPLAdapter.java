package com.cricket;

import csvjar.CSVBuilderException;
import csvjar.CSVBuilderFactory;
import csvjar.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;

import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class IPLAdapter {

    Map<String, CricketDAO> iplCSVMap ;

    public IPLAdapter() {
        this.iplCSVMap = new HashMap<>();
    }

    public abstract Map<String, CricketDAO> loadIPLCSVData (CricketAnalyser.PlayerType playerType , String ... csvFilePath) throws CricketAnalyserException;

    protected <E> Map<String, CricketDAO> loadIPLCSVData(Class<E> iplClass , String filePath) throws CricketAnalyserException {

        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> censusCSVIterator = csvBuilder.getStateCSVFileIterator(reader,iplClass);
            Iterable<E> Iterable = () -> censusCSVIterator;
            if (iplClass.getName().equals("com.cricketleague.MostRunsCSV")) {
                StreamSupport.stream(Iterable.spliterator(), false)
                        .map(MostRunsCSV.class::cast)
                        .forEach(iplCSV -> iplCSVMap .put(iplCSV.playerName, new CricketDAO(iplCSV)));
            }
            else if (iplClass.getName().equals("com.cricketleague.MostWicketsCSV")) {
                StreamSupport.stream(Iterable.spliterator(), false)
                        .map(MostWicketsCSV.class::cast)
                        .forEach(iplBowlersCSV -> iplCSVMap .put(iplBowlersCSV.playerName, new CricketDAO(iplBowlersCSV)));
            }
            return iplCSVMap;
        } catch (NoSuchFileException e) {
            throw new CricketAnalyserException(e.getMessage(), CricketAnalyserException.ExceptionType.FILE_NOT_FOUND);
        } catch (CSVBuilderException | IOException e) {
            throw new CricketAnalyserException(e.getMessage(), CricketAnalyserException.ExceptionType.FILE_ERROR);
        }
    }

}