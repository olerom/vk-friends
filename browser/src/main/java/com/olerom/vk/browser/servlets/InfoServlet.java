package com.olerom.vk.browser.servlets;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.objects.users.UserXtrCounters;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by olerom on 02.02.17.
 */
public class InfoServlet extends HttpServlet {
    private final String clientSecret;
    private final int clientId;
    private final String code;
    private final VkApiClient vk;
    private final UserActor actor;

    public InfoServlet(VkApiClient vk, int clientId, String clientSecret, String code, UserActor actor) {
        this.vk = vk;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.code = code;
        this.actor = actor;
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        System.out.println("GET was used");

        try {
            List<UserXtrCounters> getUsersResponse = vk.users().get(actor).userIds(request.getParameter("user")).execute();
            UserXtrCounters user = getUsersResponse.get(0);

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(getInfoPage(user));

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            response.getWriter().println("error");
            response.setContentType("text/html;charset=utf-8");
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        System.err.println("POST was used");
        doGet(request, response);
    }

    private String getInfoPage(UserXtrCounters user) {
        return "Hello <a href='https://vk.com/id" + user.getId() + "'>" + user.getFirstName() + "</a>";
    }
}
