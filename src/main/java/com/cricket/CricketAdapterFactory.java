package com.cricket;

import java.util.Map;

public class CricketAdapterFactory {
    public Map<String, CricketDAO> grtCricketData(String csvFilePath) {
        Map<String, CricketDAO> cricketMap = new IPLAdapter().loadCricketData(csvFilePath);
        return cricketMap;
    }
}
