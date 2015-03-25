var app = angular.module("main",[]);
angularConfig(app);

app.controller('menuCtrol',function($scope,$http){
	$scope.title = '主页';
	
	$scope.slideToggle = function($event) {
		$($event.target).parents('li').find("ul").slideToggle();
	};
	
	$http.post("/goods/operate/menu/showMenus.do").success(function(MenuDTOParents){
		$scope.MenuDTOParents = MenuDTOParents;
	});
	
});
	
app.controller('userCtrol',function($scope,$http){
	$http.post("/goods/operate/user/findUser.do").success(function(user){
		$scope.user = user;
	});	
	$scope.logout = function(){
	$http.post("/goods/operate/user/logout.do").success(function(){
        window.location.href="index.html";		
	});
	}
});

app.controller('titleCtrol',function($scope){
	$scope.show = function(){
		$scope.title=this.child.name;
		//console.log($scope.title);
	}
});