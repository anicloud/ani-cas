/**
 * Created by zhangdongming on 16-10-17.
 */
'use strict';
angular.module('app.service.config',[])
    .factory('ConfigService',function () {
        return{
            initConfig:function () {
                console.log($.i18n);
                $.i18n.properties({
                    name:'exciting_config',
                    path:'',
                    mode:'both',
                    callback:function(){
                        console.log($.i18n.prop('cas_service'));
                    }
                });
                return null;
            },
            configObject:{
                casPath:'https://bj-yatsen.anicel.cn:8443/cas',
                earthPath:'https://bj-yatsen.anicel.cn:8443/earth-web/service'
            }
        }
    });