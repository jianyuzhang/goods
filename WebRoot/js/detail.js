var app = angular.module('detail', []);
app.controller('detailCtrol', function($scope, $http, $element) {
	$scope.cd = $scope.cd[0]
	$scope.num = 1;
	$scope.change = function(event) {
		var validateNum = /^[0-9]*[1-9][0-9]*$/;
		console.log(validateNum.test($scope.num))
		if (!validateNum.test($scope.num) || $scope.num == '') {
			$element.find(event.target).popover({
				content : '数量填写错误',
				placement : 'top',
				trigger : 'manual'
			});
			$element.find(event.target).popover('show');
			$scope.num = 1;
		}
	}
	$scope.minus = function() {
		if ($scope.num > 1) {
			$scope.num--;
		}
	}
	$scope.plus = function() {
		$scope.num++;
	}
	/*
	 * 添加购物车
	 */
	$scope.addCart = function() {
		console.log($scope.uid == null)
		console.log($scope.cid)
		/*
		 * 判断购物车里是否存在当前商品
		 */
		$http.post("/goods/operate/cartItem/sameCart.do", {
			cid : $scope.cid,
			uid : $scope.uid
		}).success(function(flag) {
			$scope.flag = flag;
			console.log($scope.flag)
		});
		/*
		 * 判断当前是否有用户登陆
		 */
		if ($scope.uid == null) {
			window.location.href = "index.html";
		} else {
			if (!$scope.flag) {
				$http.post("/goods/operate/cartItem/addCart.do", {
					uid : $scope.uid,
					cid : $scope.cid,
					num : $scope.num
				}).success(function() {

				});
			} else {
				/*
				 * 获取购物车当前用户该商品的数目
				 */
				$http.post("/goods/operate/cartItem/countCart.do", {
					uid : $scope.uid,
					cid : $scope.cid
				}).success(function(count) {
					$scope.count = count;
					/*
					 * 加上当前添加的数目运算完插入数据库中
					 */
					$http.post("/goods/operate/cartItem/updateCart.do", {
						uid : $scope.uid,
						cid : $scope.cid,
						num : $sope.num + $scope.count
					}).success(function() {

					});
				});

			}
		}

	}
});