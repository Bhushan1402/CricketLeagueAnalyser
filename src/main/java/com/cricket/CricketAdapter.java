package com.cricket;

import java.util.Map;

public abstract class CricketAdapter {
    public abstract <E> Map<String, CricketDAO> loadCricketData(Class<E> className, String csvFilePath);
}
