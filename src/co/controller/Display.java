package co.controller;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import co.db.DisplayTransaction;
import co.db.Provider;
import co.db.Users;
import co.model.UsersDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Display
 */
@WebServlet("/co.controller.Display")
public class Display extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Display() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String from_date=request.getParameter("date_from");
		String to_date=request.getParameter("date_to");
		Users u = new Users();
		HttpSession session = request.getSession();
		u.setUsername((String) session.getAttribute("uid"));
		u.setFrom_date(from_date);
		System.out.println("From_date: "+u.getFrom_date());
		u.setTo_date(to_date);
		System.out.println("To_date: "+u.getTo_date());
		try {
			Connection con = Provider.getConnection();
			String sql="SELECT banking_system.transaction.credit,banking_system.transaction.debit,banking_system.transaction.date,banking_system.transaction.sender_acc_no,"+
  "banking_system.transaction.recipient_acc_no,banking_system.transaction.balance FROM banking_system.transaction "
  + "WHERE banking_system.transaction.username=? AND DATE(date) BETWEEN ? AND ? ";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, u.getUsername());
			pst.setString(2, u.getFrom_date());
			pst.setString(3, u.getTo_date());
			ResultSet rs=pst.executeQuery();
			RequestDispatcher rd=null;
			if(rs.next())
			{
			System.out.println("Fetching your records...");
			ArrayList<DisplayTransaction> records=new ArrayList<DisplayTransaction>();
			while(rs.next())
			{
				DisplayTransaction rcds= new DisplayTransaction();
				rcds.credit=rs.getInt("credit");
				rcds.debit=rs.getInt("debit");
				rcds.date=rs.getString("date");
				rcds.sender_acc_no=rs.getLong("sender_acc_no");
				rcds.recipient_acc_no=rs.getLong("recipient_acc_no");
				rcds.balance=rs.getLong("balance");
				records.add(rcds);
			}
		    request.setAttribute("account_details",records);

		    rd=request.getRequestDispatcher("/display.jsp");
		    rd.forward(request,response);
			}
			else
			{
				request.setAttribute("no_rcds", "No Records Found");
				rd=request.getRequestDispatcher("/display1.jsp");
				rd.include(request,response);
			}
		}
	catch(Exception e)
			{
				e.printStackTrace();
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
