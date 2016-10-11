/**
 * Created by zhangdongming on 16-8-29.
 */
'use strict';

angular.module('app.view', [
        'app.view.login',
        'app.view.token'
    ])
    .config(['$stateProvider', '$locationProvider', '$urlRouterProvider', function ($stateProvider, $locationProvider, $urlRouterProvider) {
        // Set the following to true to enable the HTML5 Mode
        // You may have to set <base> tag in index and a routing configuration in your server
        $locationProvider.html5Mode(false);

        // Defaults to dashboard
        $urlRouterProvider.otherwise('login/username');
    }]);