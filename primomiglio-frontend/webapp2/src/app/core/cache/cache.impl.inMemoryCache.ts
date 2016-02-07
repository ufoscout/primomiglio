import {Observable, BehaviorSubject} from 'rxjs/Rx';
import {IStorage, ICacheEntry} from './cache';

export class VolatileStorage implements IStorage {

  private cache: { [key: string]: ICacheEntry } = {};

  public get(key: string): Observable<ICacheEntry> {
    return new BehaviorSubject(this.cache[key]);
  }

  public set(key: string, value: ICacheEntry): Observable<boolean> {
    this.cache[key] = value;
    return new BehaviorSubject(true);
  }

  public clear(): Observable<boolean> {
    this.cache = {};
    return new BehaviorSubject(true);
  }

}
