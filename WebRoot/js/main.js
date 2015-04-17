var app = angular.module("main", [ 'list','detail','show','cart','user']);
angularConfig(app);
app.controller('indexCtrol',function($scope, $http, $element, $compile){
	$scope.title = '全部';
	$scope.pageSize = 18;
	$scope.showWitch = 0;
	$http.post("/goods/operate/user/findUser.do").success(function(user) {
		$scope.user = user;
		console.log(typeof($scope.user))
		$scope.uid=user.uid;
		$scope.flag = $scope.uid==null;
		console.log($scope.flag);
	});
	$scope.showDetial = function (event){
		$scope.cid = event.currentTarget.children[0].innerText;
		$http.post("/goods/operate/CD/showCDDetial.do",{cid:$scope.cid}).success(function(cd){
			$scope.cd = cd;
			//console.log($scope.cd)
			var detail = $element.find('div#content');
			detail.empty().removeAttr('cart').removeAttr('show').removeAttr('user').attr('detail', '');
			$compile(detail)($scope);
		});
		
	}
	$scope.show = function(event) {
		var detail = $element.find('div#content');
		detail.empty().removeAttr('detail').removeAttr('cart').removeAttr('user').attr('show','');
		$compile(detail)($scope);
		var id = event.currentTarget.children[1].innerText;
		event.preventDefault();
		$scope.id=id;
		$scope.title = this.child.name;
		var list = $element.find('div[list]');
		list.empty().removeAttr('list').attr('list', '');
		$compile(list)($scope);
	}
	$scope.showCart = function(event){
		/*
		 * 判断当前是否有用户登陆
		 */
		if ($scope.uid==null) {
			window.location.href = "index.html";
		} else {
		var cart = $element.find('div#content');
		cart.empty().removeAttr('detail').removeAttr('show').removeAttr('user').attr('cart', '');
		$compile(cart)($scope);
		}
	}
	$scope.showUser = function(event){
		if ($scope.uid==null) {
			window.location.href = "index.html";
		} else {
		var user = $element.find('div#content');
		user.empty().removeAttr('detail').removeAttr('show').removeAttr('cart').attr('user', '');
		$compile(user)($scope);
		
		}
	}
});
app.controller('buttonCtrol', function($scope,$compile,$element){
	
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
app.directive('cart',function(){
	return{
		restrict : 'EA',
		templateUrl : 'cart.html'
	}
});
app.directive('user',function(){
	return{
		restrict :'EA',
		templateUrl : 'user.html'
	}
})