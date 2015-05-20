var app = angular.module('user', []);
app.controller('infoCtrol',
		function($scope, $http, $element, $compile, $rootScope) {
			$scope.length = 0;

			$scope.addAddress = function() {
				$scope.showWitch = 1;
				$element.find('button#infoReset').click();
			}
			$scope.editInfo = function(info) {
				$scope.info = info;
				$scope.showWitch = 2;
			}
			$scope.goBack =function(){
				$scope.showWitch = 0;
			}
			$http.post("/goods/operate/address/showSomeAddress.do", {
				uid : $scope.uid
			}).success(function(address) {

				$scope.length = address.length;
				$scope.infos = address;
				$scope.infos.forEach(function(c) {
					c.flag = c.status == 1;
				});
			});
			/*
			 * 验证手机号码
			 */
			$scope.checkPhoneNumber = function(event) {
				console.log(event.target.value)
				var validatephone = /1[3|5|7|8|][0-9]{9}/;
				if (!validatephone.test(event.target.value)||(event.target.value).length>11) {
					$element.find(event.target).popover({
						content : '手机号码格式错误',
						placement : 'top',
						trigger : 'manual'
					});
					$element.find(event.target).popover('show');
				} else {
					$element.find(event.target).popover('destroy');
				}
				$scope.isRight = validatephone.test(event.target.value)&&(event.target.value).length<=11;
				console.log($scope.isRight);
			}

			$scope.change = function(o) {
				$scope.infos.forEach(function(c) {
					c.flag = false;
				});
				o.flag = true;
				$scope.infos.forEach(function(a) {
					a.status = a.flag ? 1 : 0;
					if (a.flag == 1) {
						$scope.$parent.info = a;
					}
				});
				for (var i = 0; i < $scope.infos.length; i++) {
					$http.post("/goods/operate/address/updateAddress.do", {
						address : $scope.infos[i].address,
						uid : $scope.infos[i].uid,
						uname : $scope.infos[i].uname,
						aid : $scope.infos[i].aid,
						phoneNumber : $scope.infos[i].phoneNumber,
						status : $scope.infos[i].status
					}).success(function() {
					});
				}
			}
			$scope.addSubmit = function() {
				if ($scope.addForm.$valid && $scope.isRight) {
					if ($scope.length == 0) {
						$http.post("/goods/operate/address/addAddress.do", {
							uid : $scope.uid,
							uname : $scope.info.uname,
							status : 1,
							address : $scope.info.address,
							phoneNumber : $scope.info.phoneNumber,
						}).success(function() {
							$scope.showUser();
						});
					} else {
						$http.post("/goods/operate/address/addAddress.do", {
							uid : $scope.uid,
							uname : $scope.info.uname,
							status : 0,
							address : $scope.info.address,
							phoneNumber : $scope.info.phoneNumber,
						}).success(function() {
							$scope.showUser();
						});
					}

				}

			}
			$scope.deleteInfo = function(info) {
				$http.post("/goods/operate/address/deleteAddress.do", {
					aid : info.aid
				}).success(function() {
					$scope.showUser();
				});

			}
			$scope.editSubmit = function(info) {
				console.log($scope.isRight);
				var validatephone = /1[3|5|7|8|][0-9]{9}/;
				if (!validatephone.test(info.phoneNumber)||(event.target.value).length>11) {
					$element.find('input#phone').popover({
						content : '手机号码格式错误',
						placement : 'top',
						trigger : 'manual'
					});
					$element.find('input#phone').popover('show');
				} else {
					$element.find('input#phone').popover('destroy');
					if ($scope.editForm.$valid
							&& $scope.isRight) {
						$http.post("/goods/operate/address/updateAddress.do", {
							address : info.address,
							uid : info.uid,
							uname : info.uname,
							aid : info.aid,
							phoneNumber : info.phoneNumber,
							status : info.status
						}).success(function() {
							$scope.showUser();
						});
					}
				}
			}
			$scope.$watch('infos', change, true);

			function change(to, from) {
				if (to != from) {
					console.log($scope.infos)

				}
			}
		});