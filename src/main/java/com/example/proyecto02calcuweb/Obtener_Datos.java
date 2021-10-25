package com.example.proyecto02calcuweb;

import Expression_Tree.ExpressionTree;
import Expression_Tree.InfixToPosfix;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import Save.SaveProblem;

@WebServlet(name = "Obtener", value = "/obtener")
public class Obtener_Datos extends HttpServlet {
    private String message;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        // read form fields
        String operacion = request.getParameter("operacion");


        String posfix = InfixToPosfix.main(operacion);
        double resul = ExpressionTree.main(posfix);
        String resultado = resul + "";

        SaveProblem.main(operacion,resultado);

        System.out.println("operación recibida: " + operacion);
        System.out.println("posfix: " + posfix);
        System.out.println("resultado de la operación: " + resul);


        // do some processing here...

        // get response writer
        PrintWriter writer = response.getWriter();

        // build HTML code
        String htmlRespone = "<!DOCTYPE html>";
        htmlRespone += "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\" />\n" +
                "    <meta name=\"description\" content=\"\" />\n" +
                "    <meta name=\"author\" content=\"\" />\n" +
                "    <title>Grayscale - Start Bootstrap Theme</title>\n" +
                "    <link rel=\"icon\" type=\"image/x-icon\" href=\"assets/favicon.ico\" />\n" +
                "    <!-- Font Awesome icons (free version)-->\n" +
                "    <script src=\"https://use.fontawesome.com/releases/v5.15.3/js/all.js\" crossorigin=\"anonymous\"></script>\n" +
                "    <!-- Google fonts-->\n" +
                "    <link href=\"https://fonts.googleapis.com/css?family=Varela+Round\" rel=\"stylesheet\" />\n" +
                "    <link href=\"https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i\" rel=\"stylesheet\" />\n" +
                "    <!-- Core theme CSS (includes Bootstrap)-->\n" +
                "    <link href=\"css/styles.css\" rel=\"stylesheet\" />\n" +
                "</head>\n" +
                "<body id=\"page-top\">\n" +
                "<!-- Navigation-->\n" +
                "<nav class=\"navbar navbar-expand-lg navbar-light fixed-top\" id=\"mainNav\">\n" +
                "    <div class=\"container px-4 px-lg-5\">\n" +
                "        <a class=\"navbar-brand\" href=\"#page-top\">Proyecto 02 - CE-1103  // Autores: Dylan Garbanzo Fallas y Alejandra Rodriguez Castro</a>\n" +
                "        <button class=\"navbar-toggler navbar-toggler-right\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarResponsive\" aria-controls=\"navbarResponsive\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
                "            Menu\n" +
                "            <i class=\"fas fa-bars\"></i>\n" +
                "        </button>\n" +
                "        <div class=\"collapse navbar-collapse\" id=\"navbarResponsive\">\n" +
                "            <ul class=\"navbar-nav ms-auto\">\n" +
                "            </ul>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</nav>\n" +
                "<!-- Masthead-->\n" +
                "<header class=\"masthead\">\n" +
                "    <div class=\"container px-4 px-lg-5 d-flex h-100 align-items-center justify-content-center\">\n" +
                "        <div class=\"d-flex justify-content-center\">\n" +
                "            <div class=\"text-center\">\n" +
                "                <h1 class=\"mx-auto my-0 text-uppercase\">Datos Obtenidos</h1>\n" +
                "                <h2 class=\"text-white-50 mx-auto mt-2 mb-5\">Tu resultado es: " + resul + ".</h2>\n" +
                "                <form action=\"http://localhost:8080/Proyecto02_CalcuWeb_war_exploded/\">"+
                "                   <input class=\"btn btn-primary\" type=\"submit\" value=\"Presiona para volver a realizar un cálculo!\"/>"+
                "                </form>"+
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</header>\n" +
                "<!-- Footer-->\n" +
                "<footer class=\"footer bg-black small text-center text-white-50\"><div class=\"container px-4 px-lg-5\">Copyright &copy; Your Website 2021</div></footer>\n" +
                "<!-- Bootstrap core JS-->\n" +
                "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js\"></script>\n" +
                "<!-- Core theme JS-->\n" +
                "<script src=\"js/scripts.js\"></script>\n" +
                "<script src=\"https://cdn.startbootstrap.com/sb-forms-latest.js\"></script>\n" +
                "</body>\n" +
                "</html>\n";


        // return response
        writer.println(htmlRespone);
    }

    public void destroy() {
    }
}
