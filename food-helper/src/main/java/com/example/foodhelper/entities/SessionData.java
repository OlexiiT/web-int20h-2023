package com.example.foodhelper.entities;

import java.util.Map;
import java.util.TreeMap;

public class SessionData {
    private static Map<String, String> sessionsMap = new TreeMap<>();

    public static String getUserId(String sessionId) {
        return sessionsMap.get(sessionId);
    }

    public static void create(String sessionId, String userId) {
        sessionsMap.put(sessionId, userId);
    }

    public static String remove(String sessionId) {
        return sessionsMap.remove(sessionId);
    }
}
