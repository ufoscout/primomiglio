export class ArrayUtils {

  public static last<T>(array:Array<T>):T {
    if (!_.isUndefined(array) && !_.isEmpty(array)) {
      return array[array.length - 1];
    }
    return undefined;
  }

}
