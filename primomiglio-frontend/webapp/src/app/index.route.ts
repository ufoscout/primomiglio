'use strict';

export class RouterConfig {
  /** @ngInject */
  constructor($stateProvider:ng.ui.IStateProvider, $urlRouterProvider:ng.ui.IUrlRouterProvider) {
    $stateProvider
      .state('home', {
        url: '/',
        template: '<div hello-directive></div>'
      });

    $urlRouterProvider.otherwise('/');
  }

}
