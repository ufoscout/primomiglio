/// <reference path="../../../../.tmp/typings/tsd.d.ts" />
import {StringUtils} from "./stringUtils";
import {ReflectionUtils} from "./reflectionUtils";

'use strict';

describe('core.utils:reflectionUtils', function () {

  var stringUtils = StringUtils, template : string;

  it('should return the class name', function () {
    var hello = new Hello();
    expect( ReflectionUtils.getClassName(hello) ).toBe("Hello");

  });

  class Hello {};

});
