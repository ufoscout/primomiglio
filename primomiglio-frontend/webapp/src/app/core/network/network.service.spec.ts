import {core} from "../core.module";
import {NetworkService} from "./network.service";

'use strict';

describe('core.network:network.service', function () {

  var network:NetworkService, scope:angular.IScope;

  beforeEach(angular.mock.module(core.MODULE_NAME));

  beforeEach(inject(function (_$rootScope_:angular.IScope, _networkService_:NetworkService) {
    network = _networkService_;
    scope = _$rootScope_.$new();
  }));

  it('Network service should be defined', inject(function () {
    networkShouldBeDefined();
  }));

  it('Should always return online true with default strategy', inject(function () {
    expect(network.isOnline()).toBeTruthy();
  }));

  function networkShouldBeDefined() {
    expect(network).toBeDefined();
  }

});
