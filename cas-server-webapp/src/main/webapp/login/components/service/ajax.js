/**
 * Created by zhangdongming on 16-8-30.
 */
'use strict';
angular.module('app.service.ajax',[]).
config(function ($httpProvider) {
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
})
.factory('AjaxService',function ($http,ConfigService) {
   return{
       checkEmail:function (email) {
           return $http({
               method:'GET',
               url:ConfigService.configObject.casPath+'/emailCheck?email='+email,
               withCredentials:true
           })
       },
       checkPhoneNumber:function (phone) {
           return $http({
               method:'GET',
               url:ConfigService.configObject.casPath+'/phoneCheck?phone='+phone
           })
       },
       sendPin:function (phone) {
           return $http({
               method:'POST',
               url:ConfigService.configObject.casPath+'/getPhoneValidateCode',
               data:$.param({telephoneNumber:phone})
           })
       },
       checkPin:function (phone,pin) {
           return $http({
               method:'POST',
               url:ConfigService.configObject.casPath+'/checkPhoneValidateCode',
               data:$.param({
                   telephoneNumber:phone,
                   checkNum:pin
               })
           })
       },
       loginByPassword:function (data) {
           return $http({
               method:'POST',
               url:ConfigService.configObject.casPath+'/login',
               data:data
           })
       }
   }
});