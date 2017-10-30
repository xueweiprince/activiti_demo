(function(angular) {
  'use strict';
angular.module('ngViewExample', ['ui.router'])
  .config(['$stateProvider','$urlRouterProvider',
    function($stateProvider,$urlRouterProvider) {

      $urlRouterProvider.otherwise('/index');

      $stateProvider
          .state('index',{
            url:"/",
            templateUrl: '/test/mainView.html',
            controller: 'BookCtrl',
            controllerAs: 'book'
          })
        .state('test',{
          url:"/login.do",
          templateUrl: '/test/mainView.html',
          controller: 'BookCtrl',
          controllerAs: 'book'
        })
        .state('book', {
          url:"/index",
          templateUrl: 'test/mainView.html',
          controller: 'BookCtrl',
          controllerAs: 'book'
        })

  }])
  .controller('MainCtrl', [
    function MainCtrl() {

  }])
  .controller('BookCtrl', [function BookCtrl($routeParams) {

  }])
  .controller('ChapterCtrl', [ function ChapterCtrl($routeParams) {

  }]);
})(window.angular);