'use strict';

var companyApp = angular.module('companyApp', ['companyDirective']);

angular.module('companyDirective', [])
 .directive('company', ['$http', function($http) {
       return {
          restrict: 'E',
          templateUrl: 'company.html',
          link: function postLink($scope, element, attrs) {
               $http.get('/api/companies').then(function(response) {
                   $scope.tickerCodes = {
                       tickerCode: null,
                       availableOptions: response.data
                   }
                   $scope.change = function() {
                        $http.get('/api/companies/'+$scope.tickerCodes.tickerCode).then(function(response) {
                            $scope.company = response.data;
                        });
                   }
               });
         }
       };
   }]
);
