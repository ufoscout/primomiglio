import {core} from "../core.module";
import {EventBus} from "./eventBus.service";

describe('core.eventBus:eventBus.service', function () {

  var eventBus:EventBus, scope:angular.IScope;

  beforeEach(angular.mock.module(core.MODULE_NAME));

  beforeEach(inject(function (_$rootScope_:angular.IScope, _eventBus_:EventBus) {
    eventBus = _eventBus_;
    scope = _$rootScope_.$new();
  }));

  it('EventBusService should be defined', inject(function () {
    eventBusShouldBeDefined();
  }));

  it('Should be possible to send and receive an event', inject(function () {
    var eventName:string = 'event' + new Date().getMilliseconds();
    var eventData:any = {};
    var received:boolean = false;

    eventBus.onEvent(eventName).subscribe((value:any) => {
      received = true;
      expect(value).toBe(eventData);
    });
    eventBus.publish(eventName, eventData);

    expect(received).toBeTruthy();

  }));

  it('Should be possible to send and receive an event without data', inject(function () {
    var eventName:string = 'event' + new Date().getMilliseconds();
    var received:boolean = false;

    eventBus.onEvent(eventName).subscribe((value:any) => {
      received = true;
      expect(value).toBeUndefined();
    });
    eventBus.publish(eventName);

    expect(received).toBeTruthy();

  }));

  it('Should be possible to receive multiple events', inject(function () {
    var eventName:string = 'event' + new Date().getMilliseconds();
    var eventData1:any = {};
    var received1:boolean = false;

    var eventData2:any = {};
    var received2:boolean = false;

    eventBus.onEvent(eventName).subscribe((value:any) => {
      if (_.isEqual(eventData1, value)) {
        received1 = true;
      }
      ;
      if (_.isEqual(eventData2, value)) {
        received2 = true;
      }
      ;
    });

    eventBus.publish(eventName, eventData2);
    eventBus.publish(eventName, eventData1);

    expect(received1).toBeTruthy();
    expect(received2).toBeTruthy();

  }));

  it('Should be possible to send and receive an event with different names', inject(function () {
    var eventName1:string = 'event1' + new Date().getMilliseconds();
    var eventData1:any = {};
    var received1:boolean = false;

    var eventName2:string = 'event2' + new Date().getMilliseconds();
    var eventData2:any = {};
    var received2:boolean = false;

    eventBus.onEvent(eventName1).subscribe((value:any) => {
      received1 = true;
      expect(value).toBe(eventData1);
    });

    eventBus.onEvent(eventName2).subscribe((value:any) => {
      received2 = true;
      expect(value).toBe(eventData2);
    });

    eventBus.publish(eventName2, eventData2);
    eventBus.publish(eventName1, eventData1);

    expect(received1).toBeTruthy();
    expect(received2).toBeTruthy();

  }));

  it('Should dispose subscription on $scope $destroy event', inject(function () {
    var eventName:string = 'event' + new Date().getMilliseconds();
    var eventData:any = {};
    var received:boolean = false;

    eventBus.onEvent(eventName, scope, (value:any) => {
      received = true;
      expect(value).toBe(eventData);
    });

    eventBus.publish(eventName, eventData);
    expect(received).toBeTruthy();

    received = false;
    scope.$destroy();
    scope.$digest();

    eventBus.publish(eventName, eventData);
    expect(received).toBeFalsy();

  }));

  function eventBusShouldBeDefined() {
    expect(eventBus).toBeDefined();
  }

});
