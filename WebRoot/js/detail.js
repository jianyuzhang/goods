var app = angular.module('detail', []);
app.controller('detailCtrol', function($scope, $http, $element) {
	$scope.cd = $scope.cd[0]
	$scope.num = 1;
	$scope.oid = 0;
	$scope.isPlay = false;
	$scope.total = $scope.num * $scope.cd.currPrice;
	console.log($scope.isPlay);
	$scope.change = function(event) {
		var validateNum = /^[0-9]*[1-9][0-9]*$/;
		// console.log(validateNum.test($scope.num))
		if (!validateNum.test($scope.num) || $scope.num == ''
				|| $scope.num > $scope.cd.sum) {
			$element.find(event.target).popover({
				content : '数量填写错误',
				placement : 'top',
				trigger : 'manual'
			});
			$element.find(event.target).popover('show');
			$scope.num = 1;
		} else {
			$element.find(event.target).popover('destroy');
		}
	}
	$scope.minus = function() {
		if ($scope.num > 1) {
			$scope.num--;
		}
		$scope.total = $scope.num * $scope.cd.currPrice;
	}
	$scope.plus = function() {
		if ($scope.num < $scope.cd.sum) {
			$scope.num++;
			console.log($scope.num);
		}

		$scope.total = $scope.num * $scope.cd.currPrice;
	}
	/*
	 * 添加购物车
	 */
	$scope.addCart = function() {
		/*
		 * 判断当前是否有用户登陆
		 */
		if ($scope.uid == null) {
			window.location.href = "index.html";
		} else {
			/*
			 * 判断购物车里是否存在当前商品
			 */

			$http.post("/goods/operate/cartItem/sameCart.do", {
				cid : $scope.cid,
				uid : $scope.uid
			}).success(function(flag) {
				$scope.flag = flag[0];
				if ($scope.flag == true) {
					/*
					 * 获取购物车当前用户该商品的数目
					 */
					$http.post("/goods/operate/cartItem/countCart.do", {
						uid : $scope.uid,
						cid : $scope.cid
					}).success(function(count) {
						$scope.count = count[0];
						/*
						 * 加上当前添加的数目运算完插入数据库中
						 */
						$http.post("/goods/operate/cartItem/updateCart.do", {
							uid : $scope.uid,
							cid : $scope.cid,
							num : $scope.num + $scope.count
						}).success(function() {

						});
					});

				} else {
					/*
					 * 购物车不存在当前商品
					 */
					$http.post("/goods/operate/cartItem/addCart.do", {
						uid : $scope.uid,
						cid : $scope.cid,
						num : $scope.num,
						singer : $scope.cd.singer,
						price : $scope.cd.price,
						currPrice : $scope.cd.currPrice,
						cname : $scope.cd.cname,
						image_b : $scope.cd.image_b
					}).success(function() {
						$scope.$parent.cartNum++;
					});
				}
			});
		}
	}

	$scope.addAddress = function() {
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
			$http.post("/goods/operate/cdInOrder/addCdInOrder.do", {
				oid : $scope.oid,
				sum : $scope.num,
				singer : $scope.cd.singer,
				price : $scope.cd.price,
				currPrice : $scope.cd.currPrice,
				cname : $scope.cd.cname,
				image_b : $scope.cd.image_b
			}).success(function() {
				$http.post("/goods/operate/CD/updateCD.do", {
					cid : $scope.cd.cid,
					sum : $scope.cd.sum - $scope.num
				}).success(function() {
					$('#myModal').modal('hide');
				});
			});

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
						$scope.showDetial($scope.cd.cid);
					})
				});
			});
		}
	}
	$('#myModal2').on('hidden.bs.modal', function(e) {
		$scope.myAuto = $element.find("#myaudio")[0];
		console.log($scope.myAuto);
		$scope.myAuto.pause();
		$element.find("#paseIcon").click();
	})
	$scope.playMusic = function (){
		$scope.myAuto = $element.find("#myaudio")[0];
		console.log($scope.myAuto);
		$scope.myAuto.play();
		$('#myModal2').modal('show');
		$scope.isPlay = true;
		console.log($scope.isPlay);
	}
	$scope.stopMusic =function (){
		$scope.myAuto = $element.find("#myaudio")[0];
		console.log($scope.myAuto);
		$scope.myAuto.pause();
		$scope.isPlay = false;
		console.log($scope.isPlay);
	}
	$scope.$watch('carts', change, true);
	function change(to, from) {
		if (to != from) {
			$scope.cartNum = $scope.carts.length;
			//$scope.$digest();
		}
	}
});