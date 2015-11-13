import {IStorage, ICacheEntry} from "./cache"

export class VolatileStorage implements IStorage {

  private cache:{ [key: string]: ICacheEntry } = {};

  public get(key:string):Rx.Observable<ICacheEntry> {
    return new Rx.BehaviorSubject(this.cache[key]).asObservable();
  }

  public set(key:string, value:ICacheEntry):Rx.Observable<boolean> {
    this.cache[key] = value;
    return new Rx.BehaviorSubject(true).asObservable();
  }

  public clear():Rx.Observable<boolean> {
    this.cache = {};
    return new Rx.BehaviorSubject(true).asObservable();
  }

}
