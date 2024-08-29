package com.jeommechu.web.index;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import com.jeommechu.menu.lunch.LunchDAO;
import com.jeommechu.menu.lunch.LunchVO;

@WebServlet("/addLunch")
public class AddLunchServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private LunchDAO lunchDAO = new LunchDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String menu = request.getParameter("menu");
        HttpSession session = request.getSession();

        if (menu == null || menu.trim().isEmpty()) {
            session.setAttribute("errorMessage", "메뉴를 입력하세요.");
            response.sendRedirect(request.getContextPath() + "/getLunches");
            return;
        }

        try {
            if (lunchDAO.isMenuExists(menu)) {
                session.setAttribute("errorMessage", "중복 메뉴입니다.");
                response.sendRedirect(request.getContextPath() + "/getLunches");
                return;
            }
            lunchDAO.addLunch(new LunchVO(0, menu));
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
        response.sendRedirect(request.getContextPath() + "/getLunches");
    }
}
