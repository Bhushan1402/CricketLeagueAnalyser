package com.cricket;

import java.util.HashMap;
import java.util.Map;

public class CricketAdapterFactory {

    Map<String, CricketDAO> cricketMap=null;

    public CricketAdapterFactory() {
        cricketMap=new HashMap<>();
    }

    public Map<String, CricketDAO> getCricketData(String... csvFilePath) {
        if (csvFilePath.length==1)
            cricketMap= new IPLAdapter().loadCricketData(MostRunsCSV.class,csvFilePath[0]);
        else
            cricketMap= new IPLAdapter().loadCricketData(MostWktsCSV.class,csvFilePath[1]);
        return cricketMap;
        }


}
