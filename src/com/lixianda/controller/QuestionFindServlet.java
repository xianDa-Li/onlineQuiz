package com.lixianda.controller;

import com.lixianda.dao.QuestionDao;
import com.lixianda.dao.UserDao;
import com.lixianda.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionFindServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionDao dao = new QuestionDao();

        //1.【调用DAO】将查询命令推送到数据库服务器上，得到所有用户信息【list】
        List<Question> questionlist = dao.findAll();
        //2.通过请求转发，向Tomcat索要info.jsp将处理结果写入到响应体
        request.setAttribute("key",questionlist);
        request.getRequestDispatcher("/question.jsp").forward(request,response);
    }
}
