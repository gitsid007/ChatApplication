/* Project Done by 
   Name: Arun Raju Gudapati;
   WiuId#:916-23-5979;
   Name: Siddhartha Reddy Sambidi;
   WiuId#:916-59-4705;
*/

/*This is the program for Music Store database management system.
This has two end users and they are store, customer*/

import java.sql.*;
import java.io.*;

class dbproject {

	static BufferedReader keyboard; // Needed for keyboard I/O.
	static Connection conn; // A connection to the DB must be established

	public static void main(String args[]) throws IOException {
		String username = "srs148";//username and password 
		String password = "Mahesh07";
		keyboard = new BufferedReader(new InputStreamReader(System.in));
		try { // Errors will throw a "SQLException" (caught below)
				// Load the Oracle JDBC driver
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			System.out.println("Registered the driver...");
			// Connect to the database. The first argument is the
			// connection string, the second is your username, the third is
			// your password.
			conn = DriverManager.getConnection("jdbc:oracle:thin:@oracle1.wiu.edu:1521/toolman.wiu.edu", username, password);
			conn.setAutoCommit(true);
			int status = 1, enterloop = 1, choice = 0, choice1=0, choice2=0;
			// This is while loop that loops until the user wants to
			// exit.
			/*
			 * Here the options below helps different types of users to use the
			 * program. Based on the type of user selected, the options vary.
			 */
		
	
			while (enterloop == 1)
			{
				System.out.println("1.Customer");
				System.out.println("2.Store");
				System.out.println("3.Exit the program");
				System.out.println("Enter your choice:");
				choice = Integer.parseInt(keyboard.readLine());
				status=1;
				while (status == 1)
				{
					if (choice == 1)
					{
						System.out.println("\n----------------------------");
						System.out.println("Welcome to the Music Store Customer");
						System.out.println("1.Show all albums order by price.");
						System.out.println("2.Show all albums composed by a particular artist.");
						System.out.println("3.Show the albums purchased by a customer.");
						System.out.println("11.Exit");
						System.out.println("Enter a number of your choice from below menu:");
						System.out.println("---------------------------");
						choice1 = Integer.parseInt(keyboard.readLine());
					}
					
					else if (choice == 2)
					{
						System.out.println("\n----------------------------");
						System.out.println("Welcome Store Employee");
						System.out.println("4.Insert the new customer into customer table.");
						System.out.println("5.Update the album price when there is a change in price.");
						System.out.println("6.select the purchase data which is more than 10 year old.");
						System.out.println("7.Retrieve all the albums available in store.");
						System.out.println("8.Retrieve all songs in order by Genre.");
						System.out.println("9.Retrieve all artists who has composed more than 5 albums in the store.");
						System.out.println("10.Retrieve the album count of a each album to keep track of albums in the store");
						System.out.println("12.Exit");
						System.out.println("Enter a number of your choice from below menu:");
						System.out.println("---------------------------");
						choice2 = Integer.parseInt(keyboard.readLine());
					} 
					
					else if (choice == 3)
					{
						System.exit(0);
					}
					
					else if (choice >= 4)
					{
						status=1;enterloop=1;
						System.out.println("\nInvalid Input. Please enter valid input from above menu:");
						break;
					}

					if (choice1 == 1)
					{
						customerQuery1();
					} 
					
					else if (choice1 == 2)
					{
						customerQuery2();
					}
					
					else if (choice1 == 3)
					{
						
						customerQuery3();
					}
					
					else if (choice2 == 4)
					{
						storeQuery1();
					}
					
					else if (choice2 == 5)
					{
		
						storeQuery2();
					}
					
					else if (choice2 == 6)
					{
						storeQuery3();
					}
					
					else if (choice2 == 7)
					{
						storeQuery4();
					}
					
					else if (choice2 == 8)
					{
						storeQuery5();
					}
					
					else if (choice2 == 9)
					{
						storeQuery6();
					}
					
					else if (choice2 == 10)
					{
						storeQuery7();
					}
				    
					else if (choice1==11)
					{
						status=0;
				
					}
					
					else if (choice2==12)
					{
						status=0;
				
					}
					
					else
		
						System.out.println("Invalid input. Please enter valid input from above menu:");
				}
			}
			}// ends the try
			
         // Catches any exception caught from the try block.
			catch (SQLException e) {
			System.out.println("Caught SQL Exception: \n     " + e);
		}
		}
	
	
	/* This method is used to Retrieve albums order by price. */
	public static void customerQuery1() throws IOException, SQLException {
	try{ 
		Statement stmt;
		stmt = conn.createStatement();
		ResultSet rset1 = stmt.executeQuery("SELECT ALBUMNAME, RATING, PRICE FROM ALBUM ORDER BY PRICE"); //method to execute query
		ResultSetMetaData rsmd = rset1.getMetaData();
		//prints column name
		int colCount = rsmd.getColumnCount();
		for (int i = 1; i <= colCount; i++) {
			System.out.print(rsmd.getColumnName(i) + "   ");
		}
		System.out.println("\n----------------------------------------------");
		//prints column data
		while (rset1.next()) {
			for (int i = 1; i <= colCount; i++) {
				System.out.print(rset1.getString(i) + "   ");
			}
			System.out.println();
		}
		}
		catch(SQLException e)
			{
				System.out.println("Caught SQL Exception: \n     " + e);
			}
	}
	
	
	/* This method is used to Retrieve all albums composed by a particular artist. */
	public static void customerQuery2() throws IOException, SQLException {
	try{
		Statement stmt;
		stmt = conn.createStatement();
		System.out.println("Enter the ArtistName(Ex: Ar Rehman):");
		String artistname = keyboard.readLine(); /* Reads artist name as input from the user */
		ResultSet rset1 = stmt.executeQuery("SELECT ALBUMNAME, ARTISTNAME FROM ALBUM, ARTIST WHERE ARTIST.ARTISTID = ALBUM.ARTISTID AND ARTISTNAME ='" + artistname+"'"); //method to execute query
		ResultSetMetaData rsmd = rset1.getMetaData();
		//prints column name
		int colCount = rsmd.getColumnCount();
		for (int i = 1; i <= colCount; i++) {
			System.out.print(rsmd.getColumnName(i) + "   ");
		}
		System.out.println("\n----------------------------------------------");
		//prints column data
		while (rset1.next()) {
			for (int i = 1; i <= colCount; i++) {
				System.out.print(rset1.getString(i) + "   ");
			}
			System.out.println();
		}
		}
		catch(SQLException e)
			{
				System.out.println("Caught SQL Exception: \n     " + e);
			}
	}
	
	
	 /* This method is used to Retrieve the albums purchased by a particular customer. */
	public static void customerQuery3() throws IOException, SQLException {
	try{
		Statement stmt;
		stmt = conn.createStatement();
		System.out.println("Enter the CustomerID (Ex: C001):");
		String customerid = keyboard.readLine(); /* Reads customer ID as input from the user */
		ResultSet rset1 = stmt.executeQuery("SELECT ALBUMNAME, CUSTOMER.CUSTOMERID FROM ALBUM, PURCHASE, CUSTOMER WHERE CUSTOMER.CUSTOMERID = PURCHASE.CUSTOMERID AND PURCHASE.PURCHASEID = ALBUM.PURCHASEID AND CUSTOMER.CUSTOMERID ='" + customerid+"'"); //method to execute query
		ResultSetMetaData rsmd = rset1.getMetaData();
		//prints column name
		int colCount = rsmd.getColumnCount();
		for (int i = 1; i <= colCount; i++) {
			System.out.print(rsmd.getColumnName(i) + "   ");
		}
		System.out.println("\n----------------------------------------------");
		//prints column data
		while (rset1.next()) {
			for (int i = 1; i <= colCount; i++) {
				System.out.print(rset1.getString(i) + "   ");
			}
			System.out.println();
		}
		}
		catch(SQLException e)
			{
				System.out.println("Caught SQL Exception: \n     " + e);
			}
	}
	
	
	/* This method is used to Insert the new customer into customer table. */
	public static void storeQuery1() throws IOException, SQLException {
	try{
		Statement stmt;
		stmt = conn.createStatement();
		System.out.println("Enter the CustomerID (EX: C150)");
		String customerid = keyboard.readLine(); //reads customer id from user
		System.out.println("Enter the customer name(Ex: Ron):");
		String customername = keyboard.readLine();  //reads customer name as input from user
		System.out.println("Enter the customer mobile number(EX: 345654123):");
		int mobileno = Integer.parseInt(keyboard.readLine());		//reads mobile number as input from user		
		System.out.println("Enter the customer email (EX: ron@gmail.com):");  
		String customeremail = keyboard.readLine();		//reads email as input from user		
		String sq1 = "INSERT INTO CUSTOMER VALUES('"+customerid+"',"                       // sql command for insert row into customer table
						+ "'" +customername+"',"
            			+ ""+mobileno+","
            			+ "'" +customeremail+"')";
		
		stmt.executeUpdate(sq1);                              // executing the insert statement 
		stmt.executeUpdate("commit");							// commiting the insert statement
        System.out.println("Inserted the row into CUSTOMER table");
											
	}
	catch(SQLException e)
			{
				System.out.println("Caught SQL Exception: \n     " + e);
			}
	}
	
	
	/* This method is used to update albums available in store. */
	public static void storeQuery2() throws IOException, SQLException {
	try{
		Statement stmt;
		stmt = conn.createStatement();
		System.out.println("Enter the AlbumID");
		String albumid = keyboard.readLine(); //reads album id from user
		ResultSet rset1 = stmt.executeQuery("UPDATE ALBUM SET PRICE = 16 WHERE  ALBUMID ='" +albumid+"'"); //method to execute query
		ResultSetMetaData rsmd = rset1.getMetaData();
		System.out.println("The row is updated");
		}
		catch(SQLException e)
			{
				System.out.println("Caught SQL Exception: \n     " + e);
			}
	}
	
	
	/* This method is used to Retrieve all the albums available in store. */
	public static void storeQuery3() throws IOException, SQLException {
	try{
		Statement stmt;
		stmt = conn.createStatement();
		System.out.println("Enter the PurchaseDate (EX: 23-FEB-07)");
		String purchasedate = keyboard.readLine();
		ResultSet rset1 = stmt.executeQuery("SELECT PURCHASEDATE FROM PURCHASE WHERE PURCHASEDATE <'" +purchasedate+"'"); //method to execute query
		ResultSetMetaData rsmd = rset1.getMetaData();
		//prints column name
		int colCount = rsmd.getColumnCount();
		for (int i = 1; i <= colCount; i++) {
			System.out.print(rsmd.getColumnName(i) + "   ");
		}
		System.out.println("\n----------------------------------------------");
		//prints column data
		while (rset1.next()) {
			for (int i = 1; i <= colCount; i++) {
				System.out.print(rset1.getString(i) + "   ");
			}
			System.out.println();
		}
		}
		catch(SQLException e)
			{
				System.out.println("Caught SQL Exception: \n     " + e);
			}
	}
	
	
	/* This method is used to Retrieve all the albums available in store. */
	public static void storeQuery4() throws IOException, SQLException {
	try{
		Statement stmt;
		stmt = conn.createStatement();
		ResultSet rset1 = stmt.executeQuery("SELECT ALBUMID, ALBUMNAME, PRICE, RATING FROM ALBUM"); //method to execute query
		ResultSetMetaData rsmd = rset1.getMetaData();
		//prints column name
		int colCount = rsmd.getColumnCount();
		for (int i = 1; i <= colCount; i++) {
			System.out.print(rsmd.getColumnName(i) + "   ");
		}
		System.out.println("\n----------------------------------------------");
		//prints column data
		while (rset1.next()) {
			for (int i = 1; i <= colCount; i++) {
				System.out.print(rset1.getString(i) + "   ");
			}
			System.out.println();
		}
		}
		catch(SQLException e)
			{
				System.out.println("Caught SQL Exception: \n     " + e);
			}
	}
	
	
	/* This method is used to Retrieve all songs in order by Genre. */
	public static void storeQuery5() throws IOException, SQLException {
	try{
		Statement stmt;
		stmt = conn.createStatement();
		ResultSet rset1 = stmt.executeQuery("SELECT * FROM SONG ORDER BY GENRE ASC"); //method to execute query
		ResultSetMetaData rsmd = rset1.getMetaData();
		//prints column name
		int colCount = rsmd.getColumnCount();
		for (int i = 1; i <= colCount; i++) {
			System.out.print(rsmd.getColumnName(i) + "   ");
		}
		System.out.println("\n----------------------------------------------");
		//prints column data
		while (rset1.next()) {
			for (int i = 1; i <= colCount; i++) {
				System.out.print(rset1.getString(i) + "   ");
			}
			System.out.println();
		}
		}
		catch(SQLException e)
			{
				System.out.println("Caught SQL Exception: \n     " + e);
			}
	}
	
	
	/* This method is used to Retrieve all artists who has composed more than 5 albums in the store. */
	public static void storeQuery6() throws IOException, SQLException {
	try{
		Statement stmt;
		stmt = conn.createStatement();
		ResultSet rset1 = stmt.executeQuery("SELECT ARTISTNAME, Count(ALBUMID) AS AlbumsComposed FROM ARTIST inner join ALBUM USING(ARTISTID) GROUP BY ARTISTNAME HAVING COUNT(ALBUMID) > 1"); //method to execute query
		ResultSetMetaData rsmd = rset1.getMetaData();
		//prints column name
		int colCount = rsmd.getColumnCount();
		for (int i = 1; i <= colCount; i++) {
			System.out.print(rsmd.getColumnName(i) + "   ");
		}
		System.out.println("\n----------------------------------------------");
		//prints column data
		while (rset1.next()) {
			for (int i = 1; i <= colCount; i++) {
				System.out.print(rset1.getString(i) + "   ");
			}
			System.out.println();
		}
		}
		catch(SQLException e)
			{
				System.out.println("Caught SQL Exception: \n     " + e);
			}
	}
	
	
	/* This method is used toRetrieve the album count of a each album to keep track of albums in the store */
	public static void storeQuery7() throws IOException, SQLException {
	try{
		Statement stmt;
		stmt = conn.createStatement();
		ResultSet rset1 = stmt.executeQuery("SELECT ALBUMNAME, COUNT(ALBUMID) AS AlbumCount FROM ALBUM GROUP BY ALBUMNAME"); //method to execute query
		ResultSetMetaData rsmd = rset1.getMetaData();
		//prints column name
		int colCount = rsmd.getColumnCount();
		for (int i = 1; i <= colCount; i++) {
			System.out.print(rsmd.getColumnName(i) + "   ");
		}
		System.out.println("\n----------------------------------------------");
		//prints column data
		while (rset1.next()) {
			for (int i = 1; i <= colCount; i++) {
				System.out.print(rset1.getString(i) + "   ");
			}
			System.out.println();
		}
		}
		catch(SQLException e)
			{
				System.out.println("Caught SQL Exception: \n     " + e);
			}
	}  

}
