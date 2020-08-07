package com.lixianda.listener;

import com.lixianda.util.DBUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OneListener implements ServletContextListener {
    // 在Tomcat启动时，预先创建20个Connection,在userDao.add方法执行时
    // 将事先创建好connection交给add方法
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Map map = new HashMap();

        for(int i=1;i<=20;i++){
            try {
                conn = DBUtil.getConnection();
                System.out.println("在Http服务器启动时，创建Connection " + conn);
                map.put(conn,true); // true表示该通道处于空闲，false通道正被使用
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        /*为了在Http服务器运行期间，一直都可以使用20个Connection,将connection保存
          到全局作用域对象
         */
        ServletContext application = sce.getServletContext();
        application.setAttribute("key1",map);
    } // map被销毁
    // 在Http服务器关闭时刻，将全局作用域对象20个Connection销毁
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        Map map = (Map)application.getAttribute("key1");
        Iterator it = map.keySet().iterator();
        while (it.hasNext()){
            Connection conn = (Connection)it.next();
            if(conn != null){
                System.out.println("我 " + conn + "先行一步,20后还是一条好汉");
                DBUtil.close(conn,ps,rs);
            }
        }
    }
}
