angular.module('app')
    .service('validationService', function ($http) {


        const size = 60000000;

            this.validateSelectedAudioFile = function($scope){
                var file = $scope.vm.audioFile;

                if(file.size > size){
                    $scope.isAudioSizeValid = false;
                } else {
                    $scope.isAudioSizeValid = true;
                }

                if(file.name.indexOf('.mp3') > -1){
                    $scope.audioFormatValid = true;
                } else {
                    $scope.audioFormatValid = false;
                }

                if($scope.isAudioSizeValid && $scope.audioFormatValid){
                    return true;
                }

                return false;

            }

    		this.validateFile = function($scope){
    			var file = $scope.vm.file;


                if(file.size > size){
                    $scope.vm.isSizeValidate = false;
                } else {
                    $scope.vm.isSizeValidate = true;
                }

    			if(file.name.indexOf('.png') > -1 || file.name.indexOf('.jpg') > -1 || file.name.indexOf('.bmp') > -1 || file.name.indexOf('.gif') > -1){
    				$scope.vm.acceptableFormat = true;
    			} else {
    				$scope.vm.acceptableFormat = false;
    			}

                if($scope.vm.isSizeValidate && $scope.vm.acceptableFormat){
                    $scope.vm.parameters.new_width = $scope.vm.parameters.width;
                    $scope.vm.parameters.new_height = $scope.vm.parameters.height;
                    return true;
                } else {
                    return false;
                }
    			
    		};




    	 });