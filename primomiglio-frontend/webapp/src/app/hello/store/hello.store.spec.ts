import {hello} from "../hello.module";
import {HelloStore} from "./hello.store";
import {HelloActions} from "../actions/hello.actions";
import {IHello} from "./hello.store";
import {ArrayUtils} from "../../core/utils/arrayUtils";

(function() {
  'use strict';

  describe('hello.store.hello', function(){

    var helloStore : HelloStore, helloActions : HelloActions, hellos: Array<IHello>;

    beforeEach(angular.mock.module(hello.MODULE_NAME));

    beforeEach(inject(function (_helloStore_ : HelloStore, _helloActions_ : HelloActions) {
      helloStore = _helloStore_;
      helloActions = _helloActions_;
      hellos = [];
    }));

    it('HelloStore should be defined', inject(function() {
      expect(helloStore).toBeDefined();
    }));

    it('should be possible to get hellos', inject(function() {
      expect(helloStore.getHellos()).toBeDefined();
    }));

    it('should be possible to add hellos', inject(function() {
      whenSubscriberIsRegistered();
      var size : number = hellos.length;
      whenSendingANewHello('Francesco');
      thenThereShouldBeHellos(size+1);
    }));

    it('should be possible to remove all hellos', inject(function() {
      whenSubscriberIsRegistered();
      whenSendingANewHello('Francesco');
      whenSendingANewHello('Tomas');
      thenThereShouldBeHellos();
      whenRemovingAllHellos();
      thenThereShouldBeHellos(0);

    }));

    it('should be possible to subscribe and receive the current value', inject(function() {
      whenSendingANewHello('Francesco');
      whenSendingANewHello('Tomas');
      whenSubscriberIsRegistered();
      thenHelloIsEqualTo('Tomas');
    }));

    it('should be possible to subscribe and receive the new values', inject(function() {
      whenSubscriberIsRegistered();
      thenHelloIsUndefined();
      whenSendingANewHello('Francesco');
      thenHelloIsEqualTo('Francesco');
      whenSendingANewHello('Tomas');
      thenHelloIsEqualTo('Tomas');
    }));

    function whenSendingANewHello(name : string) {
      helloActions.sendHello(name);
    }

    function whenRemovingAllHellos() {
      helloActions.removeAllHellos();
    }

    function whenSubscriberIsRegistered() {
      helloStore.getHellos().subscribe((value : Array<IHello>) => {
        hellos = value;
        //console.log('received');
        //console.log(value);
      });
    }

    function thenHelloIsUndefined() {
      expect(ArrayUtils.last(hellos)).toBeUndefined();
    }

    function thenHelloIsEqualTo(value: string) {
      expect(ArrayUtils.last(hellos).to).toEqual(value);
    }

    function thenThereShouldBeHellos(howMany : number = undefined) {
      if (_.isUndefined(howMany)) {
        expect(hellos.length > 0).toBeTruthy();
      } else {
        expect(hellos.length).toBe(howMany);
      }
    }

  });

})();

