<%@ page import="java.util.List" %>
<%@ page import="com.lixianda.entity.Question" %><%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2020/7/25
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <center>
        <form action="/myWeb/exam">
            <table border="2" >
                <tr>
                    <td>试题编号</td>
                    <td>题目信息</td>
                    <td>A</td>
                    <td>B</td>
                    <td>C</td>
                    <td>D</td>
                    <td>答案</td>
                </tr>
                <%
                    List<Question> questionList = (List)session.getAttribute("key");
                    for(Question q : questionList){
                %>
                    <tr>
                        <td><%=q.getQuestionId()%></td>
                        <td><%=q.getTitle()%></td>
                        <td><%=q.getOptionA()%></td>
                        <td><%=q.getOptionB()%></td>
                        <td><%=q.getOptionC()%></td>
                        <td><%=q.getOptionD()%></td>
                        <td>
                            <input type="radio" name="answer_<%=q.getQuestionId()%>" value="A">A
                            <input type="radio" name="answer_<%=q.getQuestionId()%>" value="B">B
                            <input type="radio" name="answer_<%=q.getQuestionId()%>" value="C">C
                            <input type="radio" name="answer_<%=q.getQuestionId()%>" value="D">D
                        </td>
                    </tr>
                <%
                    }
                %>
                <tr align="center">
                    <td colspan="3"><input type="submit" value="提交试卷"/></td>
                    <td colspan="4"><input type="reset" value="重做"/></td>
                </tr>
            </table>
        </form>
    </center>
</body>
</html>
