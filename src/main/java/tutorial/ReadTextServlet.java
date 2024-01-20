package tutorial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

@WebServlet(urlPatterns = "/ReadText")
public class ReadTextServlet extends HttpServlet {

    Map<String, String> books = new HashMap<>();

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader rd = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line).append("\r\n");
        }
        if (books.containsKey(req.getParameter("book"))) {
            resp.sendError(400,"This book ia already saved");
            return;
        }
        books.put(req.getParameter("book"), sb.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String bookName = req.getParameter("book");
        if (books.containsKey(bookName)) {
            resp.getWriter().print(books.get(bookName));
        } else {
            resp.sendError(404, "No book with name " + bookName + " found");
        }
        resp.setHeader("Content-Type", "plain/text; charset=utf-8");
    }
}
