import 'lodash';

declare var _: _.LoDashStatic;

export class StringUtils {

  public static replacePlaceholders = (template: string, values: string | Array<string> | { [key: string] : string|number}): string => {
    if (!_.isUndefined(template) && !_.isUndefined(values)) {
      if (_.isString(values)) {
        values = (<string> values).split(',');
      }
      _.forEach(values, function (value: any, index: number|string) {
        var placeHolder = '{' + index + '}';
        template = template.replace(placeHolder, value);
      });
    }
    return template;
  };

}
