var app = angular.module('app', ['ngRoute', 'ngMaterial', 'ngFileUpload', 'ngCookies', 'ngAudio'])
    .constant('CONVERT_IMAGE', 'http://localhost:8080/images')
    .constant('GET_CONVERTED_IMAGE', 'http://localhost:8080/images/get_converted_file/')

app.config(function ($routeProvider) {
    $routeProvider
      .when('/', {
          templateUrl: 'views/home.html',
          controller: 'homeController'
      })
      .when('/contact', {
          templateUrl: 'views/contact.html',
          controller: 'contactController'
      })
        .when('/image', {
          templateUrl: 'views/components/image.html',
          controller: 'imageController'
      })
      .otherwise({
          redirectTo: '/'
      });

});

 app.config(function($mdAriaProvider) {
   $mdAriaProvider.disableWarnings();
});

