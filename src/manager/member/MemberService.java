package manager.member;

import db.DBConn;
import dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberService {
    public int insertUserData(UserDto dto) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        int result = 0;
        try {
            String query = "insert into user(u_id,u_pw,u_name,u_tel) values(?, ?, ?, ?)";
            psmt = conn.prepareStatement(query);

            psmt.setString(1, dto.userId());
            psmt.setString(2, dto.userPw());
            psmt.setString(3, dto.userName());
            psmt.setString(4, dto.userTel());

            result = psmt.executeUpdate();
            psmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public int updateUserData(UserDto dto){
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt;
        int result = 0;
        try {
            String query = "update user set u_id = ?, u_pw = ?, u_name = ?, u_tel = ? where u_id = ?";
            psmt = conn.prepareStatement(query);

            psmt.setString(1, dto.userId());
            psmt.setString(2, dto.userPw());
            psmt.setString(3, dto.userName());
            psmt.setString(4, dto.userTel());
            psmt.setString(5, dto.userId());

            result = psmt.executeUpdate();
            psmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public int deleteUserData(String userId) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt;
        int result = 0;
        try {
            String query = "delete from user where u_id = ?";
            psmt = conn.prepareStatement(query);
            psmt.setString(1, userId);

            result = psmt.executeUpdate();
            psmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<UserDto> showAllUserData(){
        List<UserDto> dtoList = new ArrayList<>();
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt;
        ResultSet rs;

        try {
            String query = "select * from user order by u_id";
            psmt = conn.prepareStatement(query);
            rs = psmt.executeQuery();

            while (rs.next()) {
                dtoList.add(UserDto.allOfExceptBalance (
                                rs.getString("u_id"),
                                rs.getString("u_pw"),
                                rs.getString("u_name"),
                                rs.getString("u_tel")
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

    public UserDto showOneUserData(String userId) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt;
        ResultSet rs;
        UserDto userDto = null;

        try {
            String query = "select * from user where u_id = ?";
            psmt = conn.prepareStatement(query);
            psmt.setString(1, userId);
            rs = psmt.executeQuery();

            while (rs.next()) {
                userDto = UserDto.allOfExceptBalance(
                        rs.getString("u_id"),
                        rs.getString("u_pw"),
                        rs.getString("u_name"),
                        rs.getString("u_tel")
                );
            }
            psmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userDto;
    }

}
