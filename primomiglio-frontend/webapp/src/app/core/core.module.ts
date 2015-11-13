/// <reference path="../../../.tmp/typings/tsd.d.ts" />

import {CacheService} from "./cache/cache.service"
import {EventBus} from "./eventBus/eventBus.service";
import {NetworkService} from "./network/network.service";
import {ResourceManager} from "./resource/resourceManager.service";

export module core {

  export var MODULE_NAME : string = 'core';

  export var coreModule : angular.IModule = angular.module(core.MODULE_NAME, []);

  core.coreModule.service('cacheService', CacheService);
  core.coreModule.service('eventBus', EventBus);
  core.coreModule.service('networkService', NetworkService);
  core.coreModule.provider('resourceManager', ResourceManager);

}
