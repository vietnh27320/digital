package model;

import context.DBContext;
import entity.Digital;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DigitalModel extends DBContext {

    public Digital getTop1Digital() throws Exception {
        DBContext db = DBContext.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "Select Top 1* from digital order by timepost desc";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital digital = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                return digital;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, connection);
        }
        return null;
    }

    public List<Digital> getTop5Next() throws Exception {
        DBContext db = DBContext.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select top 5 * from digital\n"
                + "	where timePost not in (\n"
                + "	select max(timepost) from digital\n"
                + "	)\n"
                + "	order by timePost desc";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Digital> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Digital(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes")));
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, connection);
        }
    }

    public int count(String txt) throws Exception {
        DBContext db = DBContext.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select count(*) from digital where title like ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, connection);
        }
        return 0;
    }

    public int countAll() throws Exception {
        DBContext db = DBContext.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select count(*) from digital";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, connection);
        }
        return 0;
    }

    public List<Digital> search(String txt, int index) throws Exception {
        DBContext db = DBContext.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from \n"
                + "(\n"
                + "select ROW_NUMBER() over (order by id asc) as r, * \n"
                + "from digital where title like ?\n"
                + ") as x\n"
                + "where r between ?*3 -2 and ?*3";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            ps.setInt(2, index);
            ps.setInt(3, index);
            rs = ps.executeQuery();
            List<Digital> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Digital(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes")));
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, connection);
        }
    }

    public Digital getOne(int id) throws Exception {
        DBContext db = DBContext.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from digital\n"
                + "where id  = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital d = new Digital(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                return d;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, connection);
        }
        return null;
    }

    public List<Digital> getAll() throws Exception {
        DBContext db = DBContext.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from digital order by timePost desc";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Digital> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Digital(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes")));
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, connection);
        }
    }
}
