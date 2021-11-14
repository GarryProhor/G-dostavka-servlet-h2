package by.prohor.dao;

import by.prohor.model.Order;
import by.prohor.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private String query;
    private Connection cn;
    private PreparedStatement ps;
    private ResultSet rs;

    public OrderDAO(Connection cn){
        this.cn=cn;
    }
    public boolean insertOrder(Order model) {
        boolean result = false;
        try {
            query = "insert into orders (p_id, u_id, o_quantity, o_date) values(?,?,?,?)";
            ps = this.cn.prepareStatement(query);
            ps.setInt(1, model.getId());
            ps.setInt(2, model.getUid());
            ps.setInt(3, model.getQunatity());
            ps.setString(4, model.getDate());
            ps.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<Order> userOrders(int id){
        List<Order> list = new ArrayList<>();
        try {
            query = "select * from orders where u_id=? order by orders.o_id desc";
            ps = this.cn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()){
                Order order = new Order();
                ProductDAO productDAO = new ProductDAO(this.cn);
                int pId = rs.getInt("p_id");

                Product product = productDAO.getSingleProduct(pId);
                order.setOrderId(rs.getInt("o_id"));
                order.setId(pId);
                order.setName(product.getName());
                order.setCategory(product.getCategory());
                order.setPrice(product.getPrice()*rs.getInt("o_quantity"));
                order.setQunatity(rs.getInt("o_quantity"));
                order.setDate(rs.getString("o_date"));
                list.add(order);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public void cancelOrder(int id){
        try {
            query = "delete from orders where o_id=?";
            ps = this.cn.prepareStatement(query);
            ps.setInt(1, id);
            ps.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
