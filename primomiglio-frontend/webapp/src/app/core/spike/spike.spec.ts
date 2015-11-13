/// <reference path="../../../../.tmp/typings/tsd.d.ts" />

(function() {
  'use strict';

  describe('spike', function() {

    it('Check BehaviourSubject', inject(function () {
      var subj : Rx.ISubject<{}> = new Rx.BehaviorSubject(undefined);
      var observable = subj.filter((value:any) : boolean => {return !_.isUndefined(value)});

      observable.subscribe((value : any) => {
        console.log('received: ' + value);
      });

      subj.onNext('two');

      console.log('done')
    }));

  })

})();

