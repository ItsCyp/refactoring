package org.iut.refactoring;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogService {
    private final List<String> logs = new ArrayList<>();

    public void add(String message) {
        logs.add(LocalDateTime.now() + " - " + message);
    }

    public List<String> getLogs() {
        return logs;
    }

    public void print() {
        System.out.println("=== LOGS ===");
        logs.forEach(System.out::println);
    }

}
