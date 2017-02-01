package com.olerom.vk.browser;

import com.olerom.vk.browser.servlets.CodeServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
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
        CodeServlet requestServlet = new CodeServlet();
        context.addServlet(new ServletHolder(requestServlet), "/");
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
}
