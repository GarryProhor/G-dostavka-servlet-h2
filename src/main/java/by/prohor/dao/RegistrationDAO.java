package by.prohor.dao;

import by.prohor.connection.DbConnection;
import by.prohor.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationDAO {
    private String result;
    private String query;
    private PreparedStatement preparedStatement;
    private ResultSet rs;


    public String insert(User user){
        result = "data entered successfully";
        query = "insert into USER(name, email, password) values(?,?,?)";
        try {
            Connection con = DbConnection.getConnection();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(user.getId()));
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            result = "data not entered";
        }



        return result;
    }
}
