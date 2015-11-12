
import {main} from "../index.module";
import {MainController} from "./main.controller";

(function() {
  'use strict';

  describe('controllers', function(){

    beforeEach(angular.mock.module(main.MODULE_NAME));
/*
    it('should define more than 5 awesome things', inject(function($controller) {
      var vm : main.MainController = $controller(main.MainController);

      expect(angular.isArray(vm.classAnimation)).toBeDefined();

    }));
*/
/*
    it('should inject a service', inject(function(webDevTec : main.WebDevTecService) {
      expect(webDevTec).toBeDefined();
    }));
*/
  });
})();
