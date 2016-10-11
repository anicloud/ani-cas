/**
 * Created by zhangdongming on 16-8-29.
 */
'use strict';
angular.module('app', [
        'ui.router',
        'ui.bootstrap',
        // 'app.directive',
        'app.service',
        'app.view'
    ])

    .run(function () {
        FastClick.attach(document.body);
    });