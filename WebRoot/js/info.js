var app = angular.module('user', []);
app.controller('infoCtrol', function($scope, $http, $element, $compile,
		$rootScope) {
	
	
	$scope.addAddress = function() {
		$scope.showWitch = 1;
	}
	$scope.editInfo = function(info) {
		$scope.info = info;
		$scope.showWitch = 2;
	}
	$http.post("/goods/operate/address/showSomeAddress.do", {
		uid : $scope.uid
	}).success(function(address) {
		$scope.infos = address;
		var num = 0;
		if ($scope.infos.length > 0) {
			for (var i = 0; i < $scope.infos.length; i++) {
				if ($scope.infos[i].status == 1) {
					num++;
				}
			}
			if (num == 0) {
				$scope.infos[$scope.infos.length-1].flag = true;
			}
		}
		$scope.infos.forEach(function(a) {
			a.status = a.flag?1:0;
		});

	});
	$scope.checkPhoneNumber =function (event){
		console.log(event.target.value)
		var validatephone = /1[3|5|7|8|][0-9]{9}/;
		console.log(validatephone.test(event.target.value));
		if(!validatephone.test(event.target.value)){
			$element.find(event.target).popover({
				content: '手机号码格式错误',
				placement: 'top',
				trigger: 'manual'
			});
			$element.find(event.target).popover('show');
		}else{
			$element.find(event.target).popover('destroy');
		}
		$scope.isRight = validatephone.test(event.target.value);
	}
	
	$scope.change = function(o) {
			$scope.infos.forEach(function(c) {
				c.flag = false;
			});
			o.flag = true;
		$scope.infos.forEach(function(a) {
			a.status = a.flag?1:0;
		});
	}
	$scope.addSubmit = function() {
		 if($scope.addForm.$valid&&$scope.isRight){
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
	$scope.deleteInfo =function (info){
		$http.post("/goods/operate/address/deleteAddress.do", {
			aid : info.aid
		}).success(function() {
			$scope.showUser();
		});
	
	}
	$scope.editSubmit = function(info) {
		console.log(info);
		var validatephone = /1[3|5|7|8|][0-9]{9}/;
		if(!validatephone.test(info.phoneNumber)){
			$element.find('input#phone').popover({
				content: '手机号码格式错误',
				placement: 'top',
				trigger: 'manual'
			});
			$element.find('input#phone').popover('show');
		}else{
			$element.find('input#phone').popover('destroy');
			if($scope.editForm.$valid){
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
			for(var i =0;i < $scope.infos.length ;i++){
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
	}
});