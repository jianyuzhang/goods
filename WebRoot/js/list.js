var app = angular.module('list', []);

app.controller('listCtrl', function($scope, $http) {
	$scope.showPage = [];
	$scope.page = 1;
	$scope.totalPage = 1;
	$http.post('/goods/operate/CD/count.do').success(function(data) {
		if (!isNaN(data[0])) {
			$scope.totalPage = parseInt(data[0] / $scope.pageSize);
			if (data[0] % $scope.pageSize) {
				$scope.totalPage++;
			}

			for (var i = $scope.page; i <= $scope.totalPage; i++) {
				$scope.showPage.push(i);
			}
		}
	});
});