package by.it.akhmelev.JD03_02;

import by.it.Xampp;
import by.it.akhmelev.JD03_01.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Ex_03_Select {
    public static void main(String[ ] args) {
            try (Connection connection= Xampp.getConnection();
                 Statement statement=connection.createStatement();) {
                //получаем пользователей
                ResultSet resultSet=statement.executeQuery("select * from users;");
                while (resultSet.next()){
                    String out=resultSet.getString("Email")+", "+resultSet.getString("Login");
                    System.out.println(out);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
    }
}
