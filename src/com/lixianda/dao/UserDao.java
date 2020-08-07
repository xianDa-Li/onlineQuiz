package com.lixianda.dao;

import com.lixianda.entity.Users;
import com.lixianda.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
    2020/7/22
 */
public class UserDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    //用户注册
    //JDBC规范中，Connection创建与销毁最浪费时间
    public int add(Users user) {

        int result = 0;
        String sql = "insert into users(userName,password,sex,email)" +
                "value(?,?,?,?)";

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getSex());
            ps.setString(4, user.getEmail());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return result;
    }

    //查询所有用户查询
    public List findAll() {

        List userList = new ArrayList();
        String sql = "select * from users";

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) { // 获取每条数据的全部信息，将信息包装成user类，并保存在一个list集合中
                Integer userId = rs.getInt("userId");
                String userName = rs.getString("userName");
                String password = rs.getString("password");
                String sex = rs.getString("sex");
                String email = rs.getString("email");
                Users user = new Users(userId, userName, password, sex, email);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        return userList;
    }

    //根据用户编号删除用户信息
    public int delete(String usersId) {

        int result = 0;

        String sql = "delete from users where userId=?";

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.valueOf(usersId));
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return result;
    }

    //登录验证
    public int login(String userName,String password){
        String sql="select count(*) from users where userName=? and password=?";
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,userName);
            ps.setString(2,password);
            rs = ps.executeQuery();
            while (rs.next()){
                result = rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        return result;
    }

    //用户修改
    public int update(String usersId,Users user){
        int result = 0;
        String sql = "update from users where userId=?";
        return result;
    }
}
