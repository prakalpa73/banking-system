package co.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.db.DisplayTransaction;
import co.db.Provider;
import co.db.Users;

public class UsersDao {

	public static int save(Users u) {
		// TODO Auto-generated method stub
		int status1=0;
		int status=0;
		int status2=0;
		try {
			Connection con=Provider.getConnection();
			String sql="insert into login values(?,?)";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, u.getUsername());
			pst.setString(2, u.getPassword());
			status1 = pst.executeUpdate();
			if(status1>0){
				String sql2="insert into user_details(username,name,address,acc_no,phone,email,acc_type,dob,city,district,state,pincode,balance) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement pst1=con.prepareStatement(sql2);
				pst1.setString(1, u.getUsername());
				pst1.setString(2, u.getName());
				pst1.setString(3, u.getAddress());
				pst1.setLong(4, u.getAcc_no());
				pst1.setString(5, u.getPhone());
				pst1.setString(6, u.getEmail());
				pst1.setString(7, u.getAcc_type());
				pst1.setString(8, u.getDob());
				pst1.setString(9, u.getCity());
				pst1.setString(10, u.getDistrict());
				pst1.setString(11, u.getState());
				pst1.setString(12, u.getPincode());
				pst1.setInt(13, u.getBalance());
				status=pst1.executeUpdate();
				if(status > 0){
					String sql3="insert into transaction(username,credit,balance) values(?,?,?)";
					PreparedStatement pst2=con.prepareStatement(sql3);
					pst2.setString(1, u.getUsername());
					pst2.setInt(2, u.getBalance());
					pst2.setInt(3, u.getBalance());
					status2 = pst2.executeUpdate();	
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return status;
	}

	public static boolean validateUser(Users u) {
		// TODO Auto-generated method stub
		boolean result=false;
		try {
			Connection con=Provider.getConnection();
			String sql="SELECT * FROM banking_system.login WHERE banking_system.login.username=? and banking_system.login.password=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, u.getUsername());
			pst.setString(2, u.getPassword());
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				String username=rs.getString("username");
				u.setUsername(username);
				result=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public static String getPassword(Users u) {
		// TODO Auto-generated method stub
		String db_password=null;
		try {
			Connection con=Provider.getConnection();
			String sql="SELECT banking.login.password FROM banking.login INNER JOIN banking.user_details ON banking.user_details.username = banking.login.username where banking.user_details.email=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, u.getEmail());
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				db_password=rs.getString(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return db_password;
	}

	public static int deposit(Users u) throws SQLException {
		
		int status=0;
		int balance=0;
		Connection con=null;
		try {
			con=Provider.getConnection();
			con.setAutoCommit(false);
			String sql="SELECT banking_system.user_details.balance FROM banking_system.transaction,banking_system.user_details"
					+ " WHERE banking_system.user_details.username=? AND tid=(SELECT tid FROM banking_system.transaction LIMIT 1)";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, u.getUsername());
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				balance=rs.getInt(1);
				u.setBalance(balance);
				System.out.println("Current Balance: "+u.getBalance());
				int total_bal=u.getCredit()+u.getBalance();
				u.setBalance(total_bal);
				System.out.println("Updated Balance: "+u.getBalance());
				String sql1="insert into transaction(username,credit,balance) values(?,?,?)";
				PreparedStatement pst1=con.prepareStatement(sql1);
				pst1.setString(1, u.getUsername());
				pst1.setInt(2, u.getCredit());
				pst1.setInt(3, u.getBalance());
				String sql2="update banking_system.user_details set balance=? where username=?";
				PreparedStatement pst2=con.prepareStatement(sql2);
				pst2.setInt(1, u.getBalance());
				pst2.setString(2, u.getUsername());
				pst2.executeUpdate();
				status=pst1.executeUpdate();
				con.commit();
			}
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
		}
		return status;
	}

	public static int withdraw(Users u) throws SQLException {
		// TODO Auto-generated method stub
		int status=0;
		int balance=0;
		Connection con=null;
		try {
			con=Provider.getConnection();
			con.setAutoCommit(false);
			String sql="SELECT banking_system.user_details.balance FROM banking_system.transaction,banking_system.user_details"
					+ " WHERE banking_system.user_details.username=? AND tid=(SELECT tid FROM banking_system.transaction LIMIT 1)";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, u.getUsername());
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				balance=rs.getInt(1);
				u.setBalance(balance);
				System.out.println("Current Balance: "+u.getBalance());
				if(u.getDebit()<u.getBalance() && u.getDebit() >=100)
				{
					int total_bal=u.getBalance()-u.getDebit();
					u.setBalance(total_bal);
					System.out.println("Updated Balance: "+u.getBalance());
					String sql1="insert into transaction(username,debit,balance) values(?,?,?)";
					PreparedStatement pst1=con.prepareStatement(sql1);
					pst1.setString(1, u.getUsername());
					pst1.setInt(2, u.getDebit());
					pst1.setInt(3, u.getBalance());
					String sql2="update banking_system.user_details set balance=? where username=?";
					PreparedStatement pst2=con.prepareStatement(sql2);
					pst2.setInt(1, u.getBalance());
					pst2.setString(2, u.getUsername());
					pst2.executeUpdate();
					status=pst1.executeUpdate();
					con.commit();
				}
				else
				{
					con.rollback();
					System.out.println("You may be entered a amount that is less than 100 or you don't have sufficient balance in your account");
				}
			}
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
		}
		return status;
	}

	public static int transfer(Users u) throws SQLException {
		// TODO Auto-generated method stub
		int status3=0,status1=0;
		int balance=0;
		Connection con=null;
		try {
			con=Provider.getConnection();
			con.setAutoCommit(false);
			String sql="SELECT banking_system.user_details.balance,banking_system.user_details.acc_no FROM banking_system.user_details WHERE banking_system.user_details.username=? ";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, u.getUsername());
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				balance=rs.getInt(1);
				u.setBalance(balance);
				System.out.println("My Current Balance: "+u.getBalance());
				long acc_no=rs.getLong(2);
				u.setAcc_no(acc_no);
				System.out.println("My a/c no.: "+u.getAcc_no());
				int total_bal=u.getBalance()-u.getDebit();
				u.setBalance(total_bal);
				String sql5="update banking_system.user_details set balance=? where username=?";
				PreparedStatement pst5=con.prepareStatement(sql5);
				pst5.setInt(1, u.getBalance());
				pst5.setString(2, u.getUsername());
				int status=pst5.executeUpdate();
				System.out.println("Updated Balance: "+u.getBalance());
				if(status>0)
				{
					String sql4="insert into transaction(username,debit,recipient_acc_no,balance) values(?,?,?,?)";
					PreparedStatement pst4=con.prepareStatement(sql4);
					pst4.setString(1, u.getUsername());
					pst4.setInt(2, u.getDebit());
					pst4.setInt(3, (int) u.getRecipient_acc_no());
					pst4.setInt(4, u.getBalance());
					status1=pst4.executeUpdate();
					System.out.println("Inserting records into my a/c...");
				}
				else
				{
					con.rollback();
					System.out.println("rollback...");
					return 0;
				}
			}
			if(status1>0)
			{
				if(u.getDebit()<u.getBalance() && u.getRecipient_acc_no()!=u.getAcc_no())
				{
					System.out.println("Recipient Username: "+u.getRecipient_username());
					System.out.println("Recipient a/c no.: "+u.getRecipient_acc_no());
					System.out.println("Recipient current a/c balance: "+u.getRecipient_acc_bal());
					
						int recipient_total_acc_bal=u.getRecipient_acc_bal()+u.getDebit();
						u.setRecipient_acc_bal(recipient_total_acc_bal);
						String sql3="update banking_system.user_details set balance=? where username=?";
						PreparedStatement pst3=con.prepareStatement(sql3);
						pst3.setInt(1, u.getRecipient_acc_bal());
						pst3.setString(2, u.getRecipient_username());
						int status2=pst3.executeUpdate();
						System.out.println("Updated Balance of recipient: "+u.getRecipient_acc_bal());
						if(status2>0)
						{
							String sql2="insert into transaction(username,credit,sender_acc_no,balance) values(?,?,?,?)";
							PreparedStatement pst2=con.prepareStatement(sql2);
							pst2.setString(1, u.getRecipient_username());
							pst2.setInt(2, u.getDebit());
							pst2.setInt(3, (int) u.getAcc_no());
							pst2.setInt(4, u.getRecipient_acc_bal());
							System.out.println("Inserting records into recipient a/c...");
							status3=pst2.executeUpdate();
							con.commit();
							
						}
						else
						{
							con.rollback();
							System.out.println("rollback...");
							return 0;
						}
				}
				else
				{
					con.rollback();
					System.out.println("rollback...You can't send money to yourself");
					return 0;
				}
			}
			else
			{
				con.rollback();
				System.out.println("rollback...");
				return 0;
			}
				
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
		}
		if(status3>0)
			return status3;
		else
			return 0;
	}

	public static String getRecipientUsername(Users u) {
		// TODO Auto-generated method stub
		String db_recipientUsername=null;
		try {
			Connection con=Provider.getConnection();
			String sql="SELECT banking_system.user_details.username FROM banking_system.user_details WHERE banking_system.user_details.acc_no =? ";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setLong(1, u.getRecipient_acc_no());
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				db_recipientUsername=rs.getString(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return db_recipientUsername;
	}

	public static int getRecipientBalance(Users u) {
		// TODO Auto-generated method stub
		int db_recipientBalance=0;
		try {
			Connection con=Provider.getConnection();
			String sql="SELECT banking_system.user_details.balance FROM banking_system.user_details WHERE banking_system.user_details.username=? ";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, u.getRecipient_username());
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				db_recipientBalance=rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return db_recipientBalance;
	}

	public static String getUsername(Users u) {
		// TODO Auto-generated method stub
		String db_username=null;
		try {
			Connection con=Provider.getConnection();
			String sql="SELECT banking.login.username FROM banking.login INNER JOIN banking.user_details ON banking.user_details.username = banking.login.username where banking.user_details.email=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, u.getEmail());
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				db_username=rs.getString(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return db_username;
	}

}