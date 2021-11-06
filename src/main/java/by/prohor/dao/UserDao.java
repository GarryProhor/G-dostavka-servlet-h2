package by.prohor.dao;

import by.prohor.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    private String query;
    private Connection cn;
    private PreparedStatement ps;
    private ResultSet rs;

    public UserDao(Connection cn) {
        this.cn = cn;
    }
    public User userLogin(String email, String password){
        User user = null;
        try{
            query = "select * from user where email=? and password=?";
            ps = this.cn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));

            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return user;
    }
}
