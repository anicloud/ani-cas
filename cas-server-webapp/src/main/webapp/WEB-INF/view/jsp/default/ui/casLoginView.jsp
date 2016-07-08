<%--

    Licensed to Apereo under one or more contributor license
    agreements. See the NOTICE file distributed with this work
    for additional information regarding copyright ownership.
    Apereo licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file
    except in compliance with the License.  You may obtain a
    copy of the License at the following location:

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.

--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html lang="zh_CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Anicloud</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <link rel="icon" href="<c:url value="/ani_logo.png" />" type="image/x-icon" />
    <link rel="stylesheet" href="component/font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="css/layout.css">
    <link rel="stylesheet" href="css/login.css">

    <script src="component/jquery/jquery.min.js"></script>
    <script src="js/login.js"></script>

</head>
<body>
<div class="login-wrapper">
    <div class="logo">
        <img src="images/logo.png">
    </div>
    <div class="title">
        从这里开始, 连接世界
    </div>
    <div class="login-panel">
        <form id="loginForm" method="POST" action="/cas/login">
            <div class="row-input">
                <span><i class="fa fa-user" aria-hidden="true"></i></span>
                <span><input type="text" name="username" id="username" placeholder="用户名/邮箱/手机号"></span>
            </div>
            <div class="row-input">
                <span><i class="fa fa-lock" aria-hidden="true"></i></span>
                <span><input type="password" name="password" id="password" placeholder="密码"></span>
            </div>
            <div>
                <input type="submit" class="btn" id="submitBtn" value="登录">
            </div>
            <%--<div class="link">--%>
                <%--<a href="<%=request.getContextPath()%>/register">--%>
                    <%--还没有账号？前往注册--%>
                <%--</a>--%>
            <%--</div>--%>
            <div>
                <input type="hidden" name="lt" value="${loginTicket}" />
                <input type="hidden" name="execution" value="${flowExecutionKey}" />
                <input type="hidden" name="_eventId" value="submit" />
            </div>
        </form>
    </div>
</div>

</body>
</html>
