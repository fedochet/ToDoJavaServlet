package org.anstreth.controllers;

import org.anstreth.todo.ToDoList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by roman on 14.06.2016.
 */

@WebServlet("/")
public class MyServlet extends HttpServlet {
    public static final String CompletedItemsKey = "completed_items";
    public static final String UncompletedItemsKey = "uncompleted_items";

    private ToDoList list = new ToDoList();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(CompletedItemsKey, list.getCompletedItems());
        req.setAttribute(UncompletedItemsKey, list.getUncompletedItems());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletPath() != null) {
            switch (req.getServletPath()) {
                case "/complete":
                    String taskToComplete = req.getParameter("task_name");
                    if (taskToComplete!=null && !taskToComplete.isEmpty() && !list.isCompleted(taskToComplete)) {
                        list.complete(taskToComplete);
                    }
                    resp.sendRedirect("");
                    break;
                case "/uncomplete":
                    String taskToUncomplete = req.getParameter("task_name");
                    if (taskToUncomplete!=null && !taskToUncomplete.isEmpty() && list.isCompleted(taskToUncomplete)) {
                        list.uncomplete(taskToUncomplete);
                    }
                    resp.sendRedirect("");
                    break;
                case "/add":
                    String newTask = req.getParameter("new_task");
                    if (newTask != null && !newTask.isEmpty()) {
                        list.addItem(newTask);
                    }
                    resp.sendRedirect("");
                    break;
            }
        }
    }
}
