'use strict';

/* Directives */


angular.module('idic.directives', []).
directive('appVersion', ['version', function(version) {
    return function(scope, elm, attrs) {
        elm.text(version);
    };
}]);

/*
//when a tab is clicked make it active
app.directive('mcToggleActive', function() {
	return {
		link: function(scope, element, attrs) {
			element.find('li').on('click', function() {
                $(this).addClass('active').siblings().removeClass('active');
            });
		}
	}
});
*/