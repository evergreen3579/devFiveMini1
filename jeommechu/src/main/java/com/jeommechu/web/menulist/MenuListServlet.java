package com.jeommechu.web.menulist;

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

import com.jeommechu.menu.menulist.MenuListDAO;
import com.jeommechu.menu.menulist.MenuListVO;

@WebServlet("/menuList")
public class MenuListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private MenuListDAO menuListDAO = new MenuListDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        String errorMessage = (String) session.getAttribute("errorMessage");
        session.removeAttribute("errorMessage");

        String popupMessage = (String) session.getAttribute("popupMessage");
        session.removeAttribute("popupMessage");

        String role = (String) session.getAttribute("role");

        List<MenuListVO> menuList = null;

        try (PrintWriter out = response.getWriter()) {
            try {
                String search = request.getParameter("search");
                menuList = menuListDAO.getMenuList(search); // 검색어를 전달하여 메뉴 리스트를 가져옴
            } catch (SQLException e) {
                throw new ServletException("데이터베이스 오류 발생", e); // 서블릿 예외로 래핑
            }

            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");

            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>메뉴 목록</title>");
            out.println("<link rel='stylesheet' href='static/menulist.css'>");
            out.println("<script>");
            out.println("window.onload = function() {");
            if (popupMessage != null) {
                out.println("alert('" + popupMessage + "');");
            }
            out.println("};");
            out.println("</script>");
            out.println("</head>");

            out.println("<body id='menu-list-page'>");

            out.println("<header>");
            out.println("<a href='getLunches'>JEOMMECHU</a>");

            out.println("<div class='rights'>");
            String memberName = (String) session.getAttribute("memberName");
            String welcomeMessage = (String) session.getAttribute("welcomeMessage");
            if (memberName != null && welcomeMessage != null) {
                out.println(memberName + welcomeMessage);
                out.println("<a href='logout'>로그아웃</a>");
            } else {
                response.sendRedirect("login.html");
                return; // 로그인 페이지로 리다이렉트 후, 현재 서블릿의 실행을 중단합니다.
            }
            out.println("</div>");
            out.println("<h1>메뉴 목록</h1>");
            out.println("</header>");

            out.println("<hr>");

            out.println("<main>");
            out.println("<h2>▶ 메뉴 검색 ◀</h2>");
            out.println("<form class='search-form' action='menuList' method='get'>");
            out.println("검색: <input type='text' name='search' value='" + request.getParameter("search") + "'/>");
            out.println("<button type='submit'>검색</button>");
            out.println("</form>");

            out.println("<br><br>");

            out.println("<h2>▶ 메뉴 목록 ◀</h2>");
            out.println("<div class='container'>");
            out.println("<br>");
            out.println("<div class='menulist'>");
            out.println("<table>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>번호</th>");
            out.println("<th>이름</th>");
            out.println("<th>고르기</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            if (menuList != null && !menuList.isEmpty()) {
                for (MenuListVO menu : menuList) {
                    out.println("<tr>");
                    out.println("<td>" + menu.getNum() + "</td>");
                    out.println("<td>" + menu.getName() + "</td>");
                    out.println("<td><a href='menuSelect?num=" + menu.getNum() + "'>선택</a></td>");
                    out.println("</tr>");
                }
            } else {
                out.println("<tr>");
                out.println("<td colspan='3'>선택된 메뉴가 없습니다.</td>");
                out.println("</tr>");
            }

            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");

            out.println("<br><br>");
            out.println("</main>");
            out.println("<hr>");

            out.println("<footer>");
            out.println("<input class='main-button' type='button' value='메인페이지' onclick=\"location.href='getLunches'\"/>");

            out.println("<div class='left'>");
            if ("ADMIN".equals(role)) {
                out.println("<br>");
                out.println("<a href='memberList'>회원 관리</a>");
            }
            out.println("</div>");

            out.println("<div class='footer-text'>Protein Cigarette</div>");
            out.println("</footer>");

            out.println("</body>");
            out.println("</html>");
        }
    }
}
