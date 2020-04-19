package com.cricket;

import csvjar.CSVBuilderException;
import csvjar.CSVBuilderFactory;
import csvjar.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IPLAdapter extends CricketAdapter {

    @Override
    public <E> Map<String, CricketDAO> loadCricketData(Class<E> className, String csvFilePath) {
            Map<String, CricketDAO> cricketMap = new HashMap<>();
            try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
                ICSVBuilder builder = CSVBuilderFactory.createCSVBuilder();
                Iterator<E> csvFileIterator = builder.getStateCSVFileIterator(reader, className);
                Iterable<E> cscIterable = () -> csvFileIterator;
                if (className.getName().equals("com.cricket.MostRunsCSV")) {
                    StreamSupport.stream(cscIterable.spliterator(), false)
                            .map(MostRunsCSV.class::cast)
                            .forEach(batsManCSV -> cricketMap.put(batsManCSV.playerName, new CricketDAO(batsManCSV)));
                } else if (className.getName().equals("com.cricket.MostWktsCSV")) {
                    StreamSupport.stream(cscIterable.spliterator(), false)
                            .map(MostWktsCSV.class::cast)
                            .forEach(batsManCSV -> cricketMap.put(batsManCSV.playerName, new CricketDAO(batsManCSV)));
                }
            } catch (IOException | CSVBuilderException e) {
                e.printStackTrace();
            }
            return cricketMap;
        }
    }

