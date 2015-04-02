var app = angular.module("main", [ 'list' ]);
angularConfig(app);

app.controller('menuCtrol', function($scope, $http) {
	$scope.title = '主页';

	$scope.slideToggle = function($event) {
		$($event.target).parents('li').find("ul").slideToggle();
	};

	$http.post("/goods/operate/menu/showMenus.do").success(function(MenuDTOParents) {
		$scope.MenuDTOParents = MenuDTOParents;
	});

});

app.controller('userCtrol', function($scope, $http) {
	$http.post("/goods/operate/user/findUser.do").success(function(user) {
		$scope.user = user;
	});
	$scope.logout = function() {
		$http.post("/goods/operate/user/logout.do").success(function() {
			window.location.href = "index.html";
		});
	}
});

app.controller('cdCtrol', function($scope, $http, $compile, $element) {
	/*$http.post("/goods/operate/CD/showListByPage.do", {
		mid : $scope.id,
		pageNo:$scope.page
	}).success(function(allcds) {
		$scope.allcds = allcds;
		var list = $element.find('div[list]');
		list.empty().removeAttr('list').attr('list', '');
		$compile(list)($scope);
	});*/
});
app.controller('titleCtrol', function($scope, $http, $element, $compile) {
	$scope.title = '全部';
	$scope.pageSize = 18;
	$scope.show = function(event) {
		var id = event.currentTarget.children[1].innerText;
		event.preventDefault();
		$scope.id=id;
		$scope.title = this.child.name;
		var list = $element.find('div[list]');
		list.empty().removeAttr('list').attr('list', '');
		$compile(list)($scope);
	}
});


app.directive('list', function() {
	return {
		restrict : 'EA',
		templateUrl : 'list.html'
	}
});