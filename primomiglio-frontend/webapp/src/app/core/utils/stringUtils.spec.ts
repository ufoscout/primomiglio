import {StringUtils} from "./stringUtils"

'use strict';

describe('core.utils:stringUtils.service', function () {

  var stringUtils = StringUtils, template : string;

  it('should replace the placeholders with the passed values', function () {
    givenTemplate();
    thenExpectPlaceholdersInTemplateToBeReplacedWithCommaSeparatedValues();
    thenExpectPlaceholdersInTemplateToBeReplacedWithArrayOfValues();
  });

  it('should replace the placeholders with the passed values', function () {
    givenTheStringUtils();
    thenTemplateShouldBeReturnedWhenValuesAreUndefined();
    thenTemplateShouldBeReturnedWhenValuesAreEmpty();
    thenOnlySomePlaceholdersShouldBeReplacedIfNotAllAvailable();
    thenAllPlaceholdersShouldBeReplaced();
    thenNamedPlaceholdersShouldBeReplaced();
  });

  function givenTemplate() {
    template = 'Hello {0} ! I am testing with {1}';
  }

  function thenExpectPlaceholdersInTemplateToBeReplacedWithArrayOfValues() {
    var params : Array<string> = ['world', 'Jasmine'];
    var result = stringUtils.replacePlaceholders(template, params);
    expect(result).toBe('Hello world ! I am testing with Jasmine');
  }

  function thenExpectPlaceholdersInTemplateToBeReplacedWithCommaSeparatedValues() {
    var params = 'Francesco,passion';
    var result = stringUtils.replacePlaceholders(template, params);
    expect(result).toBe('Hello Francesco ! I am testing with passion');
  }

  function givenTheStringUtils() {
    expect(stringUtils).not.toBeUndefined();
  }

  function thenTemplateShouldBeReturnedWhenValuesAreUndefined() {
    expect(stringUtils.replacePlaceholders('hello', undefined)).toBe('hello');
  }

  function thenTemplateShouldBeReturnedWhenValuesAreEmpty() {
    expect(stringUtils.replacePlaceholders('hello', [])).toBe('hello');
  }

  function thenOnlySomePlaceholdersShouldBeReplacedIfNotAllAvailable() {
    expect(stringUtils.replacePlaceholders('hello {0} {1}', [])).toBe('hello {0} {1}');
    expect(stringUtils.replacePlaceholders('hello {0} {1}', 'francesco')).toBe('hello francesco {1}');
  }

  function thenAllPlaceholdersShouldBeReplaced() {
    expect(stringUtils.replacePlaceholders('hello {0} {1}', ['francesco','cina'])).toBe('hello francesco cina');
    expect(stringUtils.replacePlaceholders('hello {0} {1}', 'francesco,cina')).toBe('hello francesco cina');
  }

  function thenNamedPlaceholdersShouldBeReplaced() {
    var values : { [key: string] : string} = {firstname: 'Francesco', lastname: 'Cina', salutation:'Mr.'};
    expect(stringUtils.replacePlaceholders('hello {salutation} {firstname} {lastname}', values ))
      .toBe('hello Mr. Francesco Cina');
  }

});
