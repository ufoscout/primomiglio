import {Observable, BehaviorSubject} from 'rxjs/Rx';
import {IStorage, ICacheEntry} from './cache';

export class SessionStorage implements IStorage {

  public get(key: string): Observable<ICacheEntry> {
    return new BehaviorSubject(JSON.parse(sessionStorage.getItem(key)));
  }

  public set(key: string, value: ICacheEntry): Observable<boolean> {
    sessionStorage.setItem(key, JSON.stringify(value));
    return new BehaviorSubject(true);
  }

  public clear(): Observable<boolean> {
    sessionStorage.clear();
    return new BehaviorSubject(true);
  }

}
