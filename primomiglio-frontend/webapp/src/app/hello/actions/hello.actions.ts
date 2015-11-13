import {EventBus} from "../../core/eventBus/eventBus.service";

export class HelloActionsConst {
  public static SEND_HELLO:string = 'send_hello_action';
  public static REMOVE_ALL_HELLOS:string = 'remove_all_hellos_action';
}

export class HelloActions {

  private eventBus:EventBus;

  /* @ngInject */
  constructor(eventBus:EventBus) {
    this.eventBus = eventBus;
  }

  public sendHello(sayHelloTo:string) {
    this.eventBus.publish(HelloActionsConst.SEND_HELLO, sayHelloTo);
  }

  public removeAllHellos() {
    this.eventBus.publish(HelloActionsConst.REMOVE_ALL_HELLOS);
  }

}

