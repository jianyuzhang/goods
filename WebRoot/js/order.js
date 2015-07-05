var app = angular.module('order', []);
app.controller('orderCtrol', function($scope, $http, $element, $compile,
		$rootScope) {
	$scope.currTotal = 0;
	$scope.oid = null;
	$(function() {
		$('[data-toggle="popover"]').popover()
	});
	$http.post('/goods/operate/order/showOrder.do', {
		uid : $scope.uid,
		status : $scope.status
	}).success(function(orders) {
		$scope.orders = orders;
	});
	/*
	 * 切换状态
	 */
	$scope.changeStatus = function(event) {
		console.log(event);
		$scope.status = event.target.children[0].value;
		if ($scope.status == " ") {
			$http.post('/goods/operate/order/showOrder.do', {
				uid : $scope.uid,
			}).success(function(orders) {
				$scope.orders = orders;
			});
		} else {
			$http.post('/goods/operate/order/showOrder.do', {
				uid : $scope.uid,
				status : $scope.status
			}).success(function(orders) {
				$scope.orders = orders;
			});
		}

	}
	/*
	 * 展示订单里的商品list
	 */
	$scope.showGoodList = function(order) {
		$('#myModal').modal('show');
		$http.post('/goods/operate/cdInOrder/showGoods.do', {
			oid : order.oid
		}).success(function(goods) {
			$scope.cdsInOrder = goods;
		});
	}
  /*
   * 取消订单
   */
	$scope.cancelOrder = function(order) {
		$http.post('/goods/operate/order/updateOrder.do', {
			status : 4,
			oid : order.oid
		}).success(function() {

		});
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
	$scope.pay = function(order){
		$('#myModal1').modal('show');
		$scope.currTotal = order.total;
		$scope.oid = order.oid;
		console.log($scope.currTotal);
		console.log(order);
	}
	$scope.payMoney = function(order) {
		console.log($scope.currTotal);
		if ($scope.audience.num < $scope.currTotal) {
			$('#myModal1').modal('hide');
			$('#myModal1').on('hidden.bs.modal', function(e) {
				setTimeout(function() {
					swal("支付失败", "", "error");
				}, 100);
			});
		} else {
			$scope.audience.num -= $scope.currTotal;
			$scope.monster.num +=$scope.currTotal;
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
					$element.find('#status1').click();
					});
				});
			});
		}
	}
	/*
	 * 查询制定订单的物流单号
	 */
	$scope.checkWuliu = function(order) {
		$http.post("/goods/operate/wuliu/showWuliu.do", {
			oid : order.oid
		}).success(function(wuliu) {
			var wuliuhao = wuliu[0].wid;
			swal(wuliuhao, "", "");
		});
	}
	/*
	 * 确认收货
	 */
	$scope.receiveGoods = function (order){
		$http.post('/goods/operate/order/updateOrder.do',{
			status : 3,
			oid :order.oid
		}).success(function (){
			swal("确认收件成功", "", "");
			setTimeout(function() {
				$element.find('#status2').click();
			}, 100);
		});
	}
});
app.filter('checkStatus', function() {
	return function(status) {
		if (0 == status) {
			return "未付款";
		} else if (1 == status) {
			return "未发货";
		} else if (2 == status) {
			return "未收货";
		} else if (3 == status) {
			return "已收货";
		} else if (4 == status) {
			return "已取消";
		}
	}
});