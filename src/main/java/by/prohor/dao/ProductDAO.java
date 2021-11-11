package by.prohor.dao;

import by.prohor.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                row.setPrice(rs.getString("price"));
                row.setImage(rs.getString("image"));

                products.add(row);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return products;
    }
}
