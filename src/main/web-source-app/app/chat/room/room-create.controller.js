(function() {
	'use strict';
	angular.module('ec-talkers.room').controller('RoomCreateController', RoomCreateController);

	function RoomCreateController(RoomFactory, $stateParams) {
		var vm = this;

		vm.feedback = {
			success: undefined,
			message: ''
		};

		vm.doSave = save;

		vm.room = {
			name: '',
			description: ''
		};


		function save() {
			RoomFactory.save(vm.room);
		}
	}
})();