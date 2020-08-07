package com.lixianda.dao;

import com.lixianda.entity.Question;
import com.lixianda.entity.Users;
import com.lixianda.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    //添加试题信息
    public int add(Question question){
        String sql = "insert into question (title,optionA,optionB,optionC,optionD,answer)" +
                "values(?,?,?,?,?,?)";
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,question.getTitle());
            ps.setString(2,question.getOptionA());
            ps.setString(3,question.getOptionB());
            ps.setString(4,question.getOptionC());
            ps.setString(5,question.getOptionD());
            ps.setString(6,question.getAnswer());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        return result;
    }

    //查询全部试题信息
    public List findAll() {

        List questionList = new ArrayList();
        String sql = "select * from question";

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) { // 获取每条数据的全部信息，将信息包装成Question类，并保存在一个list集合中
                Integer questionId = rs.getInt("questionId");
                String title = rs.getString("title");
                String optionA = rs.getString("optionA");
                String optionB = rs.getString("optionB");
                String optionC = rs.getString("optionC");
                String optionD = rs.getString("optionD");
                String answer = rs.getString("answer");
                Question question = new Question(questionId,title, optionA, optionB,optionC,optionD,answer);
                questionList.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return questionList;
    }

    //根据试题编号删除试题信息
    public int delete(String questionId){
        int result = 0;

        String sql = "delete from question where questionId=?";

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.valueOf(questionId));
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return result;
    }

    //根据试题编号查找试题信息
    public Question findById(String questionId){
        String sql = "select * from question where questionId = ?";
        Question question = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,Integer.valueOf(questionId));
            rs = ps.executeQuery();
            while (rs.next()) { // 获取每条数据的全部信息，将信息包装成Question类，并保存在一个list集合中
                Integer questionId1 = rs.getInt("questionId");
                String title = rs.getString("title");
                String optionA = rs.getString("optionA");
                String optionB = rs.getString("optionB");
                String optionC = rs.getString("optionC");
                String optionD = rs.getString("optionD");
                String answer = rs.getString("answer");
                question = new Question(questionId1,title, optionA, optionB,optionC,optionD,answer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        return question;
    }

    //根据试题编号更新试题信息
    public int update(Question question){
        String sql = "update question set title=?,optionA=?,optionB=?,optionC=?,optionD=?,answer=? where questionId = ?";
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,question.getTitle());
            ps.setString(2,question.getOptionA());
            ps.setString(3,question.getOptionB());
            ps.setString(4,question.getOptionC());
            ps.setString(5,question.getOptionD());
            ps.setString(6,question.getAnswer());
            ps.setInt(7,question.getQuestionId());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        return result;
    }

    //随机查询4道考试题
    public List findRand(){
        String sql = "select * from question order by rand() limit 0,4";
        List randList = new ArrayList();
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) { // 获取每条数据的全部信息，将信息包装成Question类，并保存在一个list集合中
                Integer questionId = rs.getInt("questionId");
                String title = rs.getString("title");
                String optionA = rs.getString("optionA");
                String optionB = rs.getString("optionB");
                String optionC = rs.getString("optionC");
                String optionD = rs.getString("optionD");
                String answer = rs.getString("answer");
                Question question = new Question(questionId,title, optionA, optionB,optionC,optionD,answer);
                randList.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return randList;
    }
}
