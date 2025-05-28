package com.pscode.nourish_now.utility;

public class PreDefineMessage {
	
	/*
	 * Pre Defined Messages For the Users
	 */
	//Messages
	
	public final static String USER_APPROVED_SUCCESSFULLY = "User Get Approved Successfully!!!";
	public final static String DATA_INSERTED_SUCCESSFULLY = "Data Inserted Successfully!!!";
	public final static String SOMETHING_WENT_WRONG = "Something Went Wrong!!!";
	public final static String DETAILS_ARE_VERIFYING = "Your Details Not Verified Yet!!!\n Please Stay Tune!!";
	public final static String USER_NOT_FOUND = "User Not Found!!";
	public final static String INVALID_DETAILS = "Hotel Or NGO Details Are Invalid!!!";


	
	//QUERY
	
	public final static String FIND_USER_BY_EMAIL = "SELECT u FROM Users u WHERE u.email = :email";
	public final static String UPDATE_USER_STATUS_BY_ID = "UPDATE Users u SET u.status = :status WHERE u.id = :id";
	public final static String FIND_USER_BY_ID = "SELECT u FROM Users u WHERE u.id = :id";
	
	
	//Exception
	
	public final static String EMAIL_ALREADY_EXIST = "Email Already Exist!!";
	
	
	
}