import {core} from "../core/core.module";
import {HelloStore} from "./store/hello.store";
import {HelloActions} from "./actions/hello.actions";
import {helloDirective} from "./view/hello.view";

export module hello {
  'use strict';

  export var MODULE_NAME: string = 'hello';

  export var helloModule: angular.IModule = angular.module(MODULE_NAME, [core.MODULE_NAME]);

  helloModule.service('helloActions', HelloActions);
  helloModule.service('helloStore', HelloStore);
  hello.helloModule.directive('helloDirective', helloDirective);

}
