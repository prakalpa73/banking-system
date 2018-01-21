package co.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.db.DisplayTransaction;
import co.db.Provider;
import co.db.UserProfile;
import co.db.Users;

/**
 * Servlet implementation class UserDetails
 */
@WebServlet("/co.controller.UserDetails")
public class UserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Users u=new Users();
		HttpSession session = request.getSession();
		u.setUsername((String) session.getAttribute("uid"));
		
		try {
			Connection con = Provider.getConnection();
			String sql="SELECT banking_system.user_details.username,banking_system.user_details.name,banking_system.user_details.address,banking_system.user_details.acc_no,"+
  "banking_system.user_details.phone,banking_system.user_details.email,banking_system.user_details.acc_type,banking_system.user_details.dob,banking_system.user_details.city,"+
  "banking_system.user_details.district,banking_system.user_details.state,banking_system.user_details.pincode,banking_system.user_details.balance FROM "
  + "banking_system.user_details WHERE banking_system.user_details.username=? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, u.getUsername());
			System.out.println("Username for userProfile: "+u.getUsername());
			ResultSet rs = pst.executeQuery();
			RequestDispatcher rd=null;
			
				System.out.println("Fetching User Details...");
				ArrayList<UserProfile> details=new ArrayList<UserProfile>();
				while(rs.next())
				{
					UserProfile up=new UserProfile();
					up.username=rs.getString("username");
					up.name=rs.getString("name");
					up.address=rs.getString("address");
					up.acc_no=rs.getLong("acc_no");
					up.phone=rs.getString("phone");
					up.email=rs.getString("email");
					up.acc_type=rs.getString("acc_type");
					up.dob=rs.getString("dob");
					up.city=rs.getString("city");
					up.district=rs.getString("district");
					up.state=rs.getString("state");
					up.pincode=rs.getString("pincode");
					up.balance=rs.getInt("balance");
					details.add(up);
				}
				request.setAttribute("userProfile",details);

			    rd=request.getRequestDispatcher("/userDetails.jsp");
			    rd.forward(request,response);
			
			
		} catch (Exception e) {
			// TODO: handle exception
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
