import {
  it,
  inject,
  injectAsync,
  describe,
  beforeEachProviders,
  TestComponentBuilder
} from 'angular2/testing';
import {NetworkService} from './network.service';

describe('core.network:network.service', function () {

  // provide our implementations or mocks to the dependency injector
  beforeEachProviders(() => [
    NetworkService
  ]);

  it('Network service should be defined', inject([ NetworkService ], (networkService) => {
    networkShouldBeDefined(networkService);
  }));

  it('Should always return online true with default strategy', inject([ NetworkService ], (networkService: NetworkService) => {
    expect(networkService.isOnline()).toBeTruthy();
  }));

  function networkShouldBeDefined(networkService: NetworkService) {
    expect(networkService).toBeDefined();
  }

});
