package com.example.proyecto02calcuweb;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "Obtener", value = "/obtener")
public class Obtener_Datos extends HttpServlet {
    private String message;

    public void init() {
        message = "Datos Obtenidos!";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");

        // read form fields
        String operacion = request.getParameter("operacion");

        System.out.println("operación recibida: " + operacion);


        // do some processing here...

        // get response writer
        PrintWriter writer = response.getWriter();

        // build HTML code
        String htmlRespone = "<html>";
        htmlRespone += "<h2>Tu operación es: " + operacion + "<br/>";
        htmlRespone += "</html>";

        // return response
        writer.println(htmlRespone);
    }

    public void destroy() {
    }
}
