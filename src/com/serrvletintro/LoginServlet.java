package com.serrvletintro;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebInitParam;

@WebServlet(
		description = "Login Servlet",
		urlPatterns = {"/LoginServlet"},
		initParams = {
				@WebInitParam(name = "user",value="Vaibhav"),
				@WebInitParam(name = "password", value = "abc@210")
		}
	)

	public class LoginServlet extends HttpServlet{
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String user = req.getParameter("user");
			String pwd = req.getParameter("pwd");
			
			String userID = getServletConfig().getInitParameter("user");
			String password = getServletConfig().getInitParameter("password");
			if (userID.equals(user) && password.equals(pwd) && user.matches("^[A-Z][a-z]{2,}") && pwd.matches("[[A-Z]{1,}[a-z]{1,}[0-9]{1,}[@$]{1}]{8,}")) {
				req.setAttribute("user", user);
				req.getRequestDispatcher("LoginSuccess.jsp").forward(req, resp);
			}else {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
				PrintWriter out = resp.getWriter();
				out.println("<font color = red>Wrong username or password</font>");
				rd.include(req, resp);
			}
		}
	}