import {Injectable} from 'angular2/core';
import {Observable} from 'rxjs/Rx';
import {IStorage, ICacheEntry, ICache} from './cache';
import {VolatileStorage} from './cache.impl.inMemoryCache';
import {SessionStorage} from './cache.impl.sessionStorageCache';
import {LocalForageStorage} from './cache.impl.localForageCache';
import  'lodash';

declare var _: _.LoDashStatic;

class Cache implements ICache {

  constructor(private storage: IStorage) {
  }

  public get<T>(key: string): Observable<T> {
    return this.storage.get(key)
      .map((entry: ICacheEntry) => {
        if (_.isUndefined(entry)) {
          return undefined;
        }
        return entry.value;
      });
  }

  public set(key: string, value: any): Observable<boolean> {
    return this.storage.set(key, {
      value: value,
      date: undefined
    });
  }

  public clear(): Observable<boolean> {
    return this.storage.clear();
  }

}

@Injectable()
export class CacheService {

  public volatileCache: ICache = new Cache(new VolatileStorage());

  public sessionCache: ICache = new Cache(new SessionStorage());

  public permanentCache: ICache = new Cache(new LocalForageStorage());

}

