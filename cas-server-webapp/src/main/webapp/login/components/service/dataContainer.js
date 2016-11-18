/**
 * Created by zhangdongming on 16-8-30.
 */
'use strict';
angular.module('app.service.dataContainer',[])
    .factory('DataContainer',function ($http) {
        return{
            username:'',
            loginMethod:'',
            avatar:'login/images/avatar_2x.png',
            searchPara:window.location.href,
            password:'anicloud',
            lt:'',
            execution:''
        }
    });