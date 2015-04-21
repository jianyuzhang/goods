var app = angular.module('cart', []);
app.controller('cartCtrol', function($scope, $http, $element, $compile, $rootScope) {
	$scope.allCheck = false;
    $scope.change =function(c){
    	if (c.status) {
			var count = 0;
			$scope.carts.forEach(function(o) {
				if (o.status == c.status) {
					count++;
				}
			});
			if (count == $scope.carts.length) {
				$scope.allCheck = c.status;
			}
		} else {
			$scope.allCheck = c.status;
		}
    }
		
	$scope.allChange = function(){
		$scope.carts.forEach(function(c){
			c.status = $scope.allCheck;
		});
	}
	
	$http.post("/goods/operate/cartItem/showSomeCarts.do",{uid:$scope.uid}).success(function(carts){
		$scope.carts = carts;
		$scope.isShow = $scope.carts.length > 0;
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
			if(cart.quantity==0){
				swal({
					  title: "确定操作吗？",
					  text: "确认删除该商品吗!",
					  type: "warning",
					  showCancelButton: true,
					  confirmButtonColor: "#DD6B55",
					  confirmButtonText: "Yes, delete it!"
					},
					function(){
						$http.post("/goods/operate/cartItem/deleteSelectedCarts.do",{id:cart.cartItemId}).success(function(){
							setTimeout(function() {
								$scope.showCart();
							}, 100);
						});
					});
				cart.quantity = 1;
			}
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
	
	function change(to, from) {
		if (to != from) {
			var total = 0;
			for (var i = 0; i < $scope.carts.length; i++) {
				var p = $scope.carts[i].currPrice;
				var quantity = $scope.carts[i].quantity;
				total += p * quantity;
			}
			$scope.total = total;
		}
	}
	
	/*
	 * 删除商品
	 */
	$scope.deleteOne = function(cart){
		var cartId =cart.cartItemId;
		$http.post("/goods/operate/cartItem/deleteSelectedCarts.do",{id:cartId}).success(function(){
			setTimeout(function() {
				$scope.showCart();
			}, 100);
		});
	}
	
	$scope.deleteSelected = function(){
		var cartIds = [];
		var num = 0;
		$scope.carts.forEach(function(o) {
			 if(o.status == true) {
				 num++;
				 cartIds.push(o.cartItemId)
			}
			 if(num==0){
				 swal("请选择商品", "", "error");
			 }else{
				 for(var i =0;i<num;i++){
					 $http.post("/goods/operate/cartItem/deleteSelectedCarts.do",{id:cartIds[i]}).success(function(){
							setTimeout(function() {
								$scope.showCart();
							}, 100);
						});
				 }
			 }
		});
		//cartIds = JSON.stringify(cartIds);
	}
});