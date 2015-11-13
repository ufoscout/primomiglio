/// <reference path="../../.tmp/typings/tsd.d.ts" />

import {core} from "./core/core.module";
import {hello} from "./hello/hello.module";
import {Config} from './index.config';
import {RouterConfig} from './index.route';
import {RunBlock} from './index.run';

export module main {
  'use strict';

  export var MODULE_NAME : string = 'webapp';

  angular.module(main.MODULE_NAME, ['ngAnimate', 'ngCookies', 'ngTouch', 'ngSanitize', 'ngResource', 'ui.router', 'ui.bootstrap', core.MODULE_NAME, hello.MODULE_NAME])

    .config(Config)
    .config(RouterConfig)
    .run(RunBlock)

}
