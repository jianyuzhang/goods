var app = angular.module("main", [ 'list','detail']);
angularConfig(app);
app.controller('indexCtrol',function($scope, $http, $element, $compile){
	$scope.title = '全部';
	$scope.pageSize = 18;
	$http.post("/goods/operate/user/findUser.do").success(function(user) {
		$scope.user = user;
		//console.log($scope.user)
	});
	$scope.showDetial = function (event){
		$scope.cid = event.currentTarget.children[0].innerText;
		$http.post("/goods/operate/CD/showCDDetial.do",{cid:$scope.cid}).success(function(){
			
		});
		
	}
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
app.controller('menuCtrol', function($scope, $http) {
	$scope.slideToggle = function($event) {
		$($event.target).parents('li').find("ul").slideToggle();
	};
	$http.post("/goods/operate/menu/showMenus.do").success(function(MenuDTOParents) {
		$scope.MenuDTOParents = MenuDTOParents;
	});

});

app.controller('userCtrol', function($scope, $http) {
	$scope.logout = function() {
		$http.post("/goods/operate/user/logout.do").success(function() {
			window.location.href = "index.html";
		});
	}
});

app.directive('list', function() {
	return {
		restrict : 'EA',
		templateUrl : 'list.html'
	}
});