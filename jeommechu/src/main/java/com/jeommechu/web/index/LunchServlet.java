package com.jeommechu.web.index;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.jeommechu.menu.lunch.LunchDAO;
import com.jeommechu.menu.lunch.LunchVO;

@WebServlet("/getLunches")
public class LunchServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private LunchDAO lunchDAO = new LunchDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            List<LunchVO> lunches = lunchDAO.getAllLunches();
            HttpSession session = request.getSession();
            String errorMessage = (String) session.getAttribute("errorMessage");
            session.removeAttribute("errorMessage");

            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>LUNCH</title>");
            out.println("<link rel='stylesheet' href='static/styles.css'>");
            out.println("</head>");
            out.println("<body id='lunch-page'>");
            out.println("<center>");
            out.println("<br><br>");
            out.println("<div class=\"eleven\">");
            out.println("<h1>- 점심 메뉴 추가 -</h1>");
            out.println("<div class='container'>");
            out.println("<form action='addLunch' method='post'>");
            out.println("<tr>");
            out.println("<td>");
            out.println("<input type='text' name='menu' placeholder=\"음식 메뉴를 작성해주세요.\"/>");
            out.println("<input type='submit' value='메뉴 추가'/>");
            out.println("</td>");
            out.println("</tr>");
            out.println("</form>");
            out.println("</div>");
            out.println("<br>");
            out.println("<h1>- 점심 메뉴 목록 -</h1>");
            out.println("<br>");
            out.println("<div class='menupan'>");
            out.println("<table border='1'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>점심 메뉴</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            // 메뉴 항목만 가져오기
            List<String> menuItems = lunchDAO.getAllMenuItems();
            // 점심 메뉴 목록을 테이블에 추가
            for (String menu : menuItems) {
                out.println("<tr>");
                out.println("<td>" + menu + "</td>");
                out.println("</tr>");
            }

            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");
            out.println("<br>");
            out.println("<form action='resetLunches' method='post'>");
            out.println("<input type='submit' value='메뉴 리셋'/>");
            out.println("</form>");
            out.println("</div>");
            out.println("<input type='button' value='메뉴 뽑기' onclick=\"location.href='select.html'\"/>");
            out.println("<br><br>");
            out.println("</center>");
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}
