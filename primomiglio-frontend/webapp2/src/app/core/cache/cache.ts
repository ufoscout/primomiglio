import {Observable} from 'rxjs/Rx';

export interface ICacheEntry {
  value: any;
  date: Date
}

export interface IStorage {
  get(key:string) :  Observable<ICacheEntry>;
  set(key:string, value:ICacheEntry) : Observable<boolean>;
  clear() : Observable<boolean>;
}

export interface ICache {
  get<T>(key:string):Observable<T>;
  set(key:string, value:any):Observable<boolean>;
  clear(): Observable<boolean>;
}
