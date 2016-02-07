import {Observable, BehaviorSubject, Subject} from 'rxjs/Rx';
import {IStorage, ICacheEntry} from './cache';

declare var localForage: LocalForage;

export class LocalForageStorage implements IStorage {
  public get(key: string): Observable<ICacheEntry> {
    var result = new Subject<ICacheEntry>();
    localForage.getItem(key)
      .then((value: ICacheEntry) => {
        result.next(value);
      });
    return result;
  }

  public set(key: string, value: ICacheEntry): Observable<boolean> {
    var result = new Subject<boolean>();
    localForage.setItem(key, value).then((value: ICacheEntry) => {
      result.next(true);
    });
    return result;
  }

  public clear(): Observable<boolean> {
    var result = new Subject<boolean>();
    localForage.clear((err: any) => {
      if (!_.isUndefined(err)) {
        result.next(true);
      } else {
        result.error(err);
      }
    });
    return result;
  }

}
