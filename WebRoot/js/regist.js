var app = angular.module("regist", []);
angularConfig(app);

app.controller('registCtrol', function($scope, $http, $element) {
	$scope.title = '注册页';

	$scope.errorTips = {
		'loginname' : '登录名不能为空',
		'password' : '密码不能为空',
		'repassword' : '确认密码不能为空',
		'email' : '邮箱不能为空'
	};

	$scope.change = function(target) {
		if (!$scope.user[target]) {
			$element.find('input[name="' + target + '"]').popover({
				content : $scope.errorTips[target],
				placement : 'top',
				trigger : 'manual'
			});
			$element.find('input[name="' + target + '"]').popover('show');
		} else {
			$element.find('input[name="' + target + '"]').popover('destroy');
		}
	};

	$scope.submit = function() {
		if ($scope.registForm.$valid) {
			$http.post("/goods/operate/user/registUser.do", $scope.user).success(function(result) {
				if (!result) {
					$scope.errorMsg = '注册失败';
					$("#myModal").modal({
						"backdrop" : "static",
						"keyboard" : true,
						"show" : true
					});
				} else {
					window.location.href = "/goods/index.html";
				}
			});
		}

		// if (!$scope.loginname) {
		// $scope.errorMsg = '登录名不能为空';
		// $("#myModal").modal({
		// "backdrop" : "static",
		// "keyboard" : true,
		// "show" : true
		// });
		// } else if (!$scope.password) {
		// $scope.errorMsg = '密码不能为空';
		// $("#myModal").modal({
		// "backdrop" : "static",
		// "keyboard" : true,
		// "show" : true
		// });
		// } else if (!$scope.repassword) {
		// $scope.errorMsg = '确认密码不能为空';
		// $("#myModal").modal({
		// "backdrop" : "static",
		// "keyboard" : true,
		// "show" : true
		// });
		// } else if (!$scope.email) {
		// $scope.errorMsg = '邮箱不能为空';
		// $("#myModal").modal({
		// "backdrop" : "static",
		// "keyboard" : true,
		// "show" : true
		// });
		// } else if (!$scope.verifyCode) {
		// $scope.errorMsg = '验证码不能为空';
		// $("#myModal").modal({
		// "backdrop" : "static",
		// "keyboard" : true,
		// "show" : true
		// });
		// } else {
		// $http.post("/goods/operate/user/registUser.do", {
		// loginname : $scope.loginname,
		// loginpass : $scope.password,
		// email : $scope.email,
		// }).success(function(result) {
		// if (!result) {
		// $scope.errorMsg = '注册失败';
		// $("#myModal").modal({
		// "backdrop" : "static",
		// "keyboard" : true,
		// "show" : true
		// });
		// } else {
		// window.location.href = "/goods/index.html";
		// }
		// });
		// }
	};
});