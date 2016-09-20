(function() {
	'use strict';
	angular.module('ec-talkers.chat').factory('WebSocketService', WebSocketService);

	function WebSocketService() {

		WebSocketService.socket = {
			client: undefined,
			stomp: undefined
		};

		WebSocketService.initSocket = function(room) {
			WebSocketService.close(room);
			WebSocketService.socket.client = new SockJS('/talker/');
			var stomp = Stomp.over(WebSocketService.socket.client);

			stomp.debug = function(str) {};
			stomp.connect({}, function() {
				stomp.subscribe('/topic/' + room.name + '/messages',
					function(message) {
						WebSocketService.notifyNewMessage(message);
					});
				stomp.subscribe('/topic/' + room.name + '/participants',
					function(message) {
						WebSocketService.notifyParticipant(message);
					});
			});
			WebSocketService.socket.stomp = stomp;
		};

		WebSocketService.close = function(room) {
			if (WebSocketService.socket.client) {
				WebSocketService.socket.client.close();
			}
			if (WebSocketService.socket.stomp) {
				WebSocketService.socket.stomp.disconnect();
			}
		};
	}
})();