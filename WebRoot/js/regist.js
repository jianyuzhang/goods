var app = angular.module("regist", []);
angularConfig(app);

app.controller('registCtrol', function($scope, $http, $element) {
	$scope.title = '注册页';

	$scope.errorTips = {
		'loginname': {
			required: '登录名不能为空'
		},
		'password': {
			required: '密码不能为空'
		},
		'repassword': {
			required: '确认密码不能为空'
		},
		'email': {
			required: '邮箱不能为空',
			email: '邮箱格式错误'
		}
	};

	$scope.change = function(event) {
		if (event) {
			$element.find(event.target).popover('destroy');
			if ($scope.registForm[$element.find(event.target).attr('name')].$invalid) {
				var content = '';
				for ( var i in $scope.registForm[$element.find(event.target).attr('name')].$error) {
					content = $scope.errorTips[$element.find(event.target).attr('name')][i];
				}
				$element.find(event.target).popover({
					content: content,
					placement: 'top',
					trigger: 'manual'
				});
				$element.find(event.target).popover('show');
			}
		}
	};

	$scope.checkUser = function(event) {
		$scope.change(event);
		if (event && $scope.user && $scope.user.loginname) {
			$element.find(event.target).popover('destroy');
			$http.post("/goods/operate/user/ajaxValidateLoginname.do", {
				loginname: $scope.user.loginname
			}).success(function(result) {
				if (result == true || result == 'true') {
					$element.find(event.target).popover({
						content: '用户名不可使用',
						placement: 'top',
						trigger: 'manual'
					});
					$element.find(event.target).popover('show');
				} else {
					$element.find(event.target).popover('destroy');
				}
			});
		}
	}
	
	$scope.submit = function() {
		if ($scope.registForm.$valid) {
			$http.post("/goods/operate/user/registUser.do", $scope.user).success(function(result) {
				if (!result) {
					$scope.errorMsg = '注册失败';
					$("#myModal").modal({
						"backdrop": "static",
						"keyboard": true,
						"show": true
					});
				} else {
					window.location.href = "/goods/index.html";
					
				}
			});
		}
	};
});

app.directive('ngBlur', function() {
	return {
		restrict: 'A',
		link: function(scope, element, attr) {
			element.bind('blur', function() {
				scope.$apply(attr.ngBlur);
			});
		}
	};
});

app.directive('main', function() {
	return {
		restrict: 'A',
		templateUrl: 'main.html'
	};
});