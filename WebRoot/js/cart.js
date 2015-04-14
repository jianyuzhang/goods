var app = angular.module('cart', []);
app.controller('cartCtrol', function($scope, $http, $element, $compile) {
	$scope.status = 0;
     console.log($element.find('input.checkOne'))
		
	
	
	$http.post("/goods/operate/cartItem/showSomeCarts.do",{uid:$scope.uid}).success(function(carts){
		$scope.carts = carts;
		//console.log($scope.carts)
	});
	$scope.plus = function(cart){
	//console.log(event.currentTarget.parentNode.childNodes[3].value)
		cart.quantity++;
		$http.post("/goods/operate/cartItem/updateCart.do", {
			uid : $scope.uid,
			cid : cart.cid,
			num : cart.quantity
		}).success(function() {

		});
	}
	$scope.minus = function(cart){
		//console.log(event.currentTarget.parentNode[3].value)
		if(cart.quantity>0){
			cart.quantity--;
			$http.post("/goods/operate/cartItem/updateCart.do", {
				uid : $scope.uid,
				cid : cart.cid,
				num : cart.quantity
			}).success(function() {

			});
			}
	}
	$scope.update = function(cart){
		var validateNum = /^[0-9]*[1-9][0-9]*$/;
		console.log(validateNum.test(cart.quantity))
		if (!validateNum.test(cart.quantity) || cart.quantity == '') {
			cart.quantity = 1;
		}
		$http.post("/goods/operate/cartItem/updateCart.do", {
			uid : $scope.uid,
			cid : cart.cid,
			num : cart.quantity
		}).success(function() {

		});
	}
	$scope.$watch('carts', change, true);
	
	function change(){
		var total = 0;
		for (var i = 0; i < $scope.carts.length; i++) {
	          var p = $scope.carts[i].currPrice;
	          var quantity = $scope.carts[i].quantity;
	         total  += p*quantity;
	        }
	       console.log(total);
	       $scope.total = total;
	}
});