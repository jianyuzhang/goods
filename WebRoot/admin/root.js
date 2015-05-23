var app = angular.module("root", [ 'allOrder', 'goods' ]);
angularConfig(app);
app.controller('rootCtrol', function($scope, $http, $element, $compile) {

	$scope.showOrder = function() {
		var content = $element.find('div#content');
		content.empty().removeAttr('goods').attr('order', '');
		$compile(content)($scope);
	}
	$scope.showGoods = function() {
		var content = $element.find('div#content');
		content.empty().removeAttr('order').attr('goods', '');
		$compile(content)($scope);
	}
});

app.directive('order', function() {
	return {
		restrict : 'EA',
		templateUrl : 'allOrder.html'
	}
});

app.directive('goods', function() {
	return {
		restrict : 'EA',
		templateUrl : 'goods.html'
	}
});