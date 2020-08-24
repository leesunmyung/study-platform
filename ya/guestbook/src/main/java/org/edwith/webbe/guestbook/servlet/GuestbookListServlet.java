package org.edwith.webbe.guestbook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.edwith.webbe.guestbook.dao.GuestbookDao;
import org.edwith.webbe.guestbook.dto.Guestbook;
import org.edwith.webbe.guestbook.servlet.GuestbookWriteServlet;;
@WebServlet("/guestbook")
public class GuestbookListServlet extends HttpServlet {

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 코드를 작성하세요.
    	response.setContentType("text/html; charset=utf-8");
    	PrintWriter out = response.getWriter();
    	ServletContext application = getServletContext();
    	GuestbookDao dao = new GuestbookDao();
    	List<Guestbook> list = dao.getGuestbooks();
    	application.setAttribute("list", list);
    	
    	response.sendRedirect("http://localhost:8080/guestbooks");
    	out.close();
    }

}
