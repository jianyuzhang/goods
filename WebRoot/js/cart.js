var app = angular.module('cart', []);
app.controller('cartCtrol', function($scope, $http, $element) {
	$scope.cartnum = 1;
	$scope.change = function(event) {
		var validatecartnum = /^[0-9]*[1-9][0-9]*$/;
		if (!validatecartnum.test($scope.cartnum) || $scope.cartnum == '') {
			$element.find(event.target).popover({
				content : '数量填写错误',
				placement : 'top',
				trigger : 'manual'
			});
			$element.find(event.target).popover('show');
			$scope.cartnum = 1;
		}
	}
	$scope.minus = function() {
		if ($scope.cartnum > 1) {
			$scope.cartnum--;
		}
	}
	$scope.plus = function() {
		$scope.cartnum++;
	}
});