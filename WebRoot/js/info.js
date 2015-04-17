var app = angular.module('user', []);
app.controller('infoCtrol', function($scope, $http, $element, $compile,
		$rootScope) {
	$scope.addAddress = function() {
		$scope.showWitch = 1;
	}
	$scope.edit = function() {
		$scope.showWitch = 2;
	}
   $http.post("/goods/operate/address/showSomeAddress.do", {
		uid   : $scope.uid}
   ).success(function(address){
	   $scope.infos = address;
   });
   $scope.change = function(o){
	  
   }
	$scope.addSubmit = function() {
		$http.post("/goods/operate/address/addAddress.do", {
			uid   : $scope.uid,
			uname : $scope.info.uname,
			status : 1,
			address : $scope.info.address,
			phoneNumber : $scope.info.phoneNumber,
		}).success(function() {
			$scope.showUser();
		});
	}
});