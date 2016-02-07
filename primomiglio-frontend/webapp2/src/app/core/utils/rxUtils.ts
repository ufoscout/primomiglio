import {Observable} from 'rxjs/Rx';

export class RxUtils {

  public static toArrayOfResult<T>(observable:Observable<T>):Array<T> {
    var result:Array<T> = [];
    observable.subscribe((value:T) => {
      result.push(value);
    });
    return result;
  }

}
