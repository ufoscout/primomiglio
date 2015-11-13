import {core} from "../core.module";
import {IResourceManager} from "./resourceManager.service";

describe('core.resource:resourceManager.service', function () {

  var resourceManager:IResourceManager, scope:angular.IScope;

  beforeEach(angular.mock.module(core.MODULE_NAME));

  beforeEach(inject(function (_$rootScope_:angular.IScope, _resourceManager_: IResourceManager) {
    resourceManager = _resourceManager_;
    scope = _$rootScope_.$new();
  }));

  it('ResourceManager service should be defined', inject(function () {
    resourceManagerShouldBeDefined();
  }));

  function resourceManagerShouldBeDefined() {
    expect(resourceManager).toBeDefined();
  }

});
