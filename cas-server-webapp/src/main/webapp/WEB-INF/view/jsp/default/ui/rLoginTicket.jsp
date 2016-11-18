<%--
  Created by IntelliJ IDEA.
  User: hey
  Date: 16-11-4
  Time: 上午11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/javascript; charset=UTF-8"%>
<%out.print("jsonpcallback({'lt':'");%>${loginTicket}<%out.print
("','execution':'");%>${flowExecutionKey}<%out.print("'})");%>

