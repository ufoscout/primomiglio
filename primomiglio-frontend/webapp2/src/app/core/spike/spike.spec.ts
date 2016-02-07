import {Subject, BehaviorSubject} from 'rxjs/Rx';
import {
  it,
  inject,
  injectAsync,
  describe,
  beforeEachProviders,
  TestComponentBuilder
} from 'angular2/testing';
import 'lodash';

declare var _: _.LoDashStatic;

(function() {
  'use strict';

  describe('spike', function() {

    it('Check BehaviourSubject', function () {
      var subj : Subject<{}> = new BehaviorSubject(undefined);
      var observable = subj.filter((value: any) : boolean => {return !_.isUndefined(value); });

      observable.subscribe((value : any) => {
        console.log('received: ' + value);
      });

      subj.next('two');

      console.log('done');
    });

  });

})();

