var app = angular.module("main", [ 'list','detail','show']);
angularConfig(app);
app.controller('indexCtrol',function($scope, $http, $element, $compile){
	$scope.title = '全部';
	$scope.pageSize = 18;
	$http.post("/goods/operate/user/findUser.do").success(function(user) {
		$scope.user = user;
		//console.log($scope.user)
		$scope.uid=user.uid;
	});
	$scope.showDetial = function (event){
		$scope.cid = event.currentTarget.children[0].innerText;
		$http.post("/goods/operate/CD/showCDDetial.do",{cid:$scope.cid}).success(function(cd){
			$scope.cd = cd;
			//console.log($scope.cd)
			var detail = $element.find('div#content');
			detail.empty().removeAttr('show').attr('detail', '');
			$compile(detail)($scope);
		});
		
	}
	$scope.show = function(event) {
		var detail = $element.find('div#content');
		detail.empty().removeAttr('detail').attr('show','');
		$compile(detail)($scope);
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
app.directive('detail', function() {
	return {
		restrict : 'EA',
		templateUrl : 'detail.html'
	}
});

app.directive('show',function(){
	return{
		restrict : 'EA',
		templateUrl : 'show.html'
	}
});