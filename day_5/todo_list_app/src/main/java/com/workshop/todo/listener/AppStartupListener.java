package com.workshop.todo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.workshop.todo.db.DatabaseUtil;

@WebListener
public class AppStartupListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        DatabaseUtil.initializeDatabase();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }
}