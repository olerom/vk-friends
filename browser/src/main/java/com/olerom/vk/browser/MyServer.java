package com.olerom.vk.browser;

import com.olerom.vk.browser.servlets.CodeServlet;
import com.olerom.vk.browser.servlets.InfoServlet;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


/**
 * Created by olerom on 01.02.17.
 */
public class MyServer {
    public void start() {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        String code = "2443978cfbe5ef6c02";
        CodeServlet requestServlet = new CodeServlet(code);
        context.addServlet(new ServletHolder(requestServlet), "/");
//        InfoServlet requestServlet = new InfoServlet(code);
//        context.addServlet(new ServletHolder(requestServlet), "/info");
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("html_pages");

        HandlerList handlers = new HandlerList();
        Handler[] handler = {resource_handler, context};
        handlers.setHandlers(handler);

        Server server = new Server(8080);
        server.setHandler(context);

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initServer(String code) throws Exception {
        Integer port = 8080;
        String host = "localhost";

        Integer clientId = 5850646;
        String clientSecret = code;

        HandlerList handlers = new HandlerList();

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setWelcomeFiles(new String[]{"static/index.html"});
        resourceHandler.setResourceBase(Application.class.getResource("/static").getPath());
        System.out.println(Application.class.getResource("/static").getPath());

        VkApiClient vk = new VkApiClient(new HttpTransportClient());
        handlers.setHandlers(new Handler[]{resourceHandler, new RequestHandler(vk, clientId, clientSecret, host)});

        Server server = new Server(port);
        server.setHandler(handlers);

        server.start();
        server.join();
    }
}
