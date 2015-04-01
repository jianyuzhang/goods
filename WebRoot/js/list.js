var app = angular.module('list', []);

app.controller('listCtrl', function($scope, $http, $element) {
	$scope.showPage = [];
	$scope.page = 1;
	$scope.totalPage = 1;
	console.log($scope.count)
		/*if (!isNaN(data[0])) {
			$scope.totalPage = parseInt(data[0] / $scope.pageSize);
			if (data[0] % $scope.pageSize) {
				$scope.totalPage++;
			}

			for (var i = $scope.page; i <= $scope.totalPage; i++) {
				$scope.showPage.push(i);
			}
		}*/
	});
