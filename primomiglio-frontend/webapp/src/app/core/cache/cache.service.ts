import {IStorage, ICacheEntry, ICache} from "./cache"
import {VolatileStorage} from "./cache.impl.inMemoryCache";
import {SessionStorage} from "./cache.impl.sessionStorageCache";
import {LocalForageStorage} from "./cache.impl.localForageCache";

class Cache implements ICache {

  constructor(private storage:IStorage) {
  }

  public get<T>(key:string):Rx.Observable<T> {
    return this.storage.get(key)
      .map((entry:ICacheEntry) => {
        if (_.isUndefined(entry)) {
          return undefined;
        }
        return entry.value;
      });
  }

  public set(key:string, value:any):Rx.Observable<boolean> {
    return this.storage.set(key, {
      value: value,
      date: undefined
    });
  }

  public clear():Rx.Observable<boolean> {
    return this.storage.clear();
  }

}

export class CacheService {

  public volatileCache: ICache = new Cache(new VolatileStorage());

  public sessionCache: ICache = new Cache(new SessionStorage());

  public permanentCache: ICache = new Cache(new LocalForageStorage());

}

