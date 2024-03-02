package kazakov.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kazakov.database.JournalDao;
import kazakov.entity.Journal;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/GetJournals")
public class GetJournalListServlet extends HttpServlet {

    JournalDao journalDao = new JournalDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Вывести все журналы в формате json

        List<Journal> journals = journalDao.getAll();
        JSONArray jsonArray = new JSONArray();
        for (Journal journal : journals) {
            JSONObject jsonJournal = createJsonFromJournal(journal);
            jsonArray.add(jsonJournal);
        }
        PrintWriter writer = resp.getWriter();
        writer.print(jsonArray);
        resp.setHeader("Content-Type", "application/json");
    }

    private JSONObject createJsonFromJournal(Journal journal) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", journal.getName());
        jsonObject.put("issueNumber", journal.getIssueNumber());
        jsonObject.put("publishDate", journal.getPublishDate());
        return jsonObject;
    }

    // поставить PostgreSQL или Mysql и поставить DBeaver
}
