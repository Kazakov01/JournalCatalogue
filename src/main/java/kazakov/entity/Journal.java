package kazakov.entity;

import java.time.LocalDate;

public class Journal {
    private final String name;
    private final int issueNumber;
    private final LocalDate publishDate;

    public Journal(String name, int issueNumber, LocalDate publishDate) {
        this.name = name;
        this.issueNumber = issueNumber;
        this.publishDate = publishDate;
    }

    public String getName() {
        return name;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }
}
