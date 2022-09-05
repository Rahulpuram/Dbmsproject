//STEP 1. Import required packages

import java.sql.*;
import java.util.Scanner;



public class JdbcDemo {

//Set JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
//static final String DB_URL = "jdbc:mysql://localhost/companydb";
   static final String DB_URL = "jdbc:mysql://localhost/esportdb?useSSL=false";
//  Database credentials
   static final String USER = "root";// add your user 
   static final String PASS = "Rahul";// add password
   

   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;


// STEP 2. Connecting to the Database
   try{
      //STEP 2a: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      //STEP 2b: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
      //STEP 2c: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
       System.out.println("Welcome to steam database\n\n");
      Scanner sc = new Scanner(System.in);
      menu(stmt,sc);

//STEP 3: Query to database
      String sql;
      sql = "SELECT fname, lname from employee";
      ResultSet rs = stmt.executeQuery(sql);

//STEP 4: Extract data from result set
      while(rs.next()){
         //Retrieve by column name
         String fname  = rs.getString("fname");
         String lname = rs.getString("lname");

         //Display values
         System.out.print("fname: " + fname);
         System.out.println(", lname: " + lname);
      			}

//STEP 5: Clean-up environment
      rs.close();
      stmt.close();
      conn.close();
	}catch(SQLException se){    	 //Handle errors for JDBC
      	se.printStackTrace();
   	}catch(Exception e){        	//Handle errors for Class.forName
      e.printStackTrace();
   }finally{				//finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }					//end finally try
   }					//end try
   System.out.println("End of Code");
}					//end main
				//end class
static void menu(Statement stmt, Scanner sc) {
   System.out.println("Login as\n");
   System.out.println("--------------------------------");
   System.out.println("0.To exit");
   System.out.println("1.Distributor");
   System.out.println("2.Developer");
   System.out.println("3.Customer");
   int a = Integer.parseInt(sc.nextLine());
   while(true){
   switch(a){
      case 0 :
         System.exit(0);break;
      case 1 :
        Distributormenu(stmt,sc);break;
      case 2 :
        Developermenu(stmt,sc);break;
      case 3 :
       Customermenu(stmt,sc);break;
      }
      a = Integer.parseInt(sc.nextLine());
   }
}
static void Distributormenu(Statement stmt,Scanner sc){
   try{
      System.out.println("Authentication :");
   System.out.println("Enter the name ");
   String str = sc.nextLine();
   System.out.println("Enter the password ");
   String str1 = sc.nextLine();
   String query = "select * from Distributor where Name = '" + str + "' and password = '" + str1 + "'";
   ResultSet rs = stmt.executeQuery(query);
   if(rs.next() == false)
               System.out.println("Authentication failed");
   else {
               System.out.println("Authentication successful\n\n");
   System.out.println("Please select any one of the following option");
   System.out.println("0.Exit");
   System.out.println("1.List all games");
   System.out.println("2.List Games by Country");
   System.out.println("3.Insert a new Game record");
   System.out.println("4.Delete a  Game record");
   System.out.println("5.List all the developers");
   System.out.println("6.Insert a new Developer");
   System.out.println("7.Delete a  Developer record");
   System.out.println("8.Insert a new Customer");
   System.out.println("9.Update  Customer record");
   System.out.println("10.Delete a  Customer record");
   System.out.println("11.List of customers");
   int a = Integer.parseInt(sc.nextLine());
   while(true){
      switch(a){
         case 0 :
             System.exit(0);break;
         case 1 :
           Gamequery(stmt,1,sc,0);
             break;
         case 2:
          Gamequery(stmt,2,sc,0); break;
         case 3 :
          Gamequery(stmt,3,sc,0);break;
         case 4 :
          Gamequery(stmt,4,sc,0);  break;
         case 5 :
          Developerquery(stmt,5,sc,0); break;
         case 6 :
          Developerquery(stmt,6,sc,0);break;
         case 7 :
          Developerquery(stmt,7,sc,0);break;
         case 8 :
          Customerquery(stmt,8,sc,0);break;
         case 9 :{
          
          System.out.println("Enter the customerid :");
          int v=Integer.parseInt(sc.nextLine());
          Customerquery(stmt,9,sc,v);
          break;
         }
         case 10 :
          Customerquery(stmt,10,sc,0);break;  
          case 11 :
           Customerquery(stmt,11,sc,0);break; 
         }

       a = Integer.parseInt(sc.nextLine());
     }
   }
   }
   catch(SQLException se){    	 //Handle errors for JDBC
      	se.printStackTrace();
   	}catch(Exception e){        	//Handle errors for Class.forName
      e.printStackTrace();
   }

}
static void Developermenu(Statement stmt,Scanner sc){
   try{
      String query = "";
   System.out.println("Enter the developerid to login: ");
   int a = Integer.parseInt(sc.nextLine()),p=0;
   query = "select Name from Developer where Developerid ="+String.valueOf(a);
   ResultSet rs = stmt.executeQuery(query);
   if(rs.next())
   p=1;
   if(p==1){
   System.out.println("Please select any one of the following option");
   System.out.println("0.Exit");
   System.out.println("1.If u want update Developer details ");
   System.out.println("2.If u want update Game details ");
   int cou = Integer.parseInt(sc.nextLine());
   while(true){
   switch(cou){
      case 0 : System.exit(0); break;
       case 1 :
        Developerquery(stmt,8,sc,a);break;
      case 2 :
       Gamequery(stmt,5,sc,a);break;
    }
    System.out.println("Please select any one of the following option");
   System.out.println("0.Exit");
   System.out.println("1.If u want update Developer details ");
   System.out.println("2.If u want update Game details ");
    cou = Integer.parseInt(sc.nextLine());
   }
   }
   else{
      System.out.println("Authentication failed");
   }

   }
   catch(SQLException se){    	 //Handle errors for JDBC
      	se.printStackTrace();
   	}catch(Exception e){        	//Handle errors for Class.forName
      e.printStackTrace();
   }
   
}


static void Customermenu(Statement stmt,Scanner sc){
    try{
      String query = "";
   System.out.println("Enter the Customerid: ");
   int a = Integer.parseInt(sc.nextLine()),p=0;
   query = "select Name from Customer where Customerid ="+String.valueOf(a);
   ResultSet rs = stmt.executeQuery(query);
   if(rs.next())
   p=1;
   if(p==1){
      while(true){
   System.out.println("Please select any one of the following option");
   System.out.println("0.Exit");
   System.out.println("1.If u want update Customer details ");
   int cou = Integer.parseInt(sc.nextLine());
   if(cou==0)
   System.exit(0);
   Customerquery(stmt,9,sc,a);
      }
     }
   }
   catch(SQLException se){    	 //Handle errors for JDBC
      	se.printStackTrace();
   	}catch(Exception e){        	//Handle errors for Class.forName
      e.printStackTrace();
   }

}


static void Gamequery(Statement stmt,int a,Scanner sc,int k){
   try{
      String query="" ;
   if(a==1){
      System.out.println("List of games");
      query = "select * from Game";
   }
   if(a==2){
      System.out.println("Select the country");
      String c = sc.nextLine();
      String str=c.replace("\"","");
      str="'"+c+"'";
      query = "select * from Game where developerid in (select Developerid from Developer where Country="+str+")";
   }
   if(a==3){
      query = "insert into Game (Name,Genre,Releasedate,developerid) values(";
      System.out.println("Enter Game name : ");
      String name = sc.nextLine();name.replace("\"","");
      name =  "'"+name+"'";
      query = query + name+",";
      System.out.println("Enter Genre : ");
      String genre = sc.nextLine();genre.replace("\"","");
      genre =  "'"+genre+"'";
      query = query + genre+",";
      System.out.println("Enter Releasedate : ");
      int date =  Integer.parseInt(sc.nextLine());
      query = query+"'"+String.valueOf(date)+"'"+",";
      System.out.println("Enter Developerid : ");
      int id = Integer.parseInt(sc.nextLine());
      query = query+"'"+String.valueOf(id)+"'"+");";
      int rs = stmt.executeUpdate(query);
      if(rs==1){
         System.out.println("New game inserted successfully");

      }
      else{
         System.out.println("Value already exists");
      }
      
   }
   else if(a==4){
      int id =0;
      
      System.out.println("Enter the Gameid u want to delete : ");
       id =  Integer.parseInt(sc.nextLine());
      query = "delete from Game where Gameid="+id;
       int rs = stmt.executeUpdate(query);
      if(rs==1){
         System.out.println("Game deleted successfully");

      }
      else{
         System.out.println("Error");
      }
   }
   else if(a==5){
      
       System.out.println("Enter the Game id u want to update : ");
      int cou = Integer.parseInt(sc.nextLine());
      query = "select * from Game where Gameid="+cou+" and developerid="+k;
      ResultSet rs = stmt.executeQuery(query);int q = 0;
      if(rs.next())
      q++;
      if(q>=1){
      System.out.println("select one of the following option: ");
      System.out.println("0.No more update ");
      System.out.println("1.If u want to update name : ");
      int b = Integer.parseInt(sc.nextLine());
      query = "update Game set ";
      int p=0;
      while(b!=0){
         p++;
         if(b==1){
            System.out.println("Enter the Game name ");
            String s = sc.nextLine();
             String str=s.replace("\"","");
              str="'"+str+"'";
            query=query+"Name="+str;

         }
          b = Integer.parseInt(sc.nextLine());

      }
      if(p>0){
      String h = String.valueOf(cou);
      query = query+"where Gameid="+h;
      int rs1 = stmt.executeUpdate(query);
      if(rs1==1){
         System.out.println("Game updated successfully");

      } 
      else{
         System.out.println("error");
       }
      }
      }
      else 
      System.out.println("Acess denied");
   }
      else{
   ResultSet rs = stmt.executeQuery(query);
    while(rs.next()){
      System.out.print("Gameid : "+rs.getString("Gameid"));
      System.out.print(",Name : "+rs.getString("Name"));
      System.out.print(",Genre : "+rs.getString("Genre"));
      System.out.print(",Releasedate : "+rs.getString("Releasedate"));
      System.out.println(",developerid : "+rs.getString("developerid"));
         }
      }
   }
   catch(SQLException se){    	 //Handle errors for JDBC
      	se.printStackTrace();
   	}catch(Exception e){        	//Handle errors for Class.forName
      e.printStackTrace();
   }
   
}



static void Developerquery(Statement stmt,int a,Scanner sc,int k){
   try{
       String query="" ;
   if(a==5){
      System.out.println("List of Developers");
      query = "select * from Developer";
   }
   if(a==6){
      query = "insert into Developer (Name,Country) values(";
      System.out.println("Enter Developer name : ");
      String name = sc.nextLine();name.replace("\"","");
      name =  "'"+name+"'";
      System.out.println("Enter Country : ");
      String country = sc.nextLine();country.replace("\"","");
      country =  "'"+country+"'";
      query = query+name+","+country+")";
      int rs = stmt.executeUpdate(query);
      if(rs==1){
         System.out.println("New Developer inserted successfully");
      }
      else{
         System.out.println("Value already exists");
      }
      
   }
   else if(a==7){
      System.out.println("Enter the Developerid u want to delete");
      int cou = Integer.parseInt(sc.nextLine());
      query = "delete from Developer where Developerid="+String.valueOf(cou);
       int rs = stmt.executeUpdate(query);
       
      if(rs==1){
         System.out.println("Developer deleted successfully");

      }
      else{
         System.out.println("Error");
      }


   }
   else if(a==8){
      System.out.println("select one of the following option: ");
      System.out.println("0.No more update ");
      System.out.println("1.If u want to update name : ");
      System.out.println("2.If u want to update country : ");
      int b = Integer.parseInt(sc.nextLine());
      query = "update Developer set ";
      int p=0;
      while(b!=0){
         p++;
         if(b==1){
            System.out.println("Enter the Developer name ");
            String s = sc.nextLine();
             String str=s.replace("\"","");
              str="'"+str+"'";
            query=query+"Name="+str;

         }
         if(b==2){
            System.out.println("Enter the country name : ");
             String s = sc.nextLine();
             String str=s.replace("\"","");
              str="'"+str+"'";
              if(p>0){
                 query = query+",";
              }
              query = query+"Country="+str;
         }
          b = Integer.parseInt(sc.nextLine());

      }
       String h = String.valueOf(k);
      query = query+"where Developerid="+h;
      int rs = stmt.executeUpdate(query);
      if(rs==1){
         System.out.println("Developer updated successfully");

      } 
      else{
         System.out.println("Value already exists");
      }

   }
   else{
   ResultSet rs = stmt.executeQuery(query);
    while(rs.next()){
      System.out.print("Developerid : "+rs.getString("Developerid"));
      System.out.print(",Name : "+rs.getString("Name"));
      System.out.println(",Country : "+rs.getString("Country"));
     }
   }
   }catch(SQLException se){    	 //Handle errors for JDBC
      	se.printStackTrace();
   	}catch(Exception e){        	//Handle errors for Class.forName
      e.printStackTrace();
   }
 }


 static void Customerquery(Statement stmt,int a,Scanner sc,int l){
    try{
       String query = "";
       if(a==11){
          query = "select * from Customer";

       }
       if(a==8){
           query = "insert into Customer (Name,Country,gameid) values(";
          System.out.println("Enter Customer name : ");
      String name = sc.nextLine();name.replace("\"","");
      name =  "'"+name+"'";query = query + name+",";
      System.out.println("Enter Country : ");
      String country = sc.nextLine();country.replace("\"","");
      country =  "'"+country+"'";query = query +country+",";
      System.out.println("Enter gameid : ");
      int gameid = Integer.parseInt(sc.nextLine());
      query = query + String.valueOf(gameid)+")";
      int rs = stmt.executeUpdate(query);
      if(rs==1){
         System.out.println("New Customer inserted successfully");

      }
      else{
         System.out.println("Value already exists");
      }
   }
   else if(a==9){
     
      System.out.println("select one of the following option: ");
      System.out.println("0.No more update ");
      System.out.println("1.If u want to update name : ");
      System.out.println("2.If u want to update country : ");
      int b = Integer.parseInt(sc.nextLine());
      query = "update Customer set ";
      int p=0;
      while(b!=0){
         p++;
         if(b==1){
            System.out.println("Enter the customer name ");
            String s = sc.nextLine();
             String str=s.replace("\"","");
              str="'"+str+"'";
            query=query+"Name="+str;

         }
         if(b==2){
            System.out.println("Enter the country name : ");
             String s = sc.nextLine();
             String str=s.replace("\"","");
              str="'"+str+"'";
              if(p>0){
                 query = query+",";
              }
              query = query+"Country="+str;
         }
          b = Integer.parseInt(sc.nextLine());

      }
      if(p>0){
      String h = String.valueOf(l);
      query = query+"where Customerid="+l;
      int rs = stmt.executeUpdate(query);
      if(rs==1){
         System.out.println(" Customer updated successfully");

      } 
      else{
         System.out.println("Value already exists");
      }
      }
      

   }
   else if(a==10){
      System.out.println("Enter the customer id u want to delete : ");
      int cou = Integer.parseInt(sc.nextLine());
      query = "delete from Customer where Customerid="+String.valueOf(cou);
       int rs = stmt.executeUpdate(query);
      if(rs==1){
         System.out.println("Customer deleted successfully");

      }
      else{
         System.out.println("Error");
      }

   }
   else{
   ResultSet rs = stmt.executeQuery(query);
    while(rs.next()){
      System.out.print("Customerid : "+rs.getString("Customerid"));
      System.out.print(",Name : "+rs.getString("Name"));
      System.out.print(",Country : "+rs.getString("Country"));
       System.out.println(",Gameid : "+rs.getString("gameid"));
     }
   }
   
   }catch(SQLException se){    	 //Handle errors for JDBC
      	se.printStackTrace();
   	}catch(Exception e){        	//Handle errors for Class.forName
      e.printStackTrace();
         }
    }
 }



//Note : By default autocommit is on. you can set to false using con.setAutoCommit(false)
