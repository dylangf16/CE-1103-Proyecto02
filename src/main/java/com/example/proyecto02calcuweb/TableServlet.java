package com.example.proyecto02calcuweb;

import java.io.*;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import Save.*;

@WebServlet(name = "Tabla", value = "/tabla")
public class TableServlet extends HttpServlet {
    private String message;

    /**
     * Genera la respuesta del servlet
     * @param request solicitud del cliente
     * @param response respuesta del servlet
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // get response writer
        PrintWriter writer = response.getWriter();

        // build HTML code
        String htmlRespone = "<!DOCTYPE html>";
        htmlRespone += buildTable();


        // return response
        writer.println(htmlRespone);
    }

    /**
     * Construye el html de la tabla de resultados
     * @return String del html del codigo de la tabla
     * @throws IOException
     */
    public String buildTable() throws IOException {
        SaveProblem saveProblem = new SaveProblem();
        List<List<String>> tableCSV = saveProblem.readFile();
        String table = "<table>";
        table += "<tr>";
        table += "<th>DATE:</th>";
        table += "<th>PROBLEM:</th>";
        table += "<th>RESULT:</th>";
        table += "</tr>";
        for (int j = 1; j < tableCSV.size(); j++) {
            table += "<tr>";
            for (int i = 0; i < tableCSV.get(j).size(); i++) {
                table += "<td>" + tableCSV.get(j).get(i) + "</td>";
            }
            table += "</tr>";
        }
        table += "</table>";
        table += "</html>";
        return table;
    }

    public void destroy() {
    }
}