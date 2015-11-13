/// <reference path="../../../../.tmp/typings/tsd.d.ts" />

export class RxUtils {

  public static toArrayOfResult<T>(observable:Rx.IObservable<T>):Array<T> {
    var result:Array<T> = [];
    observable.subscribe((value:T) => {
      result.push(value);
    });
    return result;
  }

}
