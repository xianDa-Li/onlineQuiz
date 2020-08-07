package com.lixianda.controller;

import com.lixianda.dao.QuestionDao;
import com.lixianda.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionFindByIdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionId;
        QuestionDao dao = new QuestionDao();
        Question question = null;
        //1.调用请求对象读取请求头中参数信息，得到试题编号
        questionId = request.getParameter("questionId");
        //2.调用Dao推送查询命令得到这个试题编号对应的试题信息
        question = dao.findById(questionId);
        //3.调用question_update.jsp将试题信息写入到响应库
        request.setAttribute("key",question);
        request.getRequestDispatcher("/question_update.jsp").forward(request,response);
    }
}
