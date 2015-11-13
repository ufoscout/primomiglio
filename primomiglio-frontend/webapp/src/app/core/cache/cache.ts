export interface ICacheEntry {
  value: any;
  date: Date
}

export interface IStorage {
  get(key:string) :  Rx.Observable<ICacheEntry>;
  set(key:string, value:ICacheEntry) : Rx.Observable<boolean>;
  clear() : Rx.Observable<boolean>;
}

export interface ICache {
  get<T>(key:string):Rx.Observable<T>;
  set(key:string, value:any):Rx.Observable<boolean>;
  clear():Rx.Observable<boolean>;
}
