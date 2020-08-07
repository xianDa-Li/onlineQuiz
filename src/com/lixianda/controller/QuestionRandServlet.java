package com.lixianda.controller;

import com.lixianda.dao.QuestionDao;
import com.lixianda.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class QuestionRandServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        QuestionDao dao = new QuestionDao();
        List questionList = null;
        HttpSession session = request.getSession(false);
        //1.调用Dao对象随机从question表拿出4道题目
        questionList = dao.findRand();
        //2.将4道题目添加到request作为共享数据
        session.setAttribute("key",questionList);
        //3.请求转发，申请调exam.jsp将4道题目写入到响应体
        request.getRequestDispatcher("/exam.jsp").forward(request,response);
    }
}
