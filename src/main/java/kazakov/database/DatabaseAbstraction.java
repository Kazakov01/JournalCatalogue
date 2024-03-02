package kazakov.database;

import kazakov.entity.Journal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DatabaseAbstraction {

    private static final DatabaseAbstraction INSTANCE = new DatabaseAbstraction();

    final ConcurrentHashMap<String, Journal> journals = new ConcurrentHashMap<>();

    public static DatabaseAbstraction getSingleton() {
        return INSTANCE;
    }
    private DatabaseAbstraction() {
    }

}
