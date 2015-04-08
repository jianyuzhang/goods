var app = angular.module('detail', []);
app.controller('detailCtrol',function($scope,$http){
	$scope.cd=$scope.cd[0]
		console.log($scope.cd[0])
	$scope.num=1;
	
	$scope.minus = function (){
		if($scope.num>1){
		$scope.num--;
	}
	}
	$scope.plus = function (){
		$scope.num++;
	}
});