package com.cricket;

import java.util.Map;

public abstract class CricketAdapter {
    public abstract Map<String, CricketDAO> loadCricketData(String csvFilePath);
}
