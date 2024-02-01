package kazakov.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import kazakov.database.JournalDao;
import kazakov.entity.Journal;

import java.io.BufferedReader;
import java.io.IOException;
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
        StringBuilder sb = new StringBuilder();

        if (journalDao.contains(req.getParameter("journal"))) {
            resp.sendError(400,"This journal ia already saved");
        } else {
            String name = rd.readLine();
            int issueNum = Integer.valueOf(rd.readLine());
            LocalDate publishDate = LocalDate.parse(rd.readLine());
            journalDao.create(new Journal(name,issueNum,publishDate));
        }

        // HW дореализовать метод PUT
        // На основе распарсенного создать объект Journal
        // И сохранить в ДАО
        // С Использование ДатабэйсАбстракшн сохранить объект используя Map
        // Все хранение в ДБ Абстракшн

    }
}
