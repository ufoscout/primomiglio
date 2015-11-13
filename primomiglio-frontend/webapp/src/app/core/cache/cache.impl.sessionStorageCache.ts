import {IStorage, ICacheEntry} from "./cache"

export class SessionStorage implements IStorage {

  public get(key:string):Rx.Observable<ICacheEntry> {
    return new Rx.BehaviorSubject(JSON.parse(sessionStorage.getItem(key))).asObservable();
  }

  public set(key:string, value:ICacheEntry):Rx.Observable<boolean> {
    sessionStorage.setItem(key, JSON.stringify(value));
    return new Rx.BehaviorSubject(true).asObservable();
  }

  public clear():Rx.Observable<boolean> {
    sessionStorage.clear();
    return new Rx.BehaviorSubject(true).asObservable();
  }

}
