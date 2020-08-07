package com.lixianda.controller;

import com.lixianda.dao.QuestionDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class QuestionDeleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionId;
        QuestionDao dao = new QuestionDao();
        int result = 0;
        PrintWriter out = null;
        //1.【调用请求对象】读取【请求头】参数(用户编号)
        questionId = request.getParameter("userId");
        //2.【调用DAO】将用户编号填充到delete命令并发送到数据库服务器
        result = dao.delete(questionId);
        //3.【调用响应对象】将处理结果以二进制写入到响应库，交给浏览器
        response.setContentType("text/html;charset=utf-8");
        out = response.getWriter();
        if(result == 1){
            out.println("<font style='color:red;font-size:40'>试题信息删除成功！</font>");
        }else {
            out.println("<font style='color:red;font-size:40'>试题信息删除失败！</font>");
        }


    }
}
