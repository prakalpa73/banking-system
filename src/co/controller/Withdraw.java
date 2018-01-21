package co.controller;

import java.io.IOException;
import java.sql.SQLException;

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
 * Servlet implementation class Withdraw
 */
@WebServlet("/co.controller.Withdraw")
public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Withdraw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
int debit=Integer.parseInt(request.getParameter("with_amount"));
		
		Users u = new Users();
		HttpSession session=request.getSession();
		u.setUsername((String) session.getAttribute("uid"));
		u.setDebit(debit);
		
		int status = 0;
		try {
			status = UsersDao.withdraw(u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd ;
		if(status>0){
			request.setAttribute("Withdrawal","Money Withdrawal Sucessfull");
			rd=request.getRequestDispatcher("/withdraw.jsp");
			System.out.println("Withdraw amount: "+u.getDebit());
			rd.forward(request,response);
		}
		else{
			request.setAttribute("with_failed","You don't have Sufficient Balance in your Account");
			rd=request.getRequestDispatcher("/withdraw.jsp");
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
