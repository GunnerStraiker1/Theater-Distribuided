package server;

import server.DBManager;
import model.Province;
import java.sql.*;
import java.util.*;

/**
 * ProvinceRepository: data accessor
 *
 * @author http://lycog.com
 */
public class ProvinceRepository {

    public static int save(Province p) {
        int iRet = -1;
        try {
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "INSERT INTO Province (Id, ShortName, Name) values(?,?,?)";

            try (PreparedStatement pstmt = con.prepareStatement(SQL)) {
                pstmt.setInt(1, p.getId());
                pstmt.setString(2, p.getShortName());
                pstmt.setString(3, p.getName());

                iRet = pstmt.executeUpdate();
            }
        } catch (SQLException se) {
            System.out.println(se);
        }

        return iRet;
    }

    public static int update(Province p) {
        int iRet = -1;
        try {
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "UPDATE Province SET ShortName=?, Name=? WHERE Id=?";
            try (PreparedStatement pstmt = con.prepareStatement(SQL)) {
                pstmt.setString(1, p.getShortName());
                pstmt.setString(2, p.getName());
                pstmt.setInt(3, p.getId());

                iRet = pstmt.executeUpdate();
            }
        } catch (SQLException se) {
            System.out.println(se);
        }

        return iRet;
    }

    public static int delete(Province p) {
        int iRet = -1;
        try {
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "DELETE FROM Province WHERE Id=?";
            try (PreparedStatement pstmt = con.prepareStatement(SQL)) {
                pstmt.setInt(1, p.getId());

                iRet = pstmt.executeUpdate();
            }
        } catch (SQLException se) {
            System.out.println(se);
        }
        return iRet;
    }

    public static void deleteAll() {
        Connection con = DBManager.getInstance().getConnection();
        try {
            con.setAutoCommit(false);
            String SQL = "DELETE FROM Province";
            PreparedStatement pstmt = con.prepareStatement(SQL);

            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException se) {
            try {
                con.rollback();
            } catch (SQLException ise) {
            }
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException fse) {
            }
        }
    }

    public static ArrayList findAll() {
        ArrayList arr = new ArrayList();

        try {
            String QRY = "SELECT * FROM Province ORDER BY Id";
            Connection con = DBManager.getInstance().getConnection();
            try (Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery(QRY);

                while (rs.next()) {
                    Province p = new Province();
                    p.setId(rs.getInt("Id"));
                    p.setShortName(rs.getString("ShortName"));
                    p.setName(rs.getString("Name"));
                    arr.add(p);
                }
            }
        } catch (SQLException se) {
            System.out.println(se);
        }
        return arr;
    }

    public static ArrayList findByName(String name) {
        ArrayList arr = new ArrayList();

        try {
            String QRY = "SELECT * FROM Province WHERE name LIKE(?) ORDER BY id";
            Connection con = DBManager.getInstance().getConnection();
            try (PreparedStatement pstmt = con.prepareStatement(QRY)) {
                pstmt.setString(1, "%" + name + "%");
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    Province p = new Province();
                    p.setId(rs.getInt("Id"));
                    p.setShortName(rs.getString("ShortName"));
                    p.setName(rs.getString("Name"));
                    arr.add(p);
                }
            }
        } catch (SQLException se) {
            System.out.println(se);
        }
        return arr;
    }
}
