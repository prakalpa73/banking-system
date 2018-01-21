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
 * Servlet implementation class Deposit
 */
@WebServlet("/co.controller.Deposit")
public class Deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deposit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int credit=Integer.parseInt(request.getParameter("depo_amount"));
		
		Users u = new Users();
		HttpSession session=request.getSession();
		u.setUsername((String) session.getAttribute("uid"));
		u.setCredit(credit);
		int status = 0;
		try {
			status = UsersDao.deposit(u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd ;
		if(status>0){
			request.setAttribute("Deposited","Money Deposited Sucessfully");
			rd=request.getRequestDispatcher("/deposit.jsp");
			System.out.println("Deposited Amount: "+u.getCredit());
			rd.forward(request,response);
		}
		else{
			request.setAttribute("depo_failed","Transaction Failed");
			rd=request.getRequestDispatcher("/deposit.jsp");
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
