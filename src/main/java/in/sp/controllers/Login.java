package in.sp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import in.sp.dbcon.DBConnection;
import in.sp.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/loginForm")
public class Login extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		String myemail= req.getParameter("email1");
		String mypassword=req.getParameter("pass1");
		
		try {
			Connection con=DBConnection.getConnection();
			
			String selectSqlQuery="select * from register where email=? and password=?";
			PreparedStatement ps=con.prepareStatement(selectSqlQuery);
			ps.setString(1, myemail);
			ps.setString(2, mypassword);
			ResultSet rs= ps.executeQuery();
			if(rs.next()) {
				User u=new User();
				u.setName(rs.getString("name"));
				u.setEmail(rs.getString("email"));
				u.setCity(rs.getString("city"));
				
				HttpSession session=req.getSession();
				session.setAttribute("session_user", u);
				
				RequestDispatcher rd=req.getRequestDispatcher("/profile.jsp");
				rd.forward(req, resp);
			}else {
				
				out.println("<h2 style='color:red'>Email Id and Password didnt match</h2>");
			    
				RequestDispatcher rd=req.getRequestDispatcher("/login.html");
				rd.include(req, resp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
