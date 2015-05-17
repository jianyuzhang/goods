var app = angular.module('allOrder', []);
app.controller('allOrderCtrol', function($scope, $http, $element, $compile,
		$rootScope) {
	$(function() {
		$('[data-toggle="popover"]').popover()
	});
	$http.post('/goods/operate/order/showOrder.do', {
		status : $scope.status
	}).success(function(orders) {
		$scope.orders = orders;
	});
	/*
	 * 切换状态
	 */
	$scope.changeStatus = function(event) {
		$scope.status = event.target.children[0].value;
		if ($scope.status == " ") {
			$http.post('/goods/operate/order/showOrder.do', {
			}).success(function(orders) {
				$scope.orders = orders;
			});
		} else {
			$http.post('/goods/operate/order/showOrder.do', {
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
			console.log($scope.cdsInOrder);
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