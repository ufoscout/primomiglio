import {IStorage, ICacheEntry} from "./cache"

declare var localForage:LocalForage;

export class LocalForageStorage implements IStorage {
  public get(key:string):Rx.Observable<ICacheEntry> {
    var result = new Rx.Subject<ICacheEntry>();
    localForage.getItem(key)
      .then((value:ICacheEntry) => {
        result.onNext(value);
      });
    return result.asObservable();
  }

  public set(key:string, value:ICacheEntry):Rx.Observable<boolean> {
    var result = new Rx.Subject<boolean>();
    localForage.setItem(key, value).then((value:ICacheEntry) => {
      result.onNext(true);
    });
    return result.asObservable();
  }

  public clear():Rx.Observable<boolean> {
    var result = new Rx.Subject<boolean>();
    localForage.clear((err:any) => {
      if (!_.isUndefined(err)) {
        result.onNext(true);
      } else {
        result.onError(err);
      }
    });
    return result.asObservable();
  }

}
