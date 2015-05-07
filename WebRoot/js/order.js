var app = angular.module('order', []);
app.controller('orderCtrol', function($scope, $http, $element, $compile,
		$rootScope) {
	$(function () {
		  $('[data-toggle="popover"]').popover()
		});
   $http.post('/goods/operate/order/showOrder.do', {
		uid : $scope.uid,
		status : $scope.status
	}).success(function(orders) {
		$scope.orders = orders;
	});

   $scope.changeStatus =function(event){
	   $scope.status=event.target.children[0].value;
	   if($scope.status==" "){
		   $http.post('/goods/operate/order/showOrder.do', {
				uid : $scope.uid,
			}).success(function(orders) {
				$scope.orders = orders;
			});
	   }else{
		   $http.post('/goods/operate/order/showOrder.do', {
				uid : $scope.uid,
				status : $scope.status
			}).success(function(orders) {
				$scope.orders = orders;
			});
	   }
	   
   }
   
	
});
app.filter('checkStatus',function(){
    return function(status){
       if(0==status){
    	   return "未付款";
       }else if (1==status){
    	   return "已发货";
       }else if(2==status){
    	   return "未收货";
       }else if(3==status){
    	   return "已收货";
       }else if(4==status){
    	   return "已取消";
       }
    }
});