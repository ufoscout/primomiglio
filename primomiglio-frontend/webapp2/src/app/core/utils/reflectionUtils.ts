export class ReflectionUtils {

  public static getClassName(inputObject): string {
    var funcNameRegex = /function (.{1,})\(/;
    var results = (funcNameRegex).exec((<any> inputObject).constructor.toString());
    return (results && results.length > 1) ? results[1] : '';
  }

}
