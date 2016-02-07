import {
  it,
  inject,
  injectAsync,
  describe,
  beforeEachProviders,
  TestComponentBuilder
} from 'angular2/testing';
import {ResourceManager} from './resourceManager.service';
import {NetworkService} from '../network/network.service';

describe('core.resource:resourceManager.service', function () {

  // provide our implementations or mocks to the dependency injector
  beforeEachProviders(() => [
    ResourceManager,
    NetworkService
  ]);

  it('ResourceManager service should be defined', inject([ResourceManager], (resourceManager : ResourceManager) => {
    resourceManagerShouldBeDefined(resourceManager);
  }));

  function resourceManagerShouldBeDefined(resourceManager : ResourceManager) {
    expect(resourceManager).toBeDefined();
  }

});
