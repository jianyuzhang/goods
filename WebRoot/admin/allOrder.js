var app = angular.module('allOrder', []);
app.controller('allOrderCtrol', function($scope, $http, $element, $compile,
		$rootScope) {
	$(function() {
		$('[data-toggle="popover"]').popover()
	});
	$scope.sender = {
		"address" : "北京市中关村",
		"name" : "张先生",
		"city" : "北京"
	};
	console.log($scope.sender);
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
			$http.post('/goods/operate/order/showOrder.do', {}).success(
					function(orders) {
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
	/*
	 * 发货
	 */
	$scope.sendGoods = function(order) {
		$('#myModal1').modal('show');
		$scope.order = order;

	}
	/*
	 * 生成物流单
	 */
	$scope.wuliuAdd = function(order) {
		console.log(order);
		$http.post("/goods/operate/wuliu/addWuliu.do", {
			oid : order.oid,
			fromwhere : $scope.sender.address,
			fromwho : $scope.sender.name,
			city : $scope.sender.city,
			towhere : order.address,
			towho : order.uname,
			phonenumber : order.phoneNumber
		}).success(function() {
			$http.post('/goods/operate/order/updateOrder.do', {
				status : 2,
				oid : order.oid
			}).success(function() {
				$('#myModal1').modal('hide');
				$('#myModal1').on('hidden.bs.modal', function(e) {
					setTimeout(function() {
						swal("提交成功", "", "success");
					}, 100);
				});
			});
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
			return "已发货";
		} else if (3 == status) {
			return "已收货";
		} else if (4 == status) {
			return "已取消";
		}
	}
});