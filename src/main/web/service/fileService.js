angular.module('app')
    .service('fileService', function ($http, validationService, CONVERT_IMAGE, GET_CONVERTED_IMAGE) {

        this.validateSelectedFile = function ($scope) {
            validationService.validateFile($scope);
        };

        this.uploadImage = function ($scope) {
            var width = $scope.vm.parameters.new_width * 1;
            var height = $scope.vm.parameters.new_height * 1;

            var data = {
                name: $scope.vm.file.name,
                height: $scope.vm.parameters.height,
                width: $scope.vm.parameters.width,
                extension: $scope.vm.file.name.split(".")[1],
                newExtension: $scope.vm.extension,
                newWidth: width,
                newHeight: height,
                encodedContent: $scope.vm.parameters.encoded_content
            };

            $http.post(CONVERT_IMAGE, data).then(function success(response) {
                if (response.status == 200) {
                    $scope.vm.converting = false;
                    $scope.token = response.data.token;
                    $scope.new_image = GET_CONVERTED_IMAGE + $scope.token;
                }

            }, function error(response) {
                $scope.vm.converting = false;
                window.alert("ERROR SERVER")
            });


        }
        this.convertFile = function ($scope) {

        }


    });