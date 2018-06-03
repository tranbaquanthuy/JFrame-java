
	import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class ConnectSQL {
	public String n;
private static String DB_URL 
	    = ""jdbc:sqlserver://ip:port;databaseName=db_name;user=usercontrolthatdatabase;password=passofuser";";
   public boolean checkUsnData(String n,String npw)
	   {
		   try {
           Connection conn = getConnection(DB_URL);
           Statement stmt = conn.createStatement();
           ResultSet rs = stmt.executeQuery("select tentv,matkhau from ThanhVien");
           while(rs.next())
           {
        	 System.out.println(rs.getString("tentv").toString());
        	 String s = rs.getString("tentv").trim();
        	 String pw = rs.getString("matkhau").trim();
  	       if (n.toString().equals(s) && npw.toString().equals(pw))
  	       {
  	    	 System.out.println("trueeeee"); 
  	         return true;    
  		   }  
           }   
 	       conn.close();
		   }
		   catch(Exception ex)
		   {
			   ex.printStackTrace();	
		   }
		   System.out.println("falseeeee");
			 return false;
	   }
   public boolean checkMatvData(String n)
   {

	   try {
       Connection conn = getConnection(DB_URL);
       Statement stmt = conn.createStatement();
       ResultSet rs = stmt.executeQuery("select * from Thongtinnganhang");
       while(rs.next())
       {
    	 System.out.println(rs.getString("matv").toString());
    	 String s = rs.getString("matv").trim();
    	
	     if (n.toString().equals(s) )
	       {
	    	 System.out.println("trueeeee"); 
	         return true;
		   }  
       }   
	       conn.close();
	   }
	   catch(Exception ex)
	   {
		   ex.printStackTrace();	
	   }
	   System.out.println("falseeeee");
		 return false;
   }
   public String getMatvData(String username)
   {

	   try {
       Connection conn = getConnection(DB_URL);
       Statement stmt = conn.createStatement();
       ResultSet rs = stmt.executeQuery("select matv from ThanhVien where tentv = '" + username + "'" );
       while(rs.next())
       {
    	 System.out.println(rs.getString("matv").toString());
    	 String s = rs.getString("matv").trim();

	    	 System.out.println("trueeeee"); 
	         return s;

         }   
	       conn.close();
	   }
	   catch(Exception ex)
	   {
		   ex.printStackTrace();	
	   }
	   System.out.println("falseeeee");
	   return ("-1");
   }
	   public void depositRollDice(String n)
	   {  
		   try {
			   
	           Connection conn = getConnection(DB_URL);
	           Statement stmt = conn.createStatement();
	           ResultSet rs = stmt.executeQuery("select * from ThanhVien,Thongtinnganhang where tentv = '"+ n + "' AND ThanhVien.matv = Thongtinnganhang.matv");
	           Long s = null;
	           String matv1 = null ;
	           while(rs.next())
	           {
	        	System.out.println(rs.getString("money").toString());
	        	String money = rs.getString("money").trim();
	        	String matv = rs.getString("matv").trim();
	  	        s = Long.parseLong(money) - 200;
	  	        matv1 = matv ;
	           }   
	           String sql = "update Thongtinnganhang "  + "set money = '" + s.toString().trim() + "' where matv ='" + matv1 +"'";
		  	   stmt.executeUpdate(sql);
	           conn.close();
			   }
			   catch(Exception ex)
			   {
				   ex.printStackTrace();	
			   }
	   }
	   public void winReward(String n)
	   {   
		   try {
			   
	           Connection conn = getConnection(DB_URL);
	           Statement stmt = conn.createStatement();
	           ResultSet rs = stmt.executeQuery("select * from ThanhVien,Thongtinnganhang where tentv = '"+ n + "' AND ThanhVien.matv = Thongtinnganhang.matv");
	           Long s = null;
	           String matv1 = null ;
	           while(rs.next())
	           {
	        	System.out.println(rs.getString("money").toString());
	        	String money = rs.getString("money").trim();
	        	String matv = rs.getString("matv").trim();
	        	 matv1 = matv ;
	  	         s = Long.parseLong(money) + 2000;
	           }   
	           String sql = "update Thongtinnganhang "  + "set money = '" + s.toString().trim() + "' where matv ='" + matv1 +"'";
		  	   stmt.executeUpdate(sql);
	           conn.close();
			   }
			   catch(Exception ex)
			   {
				   ex.printStackTrace();	
			   }
			 
	   }
	   public void transferMoney(String username1,String money1,String matv2)
	   {   
		   
		  
		   try {
			   
	           Connection conn = getConnection(DB_URL);
	           Statement stmt = conn.createStatement();
	           ResultSet rs = stmt.executeQuery("select * from ThanhVien,Thongtinnganhang where tentv = '"+ username1 + "' AND ThanhVien.matv = Thongtinnganhang.matv");
	           Long s = null;
	           String matv1 = null ;
	           while(rs.next())
	           {
	        	System.out.println(rs.getString("money").toString());
	        	String money = rs.getString("money").trim();
	        	String matv = rs.getString("matv").trim();
	        	matv1 = matv ;
	  	         s = Long.parseLong(money) - Long.parseLong(money1);
	           }   
	           String sql = "update Thongtinnganhang "  + "set money = '" + s.toString().trim() + "' where matv ='" + matv1 +"'";
		  	   stmt.executeUpdate(sql);  
		  	 ResultSet rs2 = stmt.executeQuery("select * from Thongtinnganhang where matv = '"+ matv2+ "'");
		  	   Long s2 = null; 
		  	   while(rs2.next())
	           {
	        	System.out.println(rs2.getString("money").toString());
	        	String money = rs2.getString("money").trim();
	  	        s2 = Long.parseLong(money) + Long.parseLong(money1);
	           }   
		  	   String sql2 = "update Thongtinnganhang "  + "set money = '" + s2.toString().trim() + "' where matv ='" + matv2 +"'";
		  	   stmt.executeUpdate(sql2);
		  	   conn.close();
			   }
			   catch(Exception ex)
			   {
				   ex.printStackTrace();	
			   }
		  
	   }
	   public Double getMoney(String n)
	   {
    
		   try {
           Connection conn = getConnection(DB_URL);
           Statement stmt = conn.createStatement();
           ResultSet rs = stmt.executeQuery("select * from ThanhVien,Thongtinnganhang where tentv = '"+ n + "' AND ThanhVien.matv = Thongtinnganhang.matv");
           while(rs.next())
           {
        	 System.out.println(rs.getString("money").toString());
        	 String money = rs.getString("money").trim();
  	    	 System.out.println("trueeeee");	 
  	         return Double.parseDouble(money);
           }   
 	       conn.close();
		   }
		   catch(Exception ex)
		   {
			   ex.printStackTrace();	
		   }
		   System.out.println("falseeeee");
		   return 0.1;
	   }
	 
	    public Connection getConnection(String dbURL) {
	        Connection conn = null;
	        try {
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            conn = DriverManager.getConnection(dbURL);
	            System.out.println("connect successfully!");
	        } catch (Exception ex) {
	            System.out.println("connect failure!");
	            ex.printStackTrace();
	        }
	        return conn;
	    }
	    public  boolean  checkInternet()
	    {
	    	try {
	            final URL url = new URL("http://www.google.com");
	            final URLConnection conn = url.openConnection();
	            conn.connect();
	            return true;
	        } catch (MalformedURLException e) {
	            throw new RuntimeException(e);
	        } catch (IOException e) {
	            return false;
	            
	        }
	    }
	    public  String getPassword(String username)
	    {
	    	 try {
	    	       Connection conn = getConnection(DB_URL);
	    	       Statement stmt = conn.createStatement();
	    	       ResultSet rs = stmt.executeQuery("select matkhau from ThanhVien where tentv = '" + username + "'" );
	    	       while(rs.next())
	    	       {
	    	    	 System.out.println(rs.getString("matkhau").toString());
	    	    	 String s = rs.getString("matkhau").trim();

	    		    	 System.out.println("trueeeee"); 
	    		         return s;

	    	         }   
	    		       conn.close();
	    		   }
	    		   catch(Exception ex)
	    		   {
	    			   ex.printStackTrace();	
	    		   }
	    		   System.out.println("falseeeee");
	    		   return ("none");
	    }
	    public String getAccountBankNumber(String username)
	    {
	    	 try {
	    	       Connection conn = getConnection(DB_URL);
	    	       Statement stmt = conn.createStatement();
	    	       ResultSet rs = stmt.executeQuery("select matv from ThanhVien where tentv = '" + username + "'" );
	    	       while(rs.next())
	    	       {
	    	    	 System.out.println(rs.getString("matv").toString());
	    	    	 String s = rs.getString("matv").trim();

	    		    	 System.out.println("trueeeee"); 
	    		         return s;

	    	         }   
	    		       conn.close();
	    		   }
	    		   catch(Exception ex)
	    		   {
	    			   ex.printStackTrace();	
	    		   }
	    		   System.out.println("falseeeee");
	    		   return ("none");
	    }
	    public  String getEmail(String username)
	    {
	    	 try {
	    	       Connection conn = getConnection(DB_URL);
	    	       Statement stmt = conn.createStatement();
	    	       ResultSet rs = stmt.executeQuery("select email from ThanhVien where tentv = '" + username + "'" );
	    	       while(rs.next())
	    	         {
	    	    	 System.out.println(rs.getString("email").toString());
	    	    	 String s = rs.getString("email").trim();
                     System.out.println("trueeeee"); 
	    		     return s;
	    	         }   
	    		       conn.close();
	    		   }
	    		   catch(Exception ex)
	    		   {
	    			   ex.printStackTrace();	
	    		   }
	    		   System.out.println("falseeeee");
	    		   return ("none");
	    }
	    public String getAddress(String username)
	    {
	    	 try {
	    	       Connection conn = getConnection(DB_URL);
	    	       Statement stmt = conn.createStatement();
	    	       ResultSet rs = stmt.executeQuery("select diachi from ThanhVien where tentv = '" + username + "'" );
	    	       while(rs.next())
	    	       {
	    	    	 System.out.println(rs.getString("diachi").toString());
	    	    	 String s = rs.getString("diachi").trim();

	    		    	 System.out.println("trueeeee"); 
	    		         return s;

	    	         }   
	    		       conn.close();
	    		   }
	    		   catch(Exception ex)
	    		   {
	    			   ex.printStackTrace();	
	    		   }
	    		   System.out.println("falseeeee");
	    		   return ("none");
	    }
	    public  void setPassword(String username,String newpassword)
	    {
	    	 try {
	    	       Connection conn = getConnection(DB_URL);
	    	       Statement stmt = conn.createStatement();
	    	       String sql = "update ThanhVien set matkhau ='"+ newpassword + "' where tentv = '" + username + "'" ;
			  	   stmt.executeUpdate(sql);

	    		    	 System.out.println("trueeeee"); 
	    		         
	    		       conn.close();
	    		   }
	    		   catch(Exception ex)
	    		   {
	    			   ex.printStackTrace();	
	    		   }
	    		   
	    }
	    public  void setEmail(String username,String newemail)
	    {
	    	 try {
	    	       Connection conn = getConnection(DB_URL);
	    	       Statement stmt = conn.createStatement();
	    	       String sql = "update ThanhVien set email ='"+ newemail + "' where tentv = '" + username + "'" ;
			  	   stmt.executeUpdate(sql);
	    		   System.out.println("trueeeee");  
	    		   conn.close();
	    		   }
	    		   catch(Exception ex)
	    		   {
	    			   ex.printStackTrace();	
	    		   }
	    		   
	    }
	    public  void setAddress(String username,String newaddress)
	    {
	    	 try {
	    	       Connection conn = getConnection(DB_URL);
	    	       Statement stmt = conn.createStatement();
	    	       String sql = "update ThanhVien set diachi ='"+ newaddress + "' where tentv = '" + username + "'" ;
			  	   stmt.executeUpdate(sql);

	    		    	 System.out.println("trueeeee"); 
	    		         
	    		       conn.close();
	    		   }
	    		   catch(Exception ex)
	    		   {
	    			   ex.printStackTrace();	
	    		   }
	    		   
	    }
    public  boolean checkUSN(String username) {
	 try {
	       Connection conn = getConnection(DB_URL);
	       Statement stmt = conn.createStatement();
	       ResultSet rs = stmt.executeQuery("select tentv from ThanhVien where tentv = '" + username + "'" );
	       while(rs.next())
	       {
	    	 System.out.println(rs.getString("tentv").toString());
	    	 String s = rs.getString("tentv").trim();
             if(s.isEmpty() == false)
             {
		    	 System.out.println("trueeeee"); 
		         return true;
             }
	         }   
		       conn.close();
		   }
		   catch(Exception ex)
		   {
			   ex.printStackTrace();	
		   }
		   System.out.println("falseeeee");
		   return false ;
	    }
    public  boolean checkCoupon(String coupon) {
   	 try {
   	       Connection conn = getConnection(DB_URL);
   	       Statement stmt = conn.createStatement();
   	       ResultSet rs = stmt.executeQuery("select mathuong from MaThuong where mathuong = '" + coupon + "'" );
   	       while(rs.next())
   	       {
   	    	 System.out.println(rs.getString("mathuong").toString());
   	    	 String s = rs.getString("mathuong").trim();
                if(s.equals(coupon))
                {
   		    	 System.out.println("trueeeee"); 
   		         return false;
                }
   	         }   
   		       conn.close();
   		   }
   		   catch(Exception ex)
   		   {
   			   ex.printStackTrace();	
   		   }
   		   System.out.println("falseeeee");
   		   return true ;
   	    }
     public  String getMoneyFromCoupon(String coupon) {
      	 try {
      	       Connection conn = getConnection(DB_URL);
      	       Statement stmt = conn.createStatement();
      	    // System.out.println(coupon);
      	       ResultSet rs = stmt.executeQuery("select sotien from MaThuong where mathuong = '" + coupon + "'" );
      	       while(rs.next())
      	       {
      	    	 System.out.println(rs.getString("sotien").toString());
      	    	 String s = rs.getString("sotien").trim();
      	   	 System.out.println("trueeeee");
                   return s;
      		   
      	         }   
      		       conn.close();
      		   }
      		   catch(Exception ex)
      		   {
      			   ex.printStackTrace();	
      		   }
      		   System.out.println("fail to get money from coupon");
      		   return "error to get money ";
    }
     public  String getStatusFromCoupon(String coupon) {
      	 try {
      	       Connection conn = getConnection(DB_URL);
      	       Statement stmt = conn.createStatement();
      	       ResultSet rs = stmt.executeQuery("select tinhtrang from MaThuong where mathuong = '" + coupon + "'" );
      	       while(rs.next())
      	       {
      	    	 System.out.println(rs.getString("tinhtrang").toString());
      	    	 String s = rs.getString("tinhtrang").trim();
      	  	 System.out.println("trueeeee");
          return s;
      		    
      	         }   
      		       conn.close();
      		   }
      		   catch(Exception ex)
      		   {
      			   ex.printStackTrace();	
      		   }
      		   System.out.println("falseeeee");
      		   return "error";
    }
     public  void addCouponMoneyToBank(String matv,String moneycoupon)
	   {   
		   try {
			   
	           Connection conn = getConnection(DB_URL);
	           Statement stmt = conn.createStatement();
	           ResultSet rs = stmt.executeQuery("select money from Thongtinnganhang where matv = '"+ matv + "'" );
	           Long s = null;
	           while(rs.next())
	           {
	        	System.out.println(rs.getString("money").toString());
	        	String money = rs.getString("money").trim();
	  	         s = Long.parseLong(money) + Long.parseLong(moneycoupon);
	           }   
	           String sql = "update Thongtinnganhang "  + "set money = '" + s.toString().trim() + "' where matv ='" + matv +"'";
		  	   stmt.executeUpdate(sql);
	           conn.close();
			   }
			   catch(Exception ex)
			   {
				   ex.printStackTrace();	
			   }
			 
	   }
     public  void setStateOfCoupon(String matv,String couponcode)
	    {
	    	 try {
	    	       Connection conn = getConnection(DB_URL);
	    	       Statement stmt = conn.createStatement();
	    	       String sql = "update MaThuong set matv ='"+ matv +"',ngaynhan = GetDate(),tinhtrang ='danhan' where mathuong = '" + couponcode + "'" ;
			  	   stmt.executeUpdate(sql);
	    		    	 System.out.println("trueeeee"); 
	    		         
	    		       conn.close();
	    		   }
	    		   catch(Exception ex)
	    		   {
	    			   ex.printStackTrace();	
	    		   }
	    		   
	    }
    public boolean checkEmail(String email) {
   	 try {
   	       Connection conn = getConnection(DB_URL);
   	       Statement stmt = conn.createStatement();
   	       ResultSet rs = stmt.executeQuery("select email from ThanhVien where email = '" + email + "'" );
   	       while(rs.next())
   	       {
   	    	 System.out.println(rs.getString("email").toString());
   	    	 String s = rs.getString("email").trim();
                if(s.isEmpty() == false)
                {
   		    	 System.out.println("trueeeee"); 
   		         return true;
                }
   	         }   
   		       conn.close();
   		   }
   		   catch(Exception ex)
   		   {
   			   ex.printStackTrace();	
   		   }
   		   System.out.println("falseeeee");
   		   return false ;
   	    }
    public  String createbanknumber() {
    	try {
    	       Connection conn = getConnection(DB_URL);
    	       Statement stmt = conn.createStatement();
    	       ResultSet rs = stmt.executeQuery("SELECT MAX(matv) as maxmatv FROM ThanhVien");
    	       while(rs.next())
    	       {
    	    	 System.out.println(rs.getString("maxmatv").toString());
    	    	 String s = rs.getString("maxmatv").trim();
    	    	 long n =Long.parseLong(s) + 1;
    	    	 String matv = null;
    	    	 if(n < 10)
    	    	 {
    	    		 matv = "00" + String.valueOf(n);
    	    	 }
    	    	 else if ( n >= 10 && n < 100)
    	    	 {
    	    		 matv = "0" + String.valueOf(n);
    	    	 }
    	    	 System.out.println(matv); 
    	    	 return String.valueOf(matv);		    	 
    	         }   
    		       conn.close();
    		   }
    		   catch(Exception ex)
    		   {
    			   ex.printStackTrace();	
    		   }
    		   System.out.println("falseeeee");
    	return("error");
    }
    public  void addNewUser(String username,String password,String Email ,String Address,String banknumber ) {
    	 try {
    		 Connection conn = getConnection(DB_URL);
  	        Statement stmt = conn.createStatement();
  	         String sql = "INSERT INTO ThanhVien(tentv,matkhau,diachi,email,matv)" +
  	    "VALUES('" + username +"','" + password + "','"+ Address +"','" + Email +"','"+ banknumber + "');";
  	        		//"Values('1','1','1','1','1')";
		  	   stmt.executeUpdate(sql);
		  	 //s
  		    	 System.out.println("trueeeee"); 
  		         
  		       conn.close();
     		   }
     		   catch(Exception ex)
     		   {
     			   ex.printStackTrace();	
     		   }
     		   System.out.println("falseeeee");
    }
    public  void addNewBankAccount(String banknumber ,String money ) {
   	 try {
   		 Connection conn = getConnection(DB_URL);
 	        Statement stmt = conn.createStatement();
 	         String sql = "INSERT INTO Thongtinnganhang(matv,money)" +
 	    "VALUES('" + banknumber +  "','" + money  + "');";
 	        		//"Values('1','1','1','1','1')";
		  	   stmt.executeUpdate(sql);
		  	 //s
 		    	 System.out.println("trueeeee"); 
 		         
 		       conn.close();
    		   }
    		   catch(Exception ex)
    		   {
    			   ex.printStackTrace();	
    		   }
    		   System.out.println("falseeeee");
   }
	    public  boolean checkStatusDatabase(){
	    	Connection conn = null;
	        try {
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            conn = DriverManager.getConnection(DB_URL);
	            System.out.println("connect successfully!");
	            return true;
	        } catch (Exception ex) {
	            System.out.println("connect failure!");
	            ex.printStackTrace();
	            return false;
	        }
	    }
	}

