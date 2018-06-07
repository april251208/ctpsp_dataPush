//项目绝对路径
var absContextUrl = function($location) {
    var s = $location.absUrl();
    s = s.substring(0,s.indexOf("#"));
    return s.substring(0, s.lastIndexOf("/"));
}

var app=angular.module("myApp",["ngRoute"]);

//配置路由
app.config(["$locationProvider","$routeProvider",function($locationProvider,$routeProvider){

    //angularJs1.6.4版本需要加入

    $locationProvider.hashPrefix('');

    $routeProvider.
    when('/seachAll',{
        templateUrl:'list.html',
        controller:'ListControl'
    }).
    when('/addUser',{
        templateUrl:'add.html',
        controller:'AddControl'
    }).
    when('/updateUser/:id',{
        templateUrl:'update.html',
        controller:'UpdateControl'
    }).
    otherwise({
        redirectTo:'/seachAll'
    });

}]);

//控制器
app.controller("ListControl",["$scope", "$location", "$rootScope", "$http", function($scope, $location, $rootScope, $http){

    $http({
        method: 'get',
        url: absContextUrl($location) + '/ssm_list',
    }).then(function(response) {
        //数据源
        $rootScope.data = response.data;
        //分页总数
        $rootScope.pageSize = 5;
        $rootScope.pages = Math.ceil($rootScope.data.length / $rootScope.pageSize); //分页数
        $rootScope.newPages = $rootScope.pages > 5 ? 5 : $rootScope.pages;
        $rootScope.pageList = [];
        $rootScope.selPage = 1;
        //设置表格数据源(分页)
        $rootScope.setData = function () {
            $rootScope.items = $rootScope.data.slice(($rootScope.pageSize * ($rootScope.selPage - 1)), ($rootScope.selPage * $rootScope.pageSize));//通过当前页数筛选出表格当前显示数据
        }
        $rootScope.items = $rootScope.data.slice(0, $rootScope.pageSize);
        //分页要repeat的数组
        for (var i = 0; i < $rootScope.newPages; i++) {
            $rootScope.pageList.push(i + 1);
        }
        //打印当前选中页索引
        $rootScope.selectPage = function (page) {
            //不能小于1大于最大
            if (page < 1 || page > $rootScope.pages) return;
            //最多显示分页数5
            if (page > 2) {
                //因为只显示5个页数，大于2页开始分页转换
                var newpageList = [];
                for (var i = (page - 3) ; i < ((page + 2) > $rootScope.pages ? $rootScope.pages : (page + 2)) ; i++) {
                    newpageList.push(i + 1);
                }
                $rootScope.pageList = newpageList;
            }
            $rootScope.selPage = page;
            $rootScope.setData();
            $rootScope.isActivePage(page);
            console.log("选择的页：" + page);
        };
        //设置当前选中页样式
        $rootScope.isActivePage = function (page) {
            return $rootScope.selPage == page;
        };
        //上一页
        $rootScope.Previous = function () {
            $rootScope.selectPage($rootScope.selPage - 1);
        }
        //下一页
        $rootScope.Next = function () {
            $rootScope.selectPage($rootScope.selPage + 1);
        };
    }, function(response) {
        alert(response.status);
    });
    $scope.toAdd=function(){
        $location.path("/addUser");
    };

    $scope.del=function(id){
        $http({
            url: absContextUrl($location) + "/ssm_del/" + id,
            method: 'delete'
        }).then(function(response){
            $location.path("/route.html");
        },function(response){
            alert(response.status)
        })
    };

    $scope.toUpd=function(id){
        $location.path("/updateUser/"+id);
    };

}]);

app.controller('AddControl',["$scope","$location","$rootScope","$http",function($scope,$location,$rootScope,$http){
    //下拉框默认选中一个值

    $scope.aid="1";
    $scope.tid="1";

    //查询下拉框所需数据
    $http({
        method: 'get',
        url: absContextUrl($location) + '/ssm_seachAddressesAndTypes',
    }).then(function(response) {
        $rootScope.addresses = response.data.addresses;
        $rootScope.types = response.data.types
    }, function(response) {
        alert(response.status);
    });

    //添加数据
    $scope.save=function(){
        var team={"name":$scope.name,"datea":$scope.datea,"description":$scope.description,"aid":$scope.aid,"tid":$scope.tid}
        $http({
            method:'post',
            url:absContextUrl($location)+'/ssm_insert',
            data:team ,
            headers:{'content-Type':'application/json'}
        }).then(function(response){
            $location.path("/seachAll");
        },function(response){
            alert(response.status)
        })
    }
}]);

app.controller("UpdateControl",["$scope","$routeParams","$rootScope","$location","$http",function($scope,$routeParams,$rootScope,$location,$http){
    //修改回显

        $http({
            method: 'get',
            url: absContextUrl($location) + '/ssm_seachAddressesAndTypes',
        }).then(function(response) {
            $rootScope.addresses = response.data.addresses;
            $rootScope.types = response.data.types;
        }, function(response) {
            alert(response.status);
        });
        $http({
            method: 'get',
            url: absContextUrl($location) + '/ssm_selectById/'+$routeParams.id,
        }).then(function(response) {
            var team= response.data;
            $scope.id=team.id;
            $scope.name=team.name;
            $scope.datea=new Date(team.datea);
            $scope.description=team.description;
            $scope.aid=team.aid+"";
            $scope.tid=team.tid+"";

        }, function(response) {
            alert(response.status);
        });




    //修改
    $scope.save=function() {
       alert($scope.description)
        var team = {
            "id":$scope.id,
            "name": $scope.name,
            "datea": $scope.datea,
            "description": $scope.description,
            "aid": $scope.aid,
            "tid": $scope.tid
        }
        $http({
            url: absContextUrl($location) + '/ssm_update',
            method: 'put',
            data: team,
        }).then(function (response) {
            $location.path("/seachAll");
        }, function (response) {
            alert(response.status)
        })
    }

}]);
























/*
*		<!-- 定义跳转的文件的前后缀 ，视图模式配置-->
	<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<!-- 这里的配置我的理解是自动给后面action的方法return的字符串加上前缀和后缀，变成一个 可用的url地址 -->
		<property name="prefix" value="/WEB-INF/view/" />
		<property name="suffix" value=".jsp" />
	</bean>
**/
