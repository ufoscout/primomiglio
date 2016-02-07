import {ReflectionUtils} from './reflectionUtils';

describe('core.utils:reflectionUtils', function () {

  it('should return the class name', function () {
    var hello = new Hello();
    expect( ReflectionUtils.getClassName(hello) ).toBe('Hello');
  });

  class Hello {};

});
