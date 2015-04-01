var app = angular.module('list', []);

app.controller('listCtrl', function($scope, $http, $element) {
	$scope.showPage = [];
	$scope.page = 1;
	$scope.totalPage = 1;
	$http.post("/goods/operate/CD/count.do",{mid:$scope.id}).success(function(count) {
			$scope.data=count;
			console.log($scope.data[0])
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

				for (var i = $scope.page; i <= $scope.totalPage; i++) {
					$scope.showPage.push(i);
				}
			}
	});
	
	$scope.forward=function(){
		if($scope.page>1)
		$scope.page--;
	};
	$scope.current = function(event){
		$scope.page=event.currentTarget.children[0].innerText;
	}
	$scope.back=function(){
		if($scope.page<=$scope.totalPage)
		$scope.page++;
	};
	/*$http.post("/goods/operate/CD/showListByPage.do", {
		mid : $scope.id,
		pageNo:$scope.page
	}).success(function(allcds) {
		$scope.allcds = allcds;
		
	})*/
});
