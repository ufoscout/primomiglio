import {EventBus} from "../../core/eventBus/eventBus.service";
import {HelloActionsConst} from "../actions/hello.actions";

export interface IHello {
  to : string;
  date : Date
}

export class HelloStore {

  private hellos:Array<IHello> = [];
  private hellosSubject:Rx.ISubject<Array<IHello>> = new Rx.BehaviorSubject(this.hellos);
  private eventBus:EventBus;

  private subscribeSendHello = (sayHelloTo:string) => {
    if (!_.isUndefined(sayHelloTo)) {
      this.hellos.push({
        to: sayHelloTo,
        date: new Date()
      });
      this.notify();
    }
  }

  private subscribeRemoveAllHellos = () => {
    this.hellos = [];
    this.notify();
  }

  private notify = () => {
    this.hellosSubject.onNext(this.hellos);
  }

  /* @ngInject */
  constructor(eventBus:EventBus) {
    this.eventBus = eventBus;

    eventBus.onEvent(HelloActionsConst.SEND_HELLO).subscribe(this.subscribeSendHello);
    eventBus.onEvent(HelloActionsConst.REMOVE_ALL_HELLOS).subscribe(this.subscribeRemoveAllHellos);
  }

  public getHellos(scope:angular.IScope = undefined, subscriber:(value:Array<IHello>) => void = undefined):Rx.IObservable<Array<IHello>> {
    var observable:Rx.IObservable<Array<IHello>> = this.hellosSubject.asObservable();
    if (!_.isUndefined(scope) && !_.isUndefined(subscriber)) {
      var disposable:Rx.IDisposable = observable.subscribe(subscriber);
      scope.$on('$destroy', () => {
        disposable.dispose();
      });
    }
    return observable;
  }

}
