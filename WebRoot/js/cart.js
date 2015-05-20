var app = angular.module('cart', []);
app.controller('cartCtrol', function($scope, $http, $element, $compile,
		$rootScope) {
	$scope.allCheck = false;
	$scope.length = 0;
	$scope.oid = 0;
	$scope.carts.forEach(function(c) {
		c.status = $scope.allCheck;
	});
	$scope.change = function(c) {
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

	$scope.allChange = function() {
		$scope.carts.forEach(function(c) {
			c.status = $scope.allCheck;
		});
	}
	$scope.plus = function(cart) {
		// console.log(event.currentTarget.parentNode.childNodes[3].value)
		cart.quantity++;
		$http.post("/goods/operate/cartItem/updateCart.do", {
			uid : $scope.uid,
			cid : cart.cid,
			num : cart.quantity
		}).success(function() {

		});
	}
	/*
	 * 
	 */
	$scope.showSomeCD = function(cart) {
		$scope.showDetial(cart.cid);
	}
	$scope.minus = function(cart) {
		if (cart.quantity > 0) {
			cart.quantity--;
			$http.post("/goods/operate/cartItem/updateCart.do", {
				uid : $scope.uid,
				cid : cart.cid,
				num : cart.quantity
			}).success(function() {

			});
			if (cart.quantity == 0) {
				swal({
					title : "确定操作吗？",
					text : "确认删除该商品吗!",
					type : "warning",
					showCancelButton : true,
					confirmButtonColor : "#DD6B55",
					confirmButtonText : "Yes, delete it!"
				}, function() {
					$http.post(
							"/goods/operate/cartItem/deleteSelectedCarts.do", {
								id : cart.cartItemId
							}).success(function() {
						setTimeout(function() {
							$scope.showCart();
						}, 100);
					});
				});
				cart.quantity = 1;
				$http.post("/goods/operate/cartItem/updateCart.do", {
					uid : $scope.uid,
					cid : cart.cid,
					num : cart.quantity
				}).success(function() {

				});
			}
		}
	}
	$scope.update = function(cart) {
		var validateNum = /^[0-9]*[1-9][0-9]*$/;
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
		var goods = [];
		if (to != from) {
			var total = 0;
			/*
			 * for (var i = 0; i < $scope.carts.length; i++) { var p =
			 * $scope.carts[i].currPrice; var quantity =
			 * $scope.carts[i].quantity; total += p * quantity; }
			 */
			$scope.carts.forEach(function(o) {
				if (o.status == true) {
					goods.push(o);
					total += o.currPrice * o.quantity;
				}
			});
			$scope.goods = goods;
			$scope.length = $scope.goods.length;
			$scope.total = total;
			$scope.cartNum = $scope.carts.length;
		}
	}

	/*
	 * 删除商品
	 */
	$scope.deleteOne = function(cart) {
		var cartId = cart.cartItemId;
		$http.post("/goods/operate/cartItem/deleteSelectedCarts.do", {
			id : cartId
		}).success(function() {
			setTimeout(function() {
				$scope.showCart();
			}, 100);
		});
	}

	$scope.deleteSelected = function() {
		var cartIds = [];
		var num = 0;
		$scope.carts.forEach(function(o) {
			if (o.status == true) {
				num++;
				cartIds.push(o.cartItemId);
			}

		});
		if (num == 0) {
			swal("请选择商品", "", "error");
		} else {
			for (var i = 0; i < num; i++) {
				$http.post("/goods/operate/cartItem/deleteSelectedCarts.do", {
					id : cartIds[i]
				}).success(function() {
					setTimeout(function() {
						$scope.showCart();
					}, 100);
				});
			}
		}
		// cartIds = JSON.stringify(cartIds);
	}
	$scope.managerInfos = function() {

		$('#myModal').modal('hide');
		$('#myModal').on('hidden.bs.modal', function(e) {
			$scope.showUser(1);
		})

	}
	$scope.pay = function() {
		/*
		 * 生成订单
		 */
		$http.post("/goods/operate/order/addOrder.do", {
			uid : $scope.uid,
			total : $scope.total,
			address : $scope.info.address,
			uname : $scope.info.uname,
			phoneNumber : $scope.info.phoneNumber
		}).success(function(order) {
			$scope.oid = order[0].oid;
			console.log($scope.oid);
			for (var i = 0; i < $scope.length; i++) {
				console.log($scope.goods[i].quantity);
				$http.post("/goods/operate/cdInOrder/addCdInOrder.do", {
					oid : $scope.oid,
					sum : $scope.goods[i].quantity,
					singer : $scope.goods[i].singer,
					price : $scope.goods[i].price,
					currPrice : $scope.goods[i].currPrice,
					cname : $scope.goods[i].cname,
					image_b : $scope.goods[i].image_b
				}).success(function() {
				});
				$http.post("/goods/operate/CD/showCDDetial.do", {
					cid : $scope.goods[i].cid
				}).success(function(cd) {
					for (var j = 0; j < $scope.length; j++) {
						$http.post("/goods/operate/CD/updateCD.do", {
							cid : cd[0].cid,
							sum : cd[0].sum - $scope.goods[j].quantity
						}).success(function() {
						});
					}

				});
				$('#myModal').modal('hide');

			}

		});
		$('#myModal1').modal('show');
	}
	$http.post("/goods/operate/money/showAllMoneys.do").success(
			function(moneys) {
				for (var i = 0; i < moneys.length; i++) {
					if (0 == moneys[i].uid) {
						$scope.monster = moneys[i];
					} else if ($scope.uid == moneys[i].uid) {
						$scope.audience = moneys[i];
						$scope.money = moneys[i].num;
					}
				}

			});
	$scope.payMoney = function() {
		if ($scope.audience < $scope.total) {
			$('#myModal1').modal('hide');
			$('#myModal1').on('hidden.bs.modal', function(e) {
				$scope.deleteSelected();
				setTimeout(function() {
					swal("支付失败", "", "error");
				}, 100);
			});
		} else {
			$scope.audience.num -= $scope.total;
			$scope.monster.num += $scope.total;
			console.log($scope.audience.num);
			console.log($scope.monster.num);
			$http.post("/goods/operate/money/updaeMoney.do", {
				mid : $scope.audience.mid,
				uid : $scope.uid,
				num : $scope.audience.num
			}).success(function() {
				$http.post("/goods/operate/money/updaeMoney.do", {
					mid : $scope.monster.mid,
					uid : 0,
					num : $scope.monster.num
				}).success(function() {
					$http.post('/goods/operate/order/updateOrder.do', {
						status : 1,
						oid : $scope.oid
					}).success(function() {

					});
					$('#myModal1').modal('hide');
					$('#myModal1').on('hidden.bs.modal', function(e) {
						$scope.deleteSelected();
					});
				});
			});
		}
	}
	$('#myModal1').on('hidden.bs.modal', function(e) {
		$scope.deleteSelected();
	});
});