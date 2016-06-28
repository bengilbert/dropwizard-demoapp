'use strict';

var companyApp = angular.module('companyApp', ['companyDirective']);

angular.module('companyDirective', [])
.directive('company', ['$http', function($http) {
      return {
         restrict: 'E',
         templateUrl: 'company.html',
         link: function postLink($scope, element, attrs) {
               $http.get('/api/companies/GOOG').then(function(response) {
                 $scope.company = response.data;
               });
         }
      };

}])

