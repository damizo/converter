angular.module('app')
    .service('emailService', function ($http) {


    		 this.sendEmail = function($scope){
    			var sent = false;

    			vm = { 
				name: $scope.vm.name,
				email: $scope.vm.email,
				content: $scope.vm.content
				};

				$scope.vm.ready = false;

				 emailjs.send("default_service","template_Q1V8TRJ5",{from_name: vm.name, 
					from_lastname: vm.email,
					message: vm.content
					});

				 clear();
				

    		};


    		function clear(){
    			$('#name_form').val('');
    			$('#email_form').val('');
    			$('#content_form').val('');
    		}


    	 });