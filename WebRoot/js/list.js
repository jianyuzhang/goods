var app = angular.module('list', []);

app.controller('listCtrl', function($scope, $http, $element,$compile) {
	$scope.pageList = [];
	$scope.page = 1;
	$scope.totalPage = 1;
	$http.post("/goods/operate/CD/count.do",{mid:$scope.id}).success(function(count) {
			$scope.data=count;
			//console.log($scope.data[0])
		if ($scope.data[0] <= $scope.pageSize) {
			$scope.style = {
				visible : 'hidden'
			}
		}
			if (!isNaN($scope.data[0])) {
				$scope.totalPage = parseInt($scope.data[0] / $scope.pageSize);
				if ($scope.data[0] % $scope.pageSize) {
					$scope.totalPage++;
				}

				for (var i = $scope.page; i <=$scope.totalPage; i++) {
					$scope.pageList.push(i);
				}
			}
	});
	var showPage= function(){
		$http.post("/goods/operate/CD/showListByPage.do", {
		mid : $scope.id,
		pageNo:$scope.page
	}).success(function(allcds) {
		$scope.allcds = allcds;
		var list = $element.find('div[list]');
		list.empty().removeAttr('list').attr('list', '');
		$compile(list)($scope);
	});}
	showPage();
	$scope.forward=function(){
		if($scope.page>1)
		$scope.page--;
		showPage();
	};
	$scope.current = function(p){
		$scope.page = p;
		showPage();
	}
	$scope.back=function(){
		if($scope.page<$scope.totalPage)
		$scope.page++;
		showPage();
	};

	
});
