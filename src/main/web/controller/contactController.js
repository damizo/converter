app.controller('contactController', function($scope, emailService, $mdDialog, $cookieStore, $http) {
	$('.intro').show(1000);

	const minutesForSpamProtection = 15;

	$scope.vm = {};
	$scope.vm.ready = false;
	$scope.vm.sending = false;
	$scope.vm.minutes = "";
	$scope.vm.minutesFromCookie = "";

	$scope.$watch($scope.vm.minutes, function( value ) {
  	 console.log($scope.vm.minutes);
	});

	setInterval(
    function(){
        $scope.getCookie();
    }
    , 15000 
	);

	$scope.getCookie = function(){
		var cookieExpired = isCookieValidate($cookieStore);
	}

	var isCookieValidate = function($cookieStore){
		var lastEmailDate = $cookieStore.get('lastEmailDate');
		if(lastEmailDate){
			var thisDate = new Date();
			var lastDate = new Date(lastEmailDate);
			var difference = "";
			var differenceInMinutes = "";

			difference = thisDate.getTime() - lastDate.getTime();
			differenceInMinutes = Math.round(difference / 60000);

			if(differenceInMinutes < minutesForSpamProtection){
				$scope.vm.spam_block = true;
            	$scope.vm.minutes = minutesForSpamProtection - differenceInMinutes;
            	$scope.vm.minutesFromCookie = $scope.vm.minutes;
            	return true;
			} else {
				$scope.vm.spam_block = false;
				return false;
			}
		}
	}

	$scope.check = function(){

		if(checkAllFields($scope)){
			$scope.vm.ready = true;
		} else {
			$scope.vm.ready = false;
		}

	};


	$scope.send = function(){

		if(isCookieValidate($cookieStore)){
			$scope.vm.ready = false;
		} else {
			emailService.sendEmail($scope);
			$scope.showConfirmationDialog();
			clear();
			$cookieStore.put('lastEmailDate', new Date());
		}

	};

	var checkAllFields = function($scope){
		return $scope.vm.name
		&& $scope.vm.email  
		&& $scope.vm.content;
	};


	function clear(){
		$scope.vm.content = '';
		$scope.vm.name = '';
		$scope.vm.email = '';
	}

	$scope.showConfirmationDialog = function() {
    		$mdDialog.show(
      		$mdDialog.alert()
        .clickOutsideToClose(true)
        .title('Confirmation')
        .textContent('Your email has been sent!')
        .ok('CLOSE')
        .openFrom('#left')
        .closeTo(angular.element(document.querySelector('#right'))))
    };
  


});