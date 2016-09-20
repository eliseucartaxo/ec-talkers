(function() {
   angular.module('ec-talkers.user').factory('User', User);

   function User($resource) {
      //Default user
      var userInfo = {
         username: '',
         nickname: ''
      };

      //Resources to get info about user
      var resources = $resource('Users', [], {
         'login': {
            url: 'auth/login',
            method: 'POST',
            isArray: false,
            headers: {
               'Content-Type': 'application/json'
            }
         },
         'create': {
            url: 'users',
            method: 'POST',
            isArray: false,
            headers: {
               'Content-Type': 'application/json'
            }
         },
         'getByUsername': {
            url: 'users/:username',
            method: 'GET',
            isArray: false,
            headers: {
               'Content-Type': 'application/json'
            }
         },
         'getById': {
            url: 'users/:id',
            method: 'GET',
            isArray: false,
            headers: {
               'Content-Type': 'application/json'
            }
         }
      });


      return {
         getUser: function() {
            return userInfo;
         },
         setUser: function(username, nickname) {
            userInfo.username = username;
            userInfo.nickname = nickname;
         },
         getResources: function() {
            return resources;
         }
      };
   }
})();