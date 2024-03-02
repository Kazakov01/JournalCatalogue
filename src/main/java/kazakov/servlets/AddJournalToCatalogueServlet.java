package kazakov.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import kazakov.database.JournalDao;
import kazakov.entity.Journal;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/AddJournal")
public class AddJournalToCatalogueServlet extends HttpServlet {

    JournalDao journalDao = new JournalDao();

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Если публикуемая книга с названием
        // Уже существует то кинуть ошибку на стороне клиента
        // Иначе внести в базу данных передаваемый файл
        // + аттрибуты журнала
        BufferedReader rd = req.getReader();
        Journal journal;
        try {
            journal = getFromJson(rd);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        if (journalDao.contains(journal.getName() + journal.getIssueNumber())) {
            resp.sendError(400,"This journal ia already saved");
        } else {
            journalDao.create(journal);
        }
    }

    /**пришел json надо его спарсить в журнал с помощью библиотеки JSon Parser
     * отдельно дополнительно написать сервлет по запросу выводящий список всех имеющихся журналов
     */
    private Journal getFromJson(Reader rd) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        Object journalObj = jsonParser.parse(rd);
        JSONObject journalJson;
        if (journalObj instanceof JSONObject) {
            journalJson = (JSONObject) journalObj;
        } else {
            throw new RuntimeException("illegal json format");
        }
        LocalDate publishDate = LocalDate.parse((String)journalJson.get("publishDate"));

        Journal journal = new Journal((String) journalJson.get("name"),
                                      ((Number) journalJson.get("issueNumber")).intValue(),
                                      publishDate);

        return journal;
    }

}
