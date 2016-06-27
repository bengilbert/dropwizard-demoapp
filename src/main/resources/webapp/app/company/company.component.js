angular.
  module('company').
  component('company', {
    templateUrl: 'company/company.template.html',
    controller: function CompanyController($http) {
      var self = this;
      self.orderProp = 'companyName';

      $http.get('/api/companies/GOOG').then(function(response) {
        self.companies = response.data;
      });
    }
  });