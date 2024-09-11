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

@WebServlet("/menulist")
public class MenuListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private MenuListDAO menuListDAO = new MenuListDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        String errorMessage = (String) session.getAttribute("errorMessage");
        session.removeAttribute("errorMessage");
        
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
            out.println("<title>메뉴 목록</title>");
            out.println("<link rel='stylesheet' href='static/main.css'>");
            out.println("</head>");  
            
            out.println("<body id='menu-list-page'>");
            
            out.println("<header>");
            out.println("<a href='getLunches'>JEOMMECHU</a>");
            
            out.println("<div class=\"rights\">");
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
            out.println("<br><br>");
            
            // 검색 폼 추가
            out.println("<h2>▶ 메뉴 검색 ◀</h2>");
            out.println("<form action='menulist' method='get'>");
            out.println("검색: <input type='text' name='search' value='" + request.getParameter("search") + "'/>");
            out.println("<input type='submit' value='검색'/>");
            out.println("</form>");
            
            out.println("<br><br>");
            
            out.println("<h2>▶ 메뉴 목록 ◀</h2>");
            out.println("<br>");
            out.println("<div class='menulist'>");
            out.println("<table border='1'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>번호</th>");
            out.println("<th>이름</th>");
            out.println("<th>전체 칼로리</th>");
            out.println("<th>오메가 칼로리</th>");
            out.println("<th>중량</th>");
            out.println("<th>단백질</th>");
            out.println("<th>지방</th>");
            out.println("<th>탄수화물</th>");
            out.println("<th>당</th>");
            out.println("<th>나트륨</th>");
            out.println("<th>포화지방</th>");
            out.println("<th>고르기</th>"); // "추가" 버튼을 위한 액션 열
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            if (menuList != null && !menuList.isEmpty()) {
                for (MenuListVO menu : menuList) {
                    out.println("<tr>");
                    out.println("<td>" + menu.getNum() + "</td>");
                    out.println("<td>" + menu.getName() + "</td>");
                    out.println("<td>" + menu.getAllKcal() + "</td>");
                    out.println("<td>" + menu.getOhKcal() + "</td>");
                    out.println("<td>" + menu.getW() + "</td>");
                    out.println("<td>" + menu.getP() + "</td>");
                    out.println("<td>" + menu.getF() + "</td>");
                    out.println("<td>" + menu.getC() + "</td>");
                    out.println("<td>" + menu.getS() + "</td>");
                    out.println("<td>" + menu.getNa() + "</td>");
                    out.println("<td>" + menu.getSf() + "</td>");
                    out.println("<td><a href='addToLunch?num=" + menu.getNum() + "'>점심에 추가</a></td>"); // "추가" 링크
                    out.println("</tr>");
                }
            } else {
                out.println("<tr>");
                out.println("<td colspan='12'>선택된 메뉴가 없습니다.</td>");
                out.println("</tr>");
            }

            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");
            
            out.println("<br><br>");
            out.println("</main>");
            out.println("<hr>");
            
            out.println("<footer>");
            
            out.println("<br><br>");
            out.println("<input type='submit' value='메뉴 랜덤 선택' onclick=\"location.href='gameSelect'\"/>");
            
            out.println("<div class=\"left\">");
            if ("ADMIN".equals(role)) {
                out.println("<br>");
                out.println("<a href='memberList'>회원 관리</a>");
            }
            out.println("</div>");
            
            out.println("<div class=\"footer-text\">Protein Cigarette</div>");
            out.println("</footer>");
            
            out.println("</body>");
            out.println("</html>");
        }
    }
}
