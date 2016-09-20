(function() {
	'use strict';
	angular.module('ec-talkers.room').factory('RoomFactory', RoomFactory);

	function RoomFactory($resource) {
		RoomFactory.resource = $resource('rooms', [], {
			'join': {
				url: ':roomName/join',
				method: 'PUT',
				isArray: false
			},
			'leave': {
				url: ':roomName/leave',
				method: 'PUT',
				isArray: false
			}
		});

		RoomFactory.rooms = [];

		RoomFactory.getRooms = function() {
			return RoomFactory.resource.query().$promise.then(function(responseData) {
				RoomFactory.rooms = responseData;
			}).catch(function(responseData) {
				console.log('Something wrong' + responseData);
			});
		};

		RoomFactory.save = function(room) {
			RoomFactory.resource.save(room).$promise
				.then(function(response) {					
					console.log('Room saved - OK');
				})
				.catch(function(response) {					
					console.log('Room save - ERROR');
				});
		};

		return RoomFactory;
	}
})();