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

<html lang="zh_CN" ng-app="app">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Anicloud</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <link rel="stylesheet" href="login/src/ani_bootstrap.min.css" />
    <link rel="stylesheet" href="login/views/login/login.css">
    <link rel="shortcut icon" type="image/x-icon" href="/login/images/favicon.ico">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div class="" data-ui-view=""></div>
<div ng-controller="tokenctrl">
    <a href="<%=request.getContextPath()%>/register"></a>
    <input type="hidden" name="lt" value="${loginTicket}" id="lt"/>
    <input type="hidden" name="execution" value="${flowExecutionKey}" id="execution"/>
</div>
</body>
<script src="login/src/jquery.min.js"></script>
<script src="login/src/angular.min.js"></script>
<script src="login/src/angular-ui-router.min.js"></script>
<script src="login/src/ui-bootstrap-tpls.min.js"></script>
<script src="login/src/fastclick.js"></script>
<script src="login/src/bootstrap.min.js"></script>
<script src="login/src/angular-animate.min.js"></script>

<script src="login/views/login/token.js"></script>
<script src="login/views/login/login.js"></script>
<script src="login/components/service/service.js"></script>
<script src="login/components/service/dataContainer.js"></script>
<script src="login/components/service/ajax.js"></script>
<script src="login/components/service/config.js"></script>
<script src="login/views/view.js"></script>
<script src="login/components/directive.js"></script>
<script src="login/app.js"></script>
</html>
