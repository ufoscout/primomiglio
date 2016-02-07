import {
  it,
  inject,
  injectAsync,
  describe,
  beforeEachProviders,
  TestComponentBuilder
} from 'angular2/testing';

import {Component, provide} from 'angular2/core';
import {BaseRequestOptions, Http} from 'angular2/http';
import {MockBackend} from 'angular2/http/testing';
import {CacheService} from './cache.service';
import {ICache} from './cache';

describe('core.cache:Cache.service', function(){

  // provide our implementations or mocks to the dependency injector
  beforeEachProviders(() => [
    BaseRequestOptions,
    MockBackend,
    provide(Http, {
      useFactory: function(backend, defaultOptions) {
        return new Http(backend, defaultOptions);
      },
      deps: [MockBackend, BaseRequestOptions]
    }),

    CacheService
  ]);

  var cache: ICache, key: string, value: any, valueFromGet: any;


  it('CacheService should be defined', inject([ CacheService ], (cacheService) => {
    expect(cacheService).toBeDefined();
  }));

  it('Volatile - cache should be defined', inject([ CacheService ], (cacheService) => {
    whenCacheIsVolatile(cacheService);
    thenCacheShouldNotBeNull();
  }));

  it('SessionStorage - cache should be defined', inject([ CacheService ], (cacheService) => {
    whenCacheIsSessionStorage(cacheService);
    thenCacheShouldNotBeNull();
  }));

  it('LocalForage - cache should be defined', inject([ CacheService ], (cacheService) => {
    whenCacheIsLocalForage(cacheService);
    thenCacheShouldNotBeNull();
  }));

  it('Volatile - should be possible to set an Object value', inject([ CacheService ], (cacheService) => {
    whenCacheIsVolatile(cacheService);
    whenKeyAndObjectValueDefined();
    whenValueIsSet();
    whenGetIsCalled();
    thenGetValueShouldBe(valueFromGet);
  }));

  it('SessionStorage - should be possible to set an Object value', inject([ CacheService ], (cacheService) => {
    whenCacheIsSessionStorage(cacheService);
    whenKeyAndObjectValueDefined();
    whenValueIsSet();
    whenGetIsCalled();
    thenGetValueShouldBe(valueFromGet);
  }));

  it('Volatile - should be possible to set a string value', inject([ CacheService ], (cacheService) => {
    whenCacheIsVolatile(cacheService);
    whenKeyAndStringValueDefined();
    whenValueIsSet();
    whenGetIsCalled();
    thenGetValueShouldBe(valueFromGet);
  }));

  it('SessionStorage - should be possible to set a string value', inject([ CacheService ], (cacheService) => {
    whenCacheIsVolatile(cacheService);
    whenKeyAndStringValueDefined();
    whenValueIsSet();
    whenGetIsCalled();
    thenGetValueShouldBe(valueFromGet);
  }));

  it('Volatile - should be possible to clear the cache', inject([ CacheService ], (cacheService) => {
    whenCacheIsVolatile(cacheService);
    whenKeyAndObjectValueDefined();
    whenValueIsSet();
    whenCacheIsCleared();
    thenGetValueShouldBe(undefined);
  }));

  it('SessionStorage - should be possible to clear the cache', inject([ CacheService ], (cacheService) => {
    whenCacheIsSessionStorage(cacheService);
    whenKeyAndObjectValueDefined();
    whenValueIsSet();
    whenCacheIsCleared();
    thenGetValueShouldBe(undefined);
  }));

  function whenCacheIsVolatile(cacheService: CacheService) {
    cache = cacheService.volatileCache;
  }

  function whenCacheIsSessionStorage(cacheService: CacheService) {
    cache = cacheService.sessionCache;
  }

  function whenCacheIsLocalForage(cacheService: CacheService) {
    cache = cacheService.permanentCache;
  }

  function thenCacheShouldNotBeNull() {
    expect(cache).toBeDefined();
  }

  function whenKeyAndObjectValueDefined() {
    key = 'key' + new Date();
    value = {
      'value': key
    };
    valueFromGet = undefined;
  }

  function whenKeyAndStringValueDefined() {
    key = 'key' + new Date();
    value = key + '-value';
    valueFromGet = undefined;
  }

  function whenValueIsSet() {
    var result : boolean = false;
    cache.set(key, value).subscribe((op: boolean) => {
      result = op;
    });
    expect(result).toBeTruthy();
  }

  function whenCacheIsCleared() {
    var result : boolean = false;
    cache.clear().subscribe((op: boolean) => {
      result = op;
    });
    expect(result).toBeTruthy();
  }

  function whenGetIsCalled(getKey : string = key) {
    cache.get(key).subscribe((value: any) => {
      valueFromGet = value;
    });
  }

  function thenGetValueShouldBe(expected : any) {
    expect(valueFromGet).toBe(expected);
  }

});
