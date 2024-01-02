package sample;

import java.sql.Connection;
import java.sql.DriverManager;



    public class dataBase {
        private static final String DB_URL = "jdbc:mysql://localhost/quanlithietbi";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "12345";

       static Connection connectDB(){

            try{
                Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                System.out.println("ket noi thanh cong");
                return  conn;
            } catch (Exception e){e.printStackTrace();}
            return  null;

        }

    }


