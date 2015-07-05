var app = angular.module("main", [ 'list','detail','show','cart','user','order','search']);
angularConfig(app);
app.controller('indexCtrol',function($scope, $http, $element, $compile){
	$scope.title = '全部';
	$scope.pageSize = 18;
	$scope.showWitch = 0;
	$scope.total = 0 ;
	$scope.cartNum=0;
	$http.post("/goods/operate/user/findUser.do").success(function(user) {
		$scope.user = user;
		$scope.uid=user.uid;
		$scope.flag = $scope.uid==null;
		/*
		 * 拿取当前用户的默认收件地址
		 */
		if(!$scope.flag){
			$http.post("/goods/operate/address/showSomeAddress.do", {
				uid : $scope.uid
			}).success(function(address) {
				for(var i =0;i<address.length;i++){
					if(address[i].status==1){
						$scope.info = address[i];
					}
				}
			});
			$http.post("/goods/operate/cartItem/showSomeCarts.do",{uid:$scope.uid}).success(function(carts){
				$scope.carts = carts;
				$scope.cartNum = $scope.carts.length;
				$scope.isShow = $scope.carts.length > 0;
			});
		}
		
	});
	$scope.manageRoute = function (){
		window.location.href = "admin.html";
	}
	$scope.showDetial = function (cid){
		$scope.cid = cid;
		$http.post("/goods/operate/CD/showCDDetial.do",{cid:$scope.cid}).success(function(cd){
			$scope.cd = cd;
			var detail = $element.find('div#content');
			detail.empty().removeAttr('cart').removeAttr('show').removeAttr('user').removeAttr('order').attr('detail', '');
			$compile(detail)($scope);
		});
		
	}
	$scope.show = function(event) {
		var detail = $element.find('div#content');
		detail.empty().removeAttr('detail').removeAttr('cart').removeAttr('user').removeAttr('order').attr('show','');
		$compile(detail)($scope);
		var id = event.currentTarget.children[1].innerText;
		event.preventDefault();
		$scope.id=id;
		$scope.title = this.child.name;
		var list = $element.find('div[list]');
		list.empty().removeAttr('list').attr('list', '');
		$compile(list)($scope);
	}
	$scope.showCart = function(){
		/*
		 * 判断当前是否有用户登陆
		 */
		
		if ($scope.uid==null) {
			window.location.href = "index.html";
		} else {
			$http.post("/goods/operate/cartItem/showSomeCarts.do",{uid:$scope.uid}).success(function(carts){
				$scope.carts = carts;
				$scope.cartNum = $scope.carts.length;
				$scope.isShow = $scope.carts.length > 0;
			});
		var cart = $element.find('div#content');
		cart.empty().removeAttr('detail').removeAttr('show').removeAttr('user').removeAttr('order').attr('cart', '');
		$compile(cart)($scope);
		}
	}
	$scope.showUser = function(i){
		if(i==null){
			i=0;
		}
		if ($scope.uid==null) {
			window.location.href = "index.html";
		} else {
			$scope.showWitch = i;
		var user = $element.find('div#content');
		user.empty().removeAttr('detail').removeAttr('show').removeAttr('cart').removeAttr('order').attr('user', '');
		$compile(user)($scope);
		
		}
	}
	$scope.showOrder = function(){
		var content = $element.find('div#content');
		content.empty().removeAttr('detail').removeAttr('show').removeAttr('cart').removeAttr('user').attr('order', '');
		$compile(content)($scope);
	}
	$scope.searchMusic =function (){
		$scope.title = "搜索";
		console.log($scope.keyValue);
		$http.post("/goods/operate/CD/searchListByPage.do", {
			cname : $scope.keyValue,
			singer : $scope.keyValue
	}).success(function(allcds) {
		$scope.allcds = allcds;
		var list = $element.find('div[list]');
		list.empty().removeAttr('list').attr('search', '');
		$compile(list)($scope);
	});
	
		var list = $element.find('div[list]');
		list.empty().removeAttr('list').attr('search', '');
		$compile(list)($scope);
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
		if(!$scope.flag){
			$http.post("/goods/operate/user/logout.do").success(function() {
				window.location.href = "main.html";
			});
		}else{
			window.location.href = "index.html";
		}
		
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
});
app.directive('order',function(){
	return{
		restrict :'EA',
		templateUrl : 'order.html'
	}
});
app.directive('search',function(){
	return{
		restrict : 'EA',
		templateUrl : 'search.html'
	}
});