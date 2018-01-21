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
 * Servlet implementation class Transfer
 */
@WebServlet("/co.controller.Transfer")
public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transfer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int debit= Integer.parseInt(request.getParameter("amount"));
		//int credit= Integer.parseInt(request.getParameter("amount"));
		long recipient_acc_no=Integer.parseInt(request.getParameter("recipient_acc_no"));
		
		Users u = new Users();
		HttpSession session=request.getSession();
		u.setUsername((String)session.getAttribute("uid"));
		u.setDebit(debit);
		//u.setCredit(credit);
		u.setRecipient_acc_no(recipient_acc_no);
		
		String recipientUsername=UsersDao.getRecipientUsername(u);
		u.setRecipient_username(recipientUsername);

		int recipient_acc_bal=UsersDao.getRecipientBalance(u);
		u.setRecipient_acc_bal(recipient_acc_bal);
		
		int status=0;
		try {
			status=UsersDao.transfer(u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd ;
		if(status>0){
			request.setAttribute("Transferred","Money Transferred Sucessfully");
			rd=request.getRequestDispatcher("/transactn.jsp");
			System.out.println("Transferred Amount: "+u.getDebit());
			rd.forward(request,response);
		}
		else{
			request.setAttribute("trans_failed","Money Transfer Failed");
			rd=request.getRequestDispatcher("/transactn.jsp");
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
