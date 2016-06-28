'use strict';

var companyApp = angular.module('companyApp', ['companyDirective', 'tickerCodeDirective']);

angular.module('companyDirective', [])
.directive('company', ['$http', function($http) {
      return {
         restrict: 'E',
         templateUrl: 'company.html',
         link: function postLink($scope, element, attrs) {
               $http.get('/api/companies/MSFT').then(function(response) {
                 $scope.company = response.data;
               });
         }
      };

}])

angular.module('tickerCodeDirective', [])
 .directive('tickerCode', ['$http', function($http) {
       return {
          restrict: 'E',
          templateUrl: 'tickerCode.html',
          link: function postLink($scope, element, attrs) {
               $http.get('/api/companies').then(function(response) {
                   $scope.tickerCodes = {
                       tickerCode: null,
                       availableOptions: response.data
                   }
               });
         }
       };
   }]
);
