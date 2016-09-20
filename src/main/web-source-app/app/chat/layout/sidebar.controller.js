(function() {
	'use strict';
	angular.module('ec-talkers.chat').controller('SidebarController', SidebarController);

	function SidebarController(RoomFactory) {
		var vm = this;

		vm.allRooms = RoomFactory.rooms;

		RoomFactory
			.getRooms()
			.then(function() {
				console.log('Getting rooms');
				vm.allRooms = RoomFactory.rooms;
			});
	}
})();