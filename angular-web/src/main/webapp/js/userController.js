function userController($scope, $window, $http) {

    var init = function () {
	$http.get('/angular/UserController?metodo=list').success(function(data){
		$scope.users = data;
	});
    }();

    $scope.salvarUser = function (form) {
    	if(!form.$valid){
    		return false;
    	}
    	var user = angular.toJson($scope.user);
    	$http.post('/angular/UserController?metodo=save&user='+user, user).success(function(data){
				$scope.users = data;
				$window.alert('Salvo com sucesso!');
				reset();
    	});
    };

    $scope.deleteUser = function(user){
	var confirm = $window.confirm('Confirma a exclusão ?');
	if(confirm){
    	var userJson = angular.toJson(user);
		var url = '/angular/UserController?metodo=delete&user=' + userJson;

		$http.post(url).success(function(data){
			var index = $scope.users.indexOf(user);
			$scope.users.splice(index, 1);
		});
	}
    };
	
    $scope.edit = function(user){
	$scope.user = user;
	$(".btn-success").attr("disabled", "disabled");
	$(".btn-primary").removeAttr("disabled");
    };
    
    var reset = function(){
    	$scope.user = {id : 0, nome : "", sobreNome : "", idade: ""};
        };
	
}
