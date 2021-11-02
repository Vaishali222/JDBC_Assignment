import javax.xml.transform.Result;
import java.sql.*;
    public class MainClass {
        public static void main(String[] args) {
            try
            {
                final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
                final String DB_URL = "jdbc:mysql://localhost:3306/MyDb";
                final String USER = "root";
                final String PASS = "Vaishali@22";

                Class.forName(JDBC_DRIVER);
                Connection con=DriverManager.getConnection(DB_URL,USER,PASS);

//            Statement stmt=con.createStatement();
//            ResultSet rs=stmt.executeQuery("select * from Product");
//            while(rs.next())
//                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));


                System.out.println("1.Find the total amount to be paid at the time of checkout for a particular cart. As shown in above table. e.g. Query should return a single integer as total amount.");
                Statement stmt1=con.createStatement();
                ResultSet r1=stmt1.executeQuery("SELECT sum(Amount) as Total from Cart");
                while(r1.next())
                    System.out.println("Total = "+r1.getString(1));

                System.out.println("\n");
                System.out.println("2. Find the product name which as sold the most.");
                Statement stmt2=con.createStatement();
                ResultSet r2=stmt2.executeQuery("SELECT Pname from Product WHERE Pid=(SELECT Pid from Cart WHERE Qty=(SELECT Max(Qty) from Cart));");
                while(r2.next())
                    System.out.println("Name with max Qty = "+r2.getString(1));


                System.out.println("\n");
                System.out.println("3.Find all the products which are not yet sold.");
                Statement stmt3=con.createStatement();
                ResultSet r3=stmt3.executeQuery("SELECT Pname from Product WHERE Pid NOT IN(SELECT Pid from Cart)");
                while(r3.next())
                    System.out.println(r3.getString(1));

                System.out.println("\n");
                con.close();
            }

            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }



