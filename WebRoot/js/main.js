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

app.controller('titleCtrol',function($scope,$http, $element,$compile){
	$scope.title = '全部';
	$scope.show = function(event){
		var id=event.currentTarget.children[1].innerText;
		event.preventDefault();
		$scope.title=this.child.name;
		$http.post("/goods/operate/CD/showSomeCDs.do",{mid:id}).success(function(allcds){
			$scope.allcds=allcds;})
			$http.post("/goods/operate/CD/count.do").success(function(count){
		console.log(count[0])
		if(count[0]<=18){
			$scope.style={visible:'hidden'}
		}
	});
        var list = $element.find('div[list]');
        list.empty().removeAttr('list').attr('list','');
        $compile(list)($scope);
	}
});

app.controller('cdCtrol',function($scope,$http,$compile,$element){
	$http.post("/goods/operate/CD/showAllCDs.do").success(function(allcds){
		$scope.allcds=allcds;
		 var list = $element.find('div[list]');
	        list.empty().removeAttr('list').attr('list','');
	        $compile(list)($scope);
	});
	$http.post("/goods/operate/CD/count.do").success(function(count){
		console.log(count)
		
	});
});

app.directive('list', function() {
	return {
		restrict: 'EA',
		templateUrl: 'list.html'
	}
});