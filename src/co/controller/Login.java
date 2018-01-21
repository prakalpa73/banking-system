package co.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.db.Users;
import co.model.UsersDao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/co.controller.Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username=request.getParameter("uid");
		String password=request.getParameter("pwd");
		
		Users u=new Users();
		u.setUsername(username);
		u.setPassword(password);
		
		boolean result=UsersDao.validateUser(u);
		if(result == true)
		{
			HttpSession session = request.getSession();
			session.setAttribute("uid", u.getUsername());
			response.sendRedirect("menu.jsp");
	
		}
		else
		{
			request.setAttribute("INVALID","Username or Password is incorrect");
			RequestDispatcher rd=request.getRequestDispatcher("default.jsp");
			rd.include(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
