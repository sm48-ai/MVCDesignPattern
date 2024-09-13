package in.sp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import in.sp.dbcon.DBConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/regForm")
public class Register extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		String myname=req.getParameter("name1");
		String myemail=req.getParameter("email1");
		String mypassword=req.getParameter("pass1");
		String mycity=req.getParameter("city1");
		try {
			Connection con=DBConnection.getConnection();
			String insertSqlQuery="insert into register values(?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(insertSqlQuery);
			ps.setString(1, myname);
			ps.setString(2, myemail);
			ps.setString(3, mypassword);
			ps.setString(4, mycity);
			
			int count=ps.executeUpdate();
			if(count>0) {
				out.println("<h2 style='color:green'>Registered Successfully</h2>");
				RequestDispatcher rd=req.getRequestDispatcher("/login.html");
				rd.include(req, resp);
			}else {
				out.println("<h2 style='color:red'>User not Registered</h2>");
				RequestDispatcher rd=req.getRequestDispatcher("/register.html");
				rd.include(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

}
