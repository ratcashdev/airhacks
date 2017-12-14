
package com.airhacks.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author airhacks.com
 */
@WebServlet("/ServerPushServlet")
public class ServerPushServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println(".");
        request.newPushBuilder().path("app.js").push();
        request.newPushBuilder().path("style.css").push();
        request.getRequestDispatcher("actual.html").forward(request, response);
    }
}
