package com.jeommechu.web.member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.jeommechu.menu.user.MemberDAO;
import com.jeommechu.menu.user.MemberVO;

@WebServlet("/memberList")
public class MemberListServlet extends HttpServlet {

    private MemberDAO memberDAO;

    @Override
    public void init() throws ServletException {
        memberDAO = new MemberDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
    	try {
            List<MemberVO> members = memberDAO.getAllMembers();
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><title>회원 목록</title></head>");
            out.println("<body>");
            out.println("<h1>회원 목록</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>회원 ID</th><th>회원 이름</th><th>권한</th><th>수정</th><th>삭제</th></tr>");
            
            for (MemberVO member : members) {
                out.println("<tr>");
                out.println("<td>" + member.getId() + "</td>");
                out.println("<td>" + member.getMemberID() + "</td>");
                out.println("<td>" + member.getMemberName() + "</td>");
                out.println("<td>" + member.getRole() + "</td>");
                out.println("<td><a href='memberEdit?id=" + member.getId() + "'>수정</a></td>");
                out.println("<td><a href='memberDelete?id=" + member.getId() + "'>삭제</a></td>");
                out.println("</tr>");
            }
        
            out.println("</table>");
            
            out.println("<a href='getLunches'>메인화면</a>");
            
            out.println("</body>");
            out.println("</html>");
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database error", e);
        }
    }
}
