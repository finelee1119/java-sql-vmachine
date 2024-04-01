package manager.vmManagement;

import db.DBConn;
import dto.ProductDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VmManagementService {
    public int insertVMData(ProductDto dto) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt;
        int result = 0;
        try {
            String query = "insert into product(p_name, p_price, p_stock) values(?, ?, ?)";
            psmt = conn.prepareStatement(query);

            psmt.setString(1, dto.productName());
            psmt.setInt(2, dto.productPrice());
            psmt.setInt(3, dto.productStock());

            result = psmt.executeUpdate();
            psmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public int updateVMData(ProductDto dto) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt;
        int result = 0;

        try {
            String query = "update product set p_name = ?, p_price = ?, p_stock = ? where p_id = ?";
            psmt = conn.prepareStatement(query);

            psmt.setString(1, dto.productName());
            psmt.setInt(2, dto.productPrice());
            psmt.setInt(3, dto.productStock());
            psmt.setInt(4, dto.productId());

            result = psmt.executeUpdate();
            psmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public int deleteVMData(int productId) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt;
        int result = 0;
        try {
            String query = "delete from product where p_id = ?";
            psmt = conn.prepareStatement(query);
            psmt.setInt(1, productId);

            result = psmt.executeUpdate();
            psmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<ProductDto> showAllVMData() {
        List<ProductDto> dtoList = new ArrayList<>();
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt;
        ResultSet rs;

        try {
            String query = "select * from product order by p_id";
            psmt = conn.prepareStatement(query);
            rs = psmt.executeQuery();

            while (rs.next()) {
                dtoList.add(ProductDto.allOf (
                                rs.getInt("p_id"),
                                rs.getString("p_name"),
                                rs.getInt("p_price"),
                                rs.getInt("p_stock")
                        )
                );
            }
            psmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dtoList;
    }

    public ProductDto showOneVMData(int productId) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt;
        ResultSet rs;
        ProductDto productDto = null;

        try {
            String query = "select * from product where p_id = ?";
            psmt = conn.prepareStatement(query);
            psmt.setInt(1, productId);
            rs = psmt.executeQuery();

            while (rs.next()) {
                productDto = ProductDto.allOf(
                        rs.getInt("p_id"),
                        rs.getString("p_name"),
                        rs.getInt("p_price"),
                        rs.getInt("p_stock")
                );
            }
            psmt.close();
            rs.close();
            return productDto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
