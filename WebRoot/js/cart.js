var app = angular.module('cart', []);
app.controller('cartCtrol', function($scope, $http, $element) {
	$http.post("/goods/operate/cartItem/showSomeCarts.do",{uid:$scope.uid}).success(function(carts){
		$scope.carts = carts;
		console.log($scope.carts)
	});
});