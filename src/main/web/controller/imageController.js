app.controller('imageController', function ($scope, fileService, Upload, $timeout) {

    init();

    $scope.uploadImage = function (file) {
        $scope.vm.file = file;
        $scope.vm.parameters.height = file.$ngfHeight;
        $scope.vm.parameters.width = file.$ngfWidth;
        $scope.vm.extension = file.type.split('/')[1].toUpperCase();

        Upload.dataUrl(file, true).then(function (url) {
            $scope.vm.parameters.encoded_content = url.replace(/^data:image\/(png|jpeg|jpg|bmp);base64,/, "");
        });

        fileService.validateSelectedFile($scope);
    }

    $scope.startConvert = function () {
        $scope.vm.converting = true;
        $timeout(function () {
            fileService.uploadImage($scope);
        }, 1500);
    }

    $scope.hideDownloadButton = function () {
        $scope.token = '';
    }

    $scope.$watch(
        "vm.increaseBy",
        function change(newValue, oldValue) {
            $scope.vm.increasedValue = '';
            $scope.vm.increasedValue = newValue + '%';

            var onePercentOfHeight = $scope.vm.parameters.height / 100;
            var onePercentOfWidth = $scope.vm.parameters.width / 100;
            var increasedWidth = newValue * onePercentOfWidth;
            var increasedHeight = newValue * onePercentOfHeight;
            var percentToDecrease = oldValue - newValue;
            var decreasedWidth = percentToDecrease * onePercentOfWidth;
            var decreasedHeight = percentToDecrease * onePercentOfHeight;

            if (newValue > oldValue) {
                $scope.vm.parameters.new_width = $scope.vm.parameters.width + increasedWidth;
                $scope.vm.parameters.new_height = $scope.vm.parameters.height + increasedHeight;
            } else {
                $scope.vm.parameters.new_width = $scope.vm.parameters.new_width - decreasedWidth;
                $scope.vm.parameters.new_height = $scope.vm.parameters.new_height - decreasedHeight;
            }
        }
    );

    function roundUp(num, precision) {
        return Math.ceil(num * precision) / precision
    }

    $scope.$watch(
        "vm.decreaseBy",
        function change(newValue, oldValue) {
            $scope.vm.decreasedValue = '';
            $scope.vm.decreasedValue = newValue + '%';

            var onePercentOfHeight = $scope.vm.parameters.height / 100;
            var onePercentOfWidth = $scope.vm.parameters.width / 100;
            var increasedWidth = newValue * onePercentOfWidth;
            var increasedHeight = newValue * onePercentOfHeight;

            var percentToDecrease = oldValue - newValue;
            var decreasedWidth = percentToDecrease * onePercentOfWidth;
            var decreasedHeight = percentToDecrease * onePercentOfHeight;

           increasedWidth = roundUp(increasedWidth, 2);
            increasedHeight = roundUp(increasedHeight, 2);
            decreasedWidth = roundUp(decreasedWidth, 2);
            decreasedHeight = roundUp(decreasedHeight, 2);

            if (newValue > oldValue) {
                $scope.vm.parameters.new_width = $scope.vm.parameters.width - increasedWidth;
                $scope.vm.parameters.new_height = $scope.vm.parameters.height - increasedHeight;
            } else {
                $scope.vm.parameters.new_width = $scope.vm.parameters.new_width + decreasedWidth;
                $scope.vm.parameters.new_height = $scope.vm.parameters.new_height + decreasedHeight;
            }

            if(newValue == 0){
                $scope.vm.parameters.new_width = $scope.vm.parameters.width;
                $scope.vm.parameters.new_height = $scope.vm.parameters.height;
            }
        }
    );


    function init(){
        $scope.initConvert = false;
        $scope.vm = {};
        $scope.vm.converting = "";
        $scope.vm.parameters = {};
        $scope.vm.parameters.height = "";
        $scope.vm.parameters.width = "";
        $scope.vm.parameters.new_width = "";
        $scope.vm.parameters.new_height = "";
        $scope.vm.parameters.new_format = "";
        $scope.vm.parameters.formats = ['PNG', 'JPG', 'BMP'];
        $scope.vm.parameters.encoded_content = "";
        $scope.vm.increaseBy = '0'
        $scope.vm.decreaseBy = '0';
        $('.intro').show(1000);
    }

});
