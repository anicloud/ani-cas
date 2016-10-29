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

                //casPath:'http://localhost:8082/cas/service',
                //earthPath:'http://s0.drtt.bj.anicel.cn:8443/earth-web/service',
                //submitPath:'http://localhost:8082/cas/login'

                //casPath:'https://s0.drtt.bj.anicel.cn:8443/cas/service',
                //earthPath:'https://s0.drtt.bj.anicel.cn:8443/earth-web/service',
                //submitPath:'https://s0.drtt.bj.anicel.cn:8443/cas/login'

                //casPath:'https://bj-yatsen.anicel.cn:8443/cas/service',
                //earthPath:'https://bj-yatsen.anicel.cn:8443/earth-web/service',
                //submitPath:'https://bj-yatsen.anicel.cn:8443/cas/login'

                casPath:'https://account.anicloud.cn/service',
                earthPath:'https://account.anicloud.cn/earth-web/service',
                submitPath:'https://account.anicloud.cn/login'
            }
        }
    });