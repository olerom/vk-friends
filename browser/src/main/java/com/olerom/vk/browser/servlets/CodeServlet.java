package com.olerom.vk.browser.servlets;

import com.olerom.vk.browser.Solver;
import com.olerom.vk.core.User;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by olerom on 01.02.17.
 */
public class CodeServlet extends HttpServlet {
    private String code;

    public CodeServlet(String code) {
        this.code = code;
    }

    public CodeServlet() {
        this.code = "nullz";
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        System.out.println("GET was used");

        code = request.getParameter("code");
        if (code == null) {
            response.getWriter().println("Some problems were detected. Try again.");
        } else {
            response.getWriter().println(code);
//            Solver solver = new Solver();
//            solver.run(code);
            User us = null;
            try {
                us = new User(code);
            } catch (ClientException e) {
                e.printStackTrace();
            } catch (ApiException e) {
                e.printStackTrace();
            }
            response.getWriter().println(us.getUserActor().getId());
        }
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        System.err.println("POST was used");
        doGet(request, response);
    }
}
