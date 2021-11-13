package by.prohor.dao;

import by.prohor.model.Cart;
import by.prohor.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private String query;
    private Connection cn;
    private PreparedStatement ps;
    private ResultSet rs;

    public ProductDAO(Connection cn) {
        this.cn = cn;
    }

    public List<Product> getAllProduct(){
        List<Product> products = new ArrayList<>();

        try {
            query = "select * from products";
            ps = this.cn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()){
                Product row = new Product();
                row.setId(rs.getInt("id"));
                row.setName(rs.getString("name"));
                row.setCategory(rs.getString("category"));
                row.setPrice(Double.parseDouble(rs.getString("price")));
                row.setImage(rs.getString("image"));

                products.add(row);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return products;
    }

    public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
        List<Cart> book = new ArrayList<>();
        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    query = "select * from products where id=?";
                    ps = this.cn.prepareStatement(query);
                    ps.setInt(1, item.getId());
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        Cart row = new Cart();
                        row.setId(rs.getInt("id"));
                        row.setName(rs.getString("name"));
                        row.setCategory(rs.getString("category"));
                        row.setPrice(rs.getDouble("price")*item.getQuantity());
                        row.setQuantity(item.getQuantity());
                        book.add(row);
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return book;
    }
}
