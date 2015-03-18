var app = angular.module("main",[]);
angularConfig(app);

app.controller('menuCtrol',function($scope,$http){
	$scope.title = '主页';
	
	
	$http.post("/goods/operate/menu/showMenus.do",{
		
	}).success(function(MenuDTOParents){
		$scope.MenuDTOParents = MenuDTOParents;
		for(var i=0;i<MenuDTOParents.length;i++){
			$scope.childern = MenuDTOParents[i].children;
			
		}
	});
	
});
	
