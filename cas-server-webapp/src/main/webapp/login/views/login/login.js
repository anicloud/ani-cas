/**
 * Created by zhangdongming on 16-8-29.
 */
'use strict';
angular.module('app.view.login', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('login', {
                abstract: true,
                url: '/login',
                templateUrl: 'login/views/login/login.html',
                controller: 'loginCtrl',
                // resolve:{
                //     initConfig:function(ConfigService){
                //         return ConfigService.initConfig();
                //     }
                // }
            })
            .state('login.username', {
                url: '/username',
                templateUrl: 'login/views/login/username.html',
                controller: 'usernameCtrl'
            })
            .state('login.password', {
                url: '/password',
                templateUrl:'login/views/login/password.html',
                controller: 'passwordCtrl'
            })
    }]).controller('loginCtrl', function ($scope,$location,DataContainer) {
    var url=$location.url();
    var index=url.indexOf('?');
    if(index>-1){
        DataContainer.searchPara=url.slice(index);
    }else{
        DataContainer.searchPara='';
    }
    // ConfigService.configObject.earthPath=$.i18n.prop('earth_service');
    // ConfigService.configObject.casPath=$.i18n.prop('cas_service');
    // ConfigService.configObject.staticPath='';
    //console.log(ConfigService.configObject);
    console.log(DataContainer.searchPara);
}).controller('usernameCtrl', function ($scope, AjaxService, DataContainer, $state,$timeout,ConfigService) {
    $scope.ConfigService=ConfigService;
    $scope.Data = DataContainer;
    function init() {
        $scope.usernameError = false;
        $scope.usernamePattern = false;
    }
    $scope.usernameCheck = function () {
        init();
        function callback(res) {
            console.log(res);
            var data = res.data;
            if (!data) {
                $scope.Data.loginMethod=$scope.emailPass?'email':'phoneNumber';
                $state.go('login.password')
            }
            else {
                $scope.usernameError = true;
            }
        }
        //remove for test
       $scope.emailPass = $scope.Data.username.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/);
       $scope.phoneNumberPass = $scope.Data.username.match(/^1(3[0-9]|4[57]|5[0-35-9]|7[01678]|8[0-9])\d{8}$/);
       if ($scope.emailPass) AjaxService.checkEmail($scope.Data.username).then(callback);
       else if ($scope.phoneNumberPass) {
           AjaxService.checkPhoneNumber($scope.Data.username).then(callback);
       }
       else{$scope.usernamePattern=true;}
    }
}).controller('passwordCtrl', function ($scope,DataContainer,$state,AjaxService,$interval,ConfigService) {
    $scope.ConfigService=ConfigService;
    $scope.Data = DataContainer;
    if($scope.Data.username==='') $state.go('login.username');
    $scope.sendBtn="点击发送验证码";
    $scope.back=function () {
        $state.go('login.username');
    };
    
    $scope.getCheckNumber=function () {
        $scope.checkBtnDisAbled=true;
        AjaxService.sendPin($scope.Data.username).then(function (res) {
            if(res.data){
                var count=5;
                var timer=$interval(function () {
                    $scope.sendBtn="重新发送("+count--+')';
                },1000,5);
                timer.then(function () {
                    $scope.sendBtn='点击发送验证码';
                    $scope.checkBtnDisAbled=false;
                })
            }
        });
    };
    $scope.login=function ($location) {
        $scope.loginError=false;
        //remove for test
        if($scope.Data.loginMethod==='email'){
            $('#password').val($scope.password);
            // var dataList={
            //     username:$scope.Data.username,
            //     password:$scope.password,
            //     lt:$scope.Data.lt,
            //     execution:$scope.Data.execution,
            //     _eventId:'submit'
            // }
            // var data=$.param(dataList);
            // AjaxService.loginByPassword(data).then(callback);
            $('#realForm').submit();
        }else if($scope.Data.loginMethod==='phoneNumber') {
            $('#password').val('anicloud');
            AjaxService.checkPin($scope.Data.username,$scope.pin).then(setFlagPin);
        }
        //add for test
        //callback({data:true});
        function callback(res) {
            if((res.data).indexOf('Log In Successful')>-1){
                window.location.reload();
            }else{
                $scope.loginError=true;
            }
        }
        function setFlagPin(res) {
            if(res.data==true){
                $scope.pinFlag=true;
                $scope.passPnone=DataContainer.username;
                // dataList={
                //     username:$scope.Data.username,
                //     password:'anicloud',
                //     lt:$scope.Data.lt,
                //     execution:$scope.Data.execution,
                //     _eventId:'submit'
                // };
                // var data=$.param(dataList);
                // AjaxService.loginByPassword(data).then(callback);
                $('#realForm').submit();
            }else{
                $scope.pinFlag=false;
                $scope.loginError=true;
            }
        }
    }
});