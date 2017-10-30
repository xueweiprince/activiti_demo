/**
 * Created by xuewei on 2017/5/9.
 */
(function(angular) {
    'use strict';
    angular.module('ngViewExample', ['ui.router'])
        .config(['$stateProvider', '$urlRouterProvider',
            function($stateProvider, $urlRouterProvider) {

                $urlRouterProvider.otherwise('/');

                $stateProvider
                    .state('index',{
                        url: '/',
                        views: {
                            'header': {
                                templateUrl: '/test/main/header.html'
                            },
                            'content': {
                                templateUrl: '/test/main/content.html'
                            },
                            'sidebar': {
                                templateUrl: '/test/main/sidebar.html'
                            }
                        }
                    })
                    .state('index.content', {
                        url: 'test',
                        views: {
                            'content@': {
                                templateUrl: '/activiti/test.do'
                            }
                        }

                    })
                    .state('index.task', {
                        url: 'task',
                        views: {
                            'content@': {
                                templateUrl: '/test/main/task/mytask.html',
                                controller:"clickDemoCtrl"
                            }
                        }

                    })
                    .state('index.test', {
                        url: 'campaigns',
                        views: {
                            'content@': {
                                templateUrl: '/activiti/test.do',
                                controller: 'CampaignController'
                            }
                        }
                    })
            }])
        .controller('MainCtrl', ['$route', '$routeParams', '$location',
            function MainCtrl($route, $routeParams, $location) {
                this.$route = $route;
                this.$location = $location;
                this.$routeParams = $routeParams;
            }])
        .controller('clickDemoCtrl',function($scope,$http) {

                $scope.hello="hello";
                $scope.definitionList={};

                $http.post("/activiti/process/list.do").then(function(data){
                    debugger;
                    $scope.definitionList=data.data;
                }).catch(function(){
                    $scope.ifShow = true;
                    $scope.sysInfo = '系统错误，请刷新页面';
                });

                $scope.toggle = function () {
                    debugger
                    var item=$("#submenutest");
                    item.toggleClass("active");
                    if (item.hasClass("active")) {
                        item.slideDown("fast");
                    } else {
                        $("#submenutest").slideUp("fast");
                    }
                }

                $scope.test2=function(){
                    alert("----------------");
                }
            }
            )
        .controller('ChapterCtrl', ['$routeParams', function ChapterCtrl($routeParams) {
            this.name = 'ChapterCtrl';
            this.params = $routeParams;
        }]);
})(window.angular);
