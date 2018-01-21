package co.controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.db.Users;
import co.model.UsersDao;


/**
 * Servlet implementation class ac_creation
 */
@WebServlet("/co.controller.Ac_creation")
public class Ac_creation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ac_creation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username=request.getParameter("uid");
		String password=request.getParameter("pwd");
		String acc_type=request.getParameter("slt");
		int balance=Integer.parseInt(request.getParameter("balance"));
		String name=request.getParameter("name");
		String dob=request.getParameter("dob");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String address=request.getParameter("text");
		String city=request.getParameter("city");
		String district=request.getParameter("district");
		String state=request.getParameter("state");
		String pincode=request.getParameter("pincode");
		long acc_no = Math.round(Math.random()*10000000);
		
		Users u=new Users();
		u.setUsername(username);
		u.setPassword(password);
		u.setAcc_type(acc_type);
		u.setBalance(balance);
		u.setName(name);
		u.setDob(dob);
		u.setEmail(email);
		u.setPhone(phone);
		u.setAddress(address);
		u.setCity(city);
		u.setDistrict(district);
		u.setState(state);
		u.setPincode(pincode);
		u.setAcc_no(acc_no);
		
		int status= UsersDao.save(u);
		RequestDispatcher rd;
			if(status>0){
				request.setAttribute("Created","Account Sucessfully Created");
				rd=request.getRequestDispatcher("/default.jsp");
				rd.forward(request,response);
			}
			else{
				request.setAttribute("Failed","Couldn't Create your account ");
				rd=request.getRequestDispatcher("/create_ac.jsp");
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
