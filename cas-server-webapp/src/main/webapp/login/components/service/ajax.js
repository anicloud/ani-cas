/**
 * Created by zhangdongming on 16-8-30.
 */
'use strict';
angular.module('app.service.ajax',[]).
config(function ($httpProvider) {
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
})
.factory('AjaxService',function ($http) {
   return{
       checkEmail:function (email) {
           return $http({
               method:'GET',
               url:'https://s0.drtt.bj.anicel.cn:8443/earth-web/service/checkEmail?email='+email,
               withCredentials:true
           })
       },
       checkPhoneNumber:function (phone) {
           return $http({
               method:'GET',
               url:'https://s0.drtt.bj.anicel.cn:8443/earth-web/service/checkPhone?phone='+phone
           })
       },
       sendPin:function (phone) {
           return $http({
               method:'POST',
               url:'https://s0.drtt.bj.anicel.cn:8443/earth-web/service/sendPhoneValidateCode',
               data:$.param({telephoneNumber:phone})
           })
       },
       checkPin:function (phone,pin) {
           return $http({
               method:'POST',
               url:'https://s0.drtt.bj.anicel.cn:8443/earth-web/service/phoneCodeValidate',
               data:$.param({
                   telephoneNumber:phone,
                   checkNum:pin
               })
           })
       },
       loginByPassword:function (data) {
           return $http({
               method:'POST',
               url:'https://s0.drtt.bj.anicel.cn:8443/cas/login',
               data:data
           })
       }
   }
});