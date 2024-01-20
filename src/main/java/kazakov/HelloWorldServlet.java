package kazakov;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/HelloWorld")
public class HelloWorldServlet extends HttpServlet {

    private final Map<String, String> db = new HashMap<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletInputStream body = req.getInputStream();
        int a = body.read();
        System.out.println(a);
        while (a != -1) {
            a = body.read();
            System.out.println(a);
        }

//        System.out.println(body.readLine());

//        if (name != null && role != null) {
//            db.put(name, role);
//            resp.getOutputStream().println("User've been saved successfully");
//        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramName1 = "name";
        String name = req.getParameter(paramName1);
        String paramName2 = "role";
        String role = req.getParameter(paramName2);
        if (name != null) {
            resp.getOutputStream().println("Nice to see u " + name);
        }
        if (role != null) {
            resp.getOutputStream().println("ur role is an " + role);
        }
        resp.getOutputStream().println("Hello world");
    }
}

// Написать отдельный новый сервлет принимающий пут и гет запросы
// на пут сохранить в "базу данных" текст книги (название книги к вкачестве cgi параметра)
// текст в теле запроса
//
// на гет нужно вернуть текст книги (название книги в cgi параметре)
// если запрошена несуществующая книга то вернуть 404 ошибку