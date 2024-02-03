package kazakov.database;

import jdk.jshell.execution.Util;
import kazakov.entity.Journal;

import java.util.HashMap;
import java.util.Map;

public class JournalDao {
    // типичные названия методов в ДАО объектах
    // find/findBy/.. - поиск
    // createXxx - создание
    // updateXxx - изменение
    // delete/deleteBy/.. - удаление
    // могут быть кастомные методы которые нужны для внутреннего процееса (например работа с актуальностью объектов)
    //
    private DatabaseAbstraction db = new DatabaseAbstraction();

    public void create(Journal journal){
        db.journals.put(journal.getName() + journal.getIssueNumber(), journal);
    }

    public boolean contains(String nameAndIssue){
        return db.journals.containsKey(nameAndIssue);
    }

    public Journal find(String nameAndIssue) {
        if (contains(nameAndIssue)) {
            return null;
        }
        return db.journals.get(nameAndIssue);
    }

}
