
package com.jeommechu.web.game;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeommechu.menu.lunch.LunchDAO;

@WebServlet("/gameTwo")
public class GameTwoServlet extends HttpServlet {
    private LunchDAO lunchDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        lunchDAO = new LunchDAO(); // LunchDAO 인스턴스를 초기화합니다
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> menuList;
        try {
            menuList = lunchDAO.getAllMenuItems(); // 모든 메뉴 이름을 가져옵니다
        } catch (SQLException e) {
            throw new ServletException("Error retrieving menu items", e);
        }

        if (menuList.isEmpty()) {
            menuList.add("No menu items found");
        }

        // 랜덤으로 메뉴를 선택합니다
        Random random = new Random();
        String randomMenu = menuList.get(random.nextInt(menuList.size()));

        // 확률 계산
        double probability = 100.0 / menuList.size();

        // 이미지의 상대 경로 설정
        String imageURL = request.getContextPath() + "/images/random.png";  // 경로를 맞춰주세요

        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("<html>");
        response.getWriter().println("<head>");
        response.getWriter().println("<title>Random Lunch Picker</title>");
        response.getWriter().println("<link rel='stylesheet' href='static/game.css'>");
        response.getWriter().println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css'>");
        
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<center>");
        response.getWriter().println("<section id=\"bg\">");
        response.getWriter().println("<div class='container'>");

        // 점심 메뉴 목록 및 제비뽑기 기능
        response.getWriter().println("<h1>점심 메뉴 뽑기</h1>");
        response.getWriter().println("<br><br>");
        
        // 이미지 삽입
        response.getWriter().println("<div class='image-container'>");
        response.getWriter().println("<img src='" + imageURL + "' alt='Random Food Image' width='150'>");
        response.getWriter().println("</div>");

        response.getWriter().println("<br><br>");
        response.getWriter().println("<button class=\"learn-more\" onclick=\"startDrawing()\">메뉴 뽑기 <i class='fas fa-dice'></i></button>");
        response.getWriter().println("<br><br><br>");
        
        response.getWriter().println("<div id=\"result\">");
        response.getWriter().println("오늘의 메뉴는?<br><br><span id=\"result-menu\">" + randomMenu + "</span>");
        response.getWriter().println("</div>");
        
        response.getWriter().println("<br><br><br>");

        // 확률 출력 부분 추가
        response.getWriter().println("<div id=\"probability\">");
        response.getWriter().println("각 메뉴 확률 : " + String.format("%.2f", probability) + "%");
        response.getWriter().println("</div>");

        response.getWriter().println("</div>"); // container 끝
        
        response.getWriter().println("<br><br><br>");
        response.getWriter().println("<a href='getLunches'>Main</a>");

        // JavaScript 코드
        response.getWriter().println("<script>");
        response.getWriter().println("function startDrawing() {");
        response.getWriter().println("    const menus = [");
        for (String menu : menuList) {
            response.getWriter().println("        \"" + menu + "\",");
        }
        response.getWriter().println("    ];");

        response.getWriter().println("    const resultSpan = document.getElementById('result-menu');");
        response.getWriter().println("    let index = 0;");
        response.getWriter().println("    const interval = setInterval(() => {");
        response.getWriter().println("        resultSpan.innerText = menus[index];");
        response.getWriter().println("        index = (index + 1) % menus.length;");
        response.getWriter().println("    }, 100);");

        response.getWriter().println("    setTimeout(() => {");
        response.getWriter().println("        clearInterval(interval);");
        response.getWriter().println("        const randomIndex = Math.floor(Math.random() * menus.length);");
        response.getWriter().println("        resultSpan.innerText = menus[randomIndex];");
        response.getWriter().println("    }, 3000);");
        response.getWriter().println("}");
        response.getWriter().println("</script>");
        response.getWriter().println("</center>");
        response.getWriter().println("<div class=\"footer-text\">Protein Cigarette</div>");
        response.getWriter().println("</section>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}
