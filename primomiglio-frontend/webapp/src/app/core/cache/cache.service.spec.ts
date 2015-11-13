import {core} from "../core.module";
import {CacheService} from "./cache.service";
import {ICache} from "./cache";

'use strict';

describe('core.cache:Cache.service', function(){

  var cacheService : CacheService, cache: ICache,
    key: string, value: any, valueFromGet: any;

  beforeEach(angular.mock.module(core.MODULE_NAME));

  beforeEach(inject(function(_cacheService_ : CacheService) {
    cacheService = _cacheService_;
  }));

  it('CacheService should be defined', inject(function() {
    expect(cacheService).toBeDefined();
  }));

  it('Volatile - cache should be defined', inject(function() {
    whenCacheIsVolatile();
    thenCacheShouldNotBeNull();
  }));

  it('SessionStorage - cache should be defined', inject(function() {
    whenCacheIsSessionStorage();
    thenCacheShouldNotBeNull();
  }));

  it('LocalForage - cache should be defined', inject(function() {
    whenCacheIsLocalForage();
    thenCacheShouldNotBeNull();
  }));

  it('Volatile - should be possible to set an Object value', inject(function() {
    whenCacheIsVolatile();
    whenKeyAndObjectValueDefined();
    whenValueIsSet();
    whenGetIsCalled();
    thenGetValueShouldBe(valueFromGet);
  }));

  it('SessionStorage - should be possible to set an Object value', inject(function() {
    whenCacheIsSessionStorage();
    whenKeyAndObjectValueDefined();
    whenValueIsSet();
    whenGetIsCalled();
    thenGetValueShouldBe(valueFromGet);
  }));

  it('Volatile - should be possible to set a string value', inject(function() {
    whenCacheIsVolatile();
    whenKeyAndStringValueDefined();
    whenValueIsSet();
    whenGetIsCalled();
    thenGetValueShouldBe(valueFromGet);
  }));

  it('SessionStorage - should be possible to set a string value', inject(function() {
    whenCacheIsVolatile();
    whenKeyAndStringValueDefined();
    whenValueIsSet();
    whenGetIsCalled();
    thenGetValueShouldBe(valueFromGet);
  }));

  it('Volatile - should be possible to clear the cache', inject(function() {
    whenCacheIsVolatile();
    whenKeyAndObjectValueDefined();
    whenValueIsSet();
    whenCacheIsCleared();
    thenGetValueShouldBe(undefined);
  }));

  it('SessionStorage - should be possible to clear the cache', inject(function() {
    whenCacheIsSessionStorage();
    whenKeyAndObjectValueDefined();
    whenValueIsSet();
    whenCacheIsCleared();
    thenGetValueShouldBe(undefined);
  }));

  function whenCacheIsVolatile() {
    cache = cacheService.volatileCache;
  }

  function whenCacheIsSessionStorage() {
    cache = cacheService.sessionCache;
  }

  function whenCacheIsLocalForage() {
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

})
