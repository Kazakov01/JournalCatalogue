package kazakov.servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/AddJournal")
public class AddJournalToCatalogueServlet extends HttpServlet {

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Если публикуемая книга с названием
        // Уже существует то кинуть ошибку на стороне клиента
        // Иначе внести в базу данных передаваемый файл
        // + аттрибуты журнала


        // HW дореализовать метод PUT
        // На основе распарсенного создать объект Journal
        // И сохранить в ДАО
        // С Использование ДатабэйсАбстракшн сохранить объект используя Map
        // Все хранение в ДБ Абстракшн


    }
}
