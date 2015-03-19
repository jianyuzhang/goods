var app = angular.module("main",[]);
angularConfig(app);

app.controller('menuCtrol',function($scope,$http){
	$scope.title = '主页';
	
	$scope.slideToggle = function($event) {
		$($event.target).parents('li').find("ul").slideToggle();
	};
	
	$http.post("/goods/operate/menu/showMenus.do").success(function(MenuDTOParents){
		$scope.MenuDTOParents = MenuDTOParents;
//		$(".dropmenu").click(function(t) {
//			t.preventDefault();
//		})
	});
	
});
	
