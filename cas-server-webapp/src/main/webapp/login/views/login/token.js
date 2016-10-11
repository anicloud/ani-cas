/**
 * Created by hey on 16-9-8.
 */
'use strict';
angular.module('app.view.token',[]).
    controller('tokenctrl',function ($scope,DataContainer) {
    DataContainer.lt=$('#lt').val();
    DataContainer.execution=$('#execution').val();
    console.log('Datacontainer',DataContainer);
})