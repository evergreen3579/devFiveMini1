package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@WebServlet("/random-food")
public class FoodServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> foodList = Arrays.asList("Pizza", "Burger", "Sushi", "Pasta", "Salad");

        // 랜덤으로 음식을 선택
        Random random = new Random();
        String randomFood = foodList.get(random.nextInt(foodList.size()));

        // 확률 계산
        double probability = 100.0 / foodList.size();

        // 이미지의 상대 경로 설정
        String imageURL = request.getContextPath() + "/images/random.png";  // 경로를 맞춰주세요

        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("<html>");
        response.getWriter().println("<head>");
        response.getWriter().println("<title>Random Food Picker</title>");
        response.getWriter().println("<link rel='stylesheet' href='style.css'>");
        response.getWriter().println("<script src='script.js'></script>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<div class='container'>");

        // 이미지 삽입
        response.getWriter().println("<div class='image-container'>");
        response.getWriter().println("<img src='" + imageURL + "' alt='Lottery Image' width='150'>");
        response.getWriter().println("</div>");

        // 제비뽑기 기능 및 UI
        response.getWriter().println("<div>");
        response.getWriter().println("<h1>음식 리스트</h1>");
        response.getWriter().println("<ul>");
        for (String food : foodList) {
            response.getWriter().println("<li>" + food + "</li>");
        }
        response.getWriter().println("</ul>");

        response.getWriter().println("<button onclick=\"startDrawing()\">제비뽑기 시작 <i class='fas fa-dice'></i></button>");

        response.getWriter().println("<div id=\"result\">");
        response.getWriter().println("오늘의 음식은: <span id=\"result-food\">" + randomFood + "</span>");
        response.getWriter().println("</div>");

        // 확률 출력 부분 추가
        response.getWriter().println("<div id=\"probability\">");
        response.getWriter().println("각 음식이 뽑힐 확률: " + String.format("%.2f", probability) + "%");
        response.getWriter().println("</div>");

        response.getWriter().println("</div>"); // 제비뽑기 기능이 포함된 div
        response.getWriter().println("</div>"); // container 끝

        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}

---------------------------------------------------------------------------------------


CSS


  body {
    background-image: url('https://example.com/background.jpg');
    background-size: cover;
    font-family: Arial, sans-serif;
    color: #333;
    text-align: center;
    padding: 60px;
    animation: rainbowAnimation 10s infinite;
}

@keyframes rainbowAnimation {
    0% { background-color: #FF0000; } /* Red */
    14% { background-color: #FF7F00; } /* Orange */
    28% { background-color: #FFFF00; } /* Yellow */
    42% { background-color: #00FF00; } /* Green */
    57% { background-color: #0000FF; } /* Blue */
    71% { background-color: #4B0082; } /* Indigo */
    85% { background-color: #9400D3; } /* Violet */
    100% { background-color: #FF0000; } /* Red again to loop */
}

h1 {
    font-size: 48px;
    color: #FFD700;
    text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.5);
}

ul {
    list-style-type: none;
    padding: 0;
}

li {
    display: inline-block;
    margin-right: 15px;
    font-size: 24px;
    color: #FFD700;
}

button {
    font-size: 20px;
    padding: 10px 20px;
    background-color: #1E90FF;
    color: #ffffff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    margin-top: 20px;
    transition: background-color 0.3s;
}

button:hover {
    background-color: #104E8B;
}

#result {
    margin-top: 20px;
    font-size: 32px;
    font-weight: bold;
    color: #FFD700;
    text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.5);
    animation: slideIn 1s;
}

@keyframes slideIn {
    from { transform: translateY(-100px); opacity: 0; }
    to { transform: translateY(0); opacity: 1; }
}

#probability {
    margin-top: 50px;
    font-size: 24px;
    color: #FFFFFF;
}

.container { 
    display: flex; 
    align-items: center; 
    justify-content: center; 
}

.image-container { 
    margin-right: 50px; 
} /* 이미지와 텍스트 간의 간격 설정 */


-------------------------------------------------

  js



  function startDrawing() {
    const foods = ["Pizza", "Burger", "Sushi", "Pasta", "Salad"];

    const resultSpan = document.getElementById('result-food');
    let index = 0;
    const interval = setInterval(() => {
        resultSpan.innerText = foods[index];
        index = (index + 1) % foods.length;
    }, 100);

    setTimeout(() => {
        clearInterval(interval);
        const randomIndex = Math.floor(Math.random() * foods.length);
        resultSpan.innerText = foods[randomIndex];
    }, 3000);
}

