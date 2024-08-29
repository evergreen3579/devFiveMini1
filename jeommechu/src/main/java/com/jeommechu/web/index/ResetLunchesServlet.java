package com.jeommechu.web.index;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.jeommechu.menu.lunch.LunchDAO;

@WebServlet("/resetLunches")
public class ResetLunchesServlet extends HttpServlet {

    private LunchDAO lunchDAO = new LunchDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            lunchDAO.deleteAllLunches();
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
        response.sendRedirect(request.getContextPath() + "/getLunches");
    }
}
