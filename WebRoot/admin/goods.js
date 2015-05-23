var app = angular.module('goods', []);
app.controller('goodsCtrol', function($http, $scope) {
	$http.post('/goods/operate/CD/showAllCDs.do').success(function(CDs) {
		$scope.cds = CDs;
	});
	$scope.addCD = function() {
		$('#myModal').modal('show');
	}
	$scope.addcd = function(cd) {
		console.log(cd);
	}
	$scope.editcd = function(cd) {
		$('#myModal1').modal('show');
		$scope.cd = cd;
		console.log($scope.cd);
	}
	$scope.editCD = function(cd) {
		console.log(cd);
		$http.post('/goods/operate/CD/editCD.do', {
			cid : cd.cid,
			mid : cd.mid,
			sum : cd.sum,
			price : cd.price,
			currPrice : cd.currPrice,
			cname : cd.cname,
			singer : cd.singer,
			language : cd.language,
			printtime : cd.printtime
		}).success(function() {
        $('#myModal1').modal('hide');
		});
	}
});

app.filter('checkMid', function() {
	return function(mid) {
		if (10 == mid) {
			return "70后";
		} else if (11 == mid) {
			return "80后";
		} else if (12 == mid) {
			return "90后";
		} else if (20 == mid) {
			return "夜店";
		} else if (21 == mid) {
			return "车载";
		} else if (22 == mid) {
			return "咖啡馆";
		} else if (30 == mid) {
			return "戏迷";
		} else if (31 == mid) {
			return "文青";
		} else if (32 == mid) {
			return "dj";
		} else if (33 == mid) {
			return "流行";
		} else if (34 == mid) {
			return "名族";
		} else if (35 == mid) {
			return "纯乐";
		}
	}
});

app.directive('imgUploadify', function() {
	return {
		restrict : 'A',
		link : function($scope, elm, attrs) {
			var opts = angular.extend({}, $scope.$eval(attrs.imgUploadify));

			var uploader = new plupload.Uploader({
				file_data_name : 'fileUpload',
				runtimes : 'html5,flash,silverlight,html4',
				browse_button : elm.attr('id'),
				container : elm.parent()[0],
				url : opts.url || '',
				flash_swf_url : 'js/Moxie.swf',
				silverlight_xap_url : 'js/Moxie.xap',
				filters : {
					max_file_size : '20mb'
									},
				init : {
					FilesAdded : function(up, files) {
						uploader.start();
					},
					FileUploaded : function(up, file, info) {

					}
				}
			});
			uploader.init();
		}
	}
});
