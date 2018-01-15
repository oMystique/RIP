package rip.lab1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.Integer;

import rip.lab1.model.Clearomizers;
import java.util.*;

import java.io.PrintWriter;

@WebServlet("/addClearomizer")
public class Clearomizer extends HttpServlet {
    private List<Clearomizer> m_clearomizers = new ArrayList();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        renderHeader(response);
        String action = (String) request.getParameter("action");

        if (action.equals("show")) {
            showClearomizers(response);
        }
        else {
            addClearomizerForm(response);
        }

        renderFooter(response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        Clearomizer clearomizer = new Clearomizer();

        clearomizer.name = request.getParameter("name");
        clearomizer.manufacturer = request.getParameter("manufacturer");
        clearomizer.type = request.getParameter("type");
        clearomizer.coilsCount = Integer.parseInt(request.getParameter("coilsCount"));

        m_clearomizer.add(clearomizer);

        doGet(request, response);
    }

    private void showClearomizers(HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("<table border=\"1\">\n" +
                "   <caption>Clearomizer</caption>\n" +
                "   <thead>" +
                "   <tr>\n" +
                "    <th>Name</th>\n" +
                "    <th>Manufacturer</th>\n" +
                "    <th>Type</th>\n" +
                "    <th>Coils Count</th>\n" +
                "   </tr>" +
                "   </thead>" +
                "   <tbody>" +
                "   <tr>");
        for ( Clearomizer car: m_clearomizers) {
            response.getWriter().println(
                    "<td>" + car.name + "</td>\n" +
                    "<td>" + car.manufacturer + "</td>\n" +
                    "<td>" + car.type + "</td>\n" +
                    "<td>" + car.coilsCount + "</td>\n" +
            );
        }
        response.getWriter().println( "</tr></tbody></table");
    }

    private void addClearomizerForm(HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();

        String htmlRespone =
                "    <form action=\"addClearomizer?action=\" method=\"post\">\n" +
                "        Name: <input type=\"text\" name=\"name\" pattern=\"[A-Za-zА-Яа-яЁё]{3, 40}\" /></br>\n" +
                "        Manufacturer: <input type=\"text\" name=\"manufacturer\" pattern=\"[A-Za-zА-Яа-яЁё]{3, 40}\" /></br>\n" +
                "        Type: <input type=\"text\" name=\"type\" pattern=\"[A-Za-zА-Яа-яЁё]{3, 40}\" /></br>\n" +
                "        Coils Count: <input type=\"text\" name=\"coilsCount\" pattern=\"^[0-9]+$\"/></br>\n" +
                "        <input name=\"action\" type=\"hidden\" value=\"addBook\"/>" +
                "        <input type=\"submit\" value=\"send\" />" +
                "    </form>\n";
        writer.println(htmlRespone);
    }

    public void renderHeader(HttpServletResponse response) throws IOException {
        response.getWriter().println("<!DOCTYPE html>\n" +
        "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n");
    }

    private void renderFooter(HttpServletResponse response) throws IOException {
        response.getWriter().println("</body></html>");
    }
}