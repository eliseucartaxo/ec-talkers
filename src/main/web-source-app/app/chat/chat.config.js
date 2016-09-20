(function() {
	'use strict';
	angular.module('ec-talkers.chat').config(configChat);

	function configChat($stateProvider, $urlRouterProvider) {
		//State config

		$stateProvider
			.state('rooms', {
				url: '/rooms',
				templateUrl: 'html/chat.home.html',
				abstract: true,
			})
			.state('rooms.list', {
				url: '',
				views: {
					'sidebar': {
						templateUrl: 'html/sidebar-rooms.html',
						controller: 'SidebarController',
						controllerAs: 'vm'
					},
					'dashboard': {
						templateUrl: 'html/default-dashboard.html'
					}
				}
			})
			.state('rooms.list.create', {
				url: '/new',
				views: {
					'dashboard@rooms': {
						templateUrl: 'html/room-create.html',
						controller: 'RoomCreateController',
						controllerAs: 'vm'
					}
				}
			})
			.state('rooms.list.chat', {
				url: '/chat/{roomId}',
				views: {
					'dashboard@rooms': {
						templateUrl: 'html/room-chat.html',
						controller: 'RoomChatController',
						controllerAs: 'vm'
					}
				}
			});
	}
})();