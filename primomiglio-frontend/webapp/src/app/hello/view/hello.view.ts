import {HelloActions} from "../actions/hello.actions";
import {HelloStore} from "../store/hello.store";
import {IHello} from "../store/hello.store";

class HelloDirectiveController {

  public classAnimation:string = '';
  public helloTo:string = 'Francesco';
  public hellos:Array<IHello> = [];

  private helloStore:HelloStore;
  private helloActions:HelloActions;

  private activate = ($timeout:ng.ITimeoutService) => {
    var self = this;
    $timeout(function () {
      self.classAnimation = 'rubberBand';
    }, 4000);
  }

  /* @ngInject */
  constructor($timeout:ng.ITimeoutService, helloStore:HelloStore, helloActions:HelloActions, public $scope:IFooDirectiveScope) {
    $scope.vm = this;
    this.activate($timeout);
    this.helloStore = helloStore;
    this.helloActions = helloActions;

    // console.log('form controller');

    this.helloStore.getHellos().subscribe((hellos:Array<IHello>) => {
      this.hellos = hellos;
    });
  }

  public sayHello():void {
    console.log('said hello to ' + this.helloTo);
    this.helloActions.sendHello(this.helloTo);
  }

}

function helloDirectiveLink(scope:IFooDirectiveScope, element:ng.IAugmentedJQuery, attributes:ng.IAttributes,
                            controller:HelloDirectiveController, transclude:angular.ITranscludeFunction):void {
  // console.log('form link!');
}

export interface IFooDirectiveScope extends ng.IScope {
  bar:  string;

  // local design only
  vm: HelloDirectiveController;
}

export function helloDirective(): angular.IDirective {
  return {
    restrict: 'AE',
//      scope:  {
//        bar:  '='
//      },
    // template:  'HELLO DIRECTIVE',
    templateUrl: 'app/hello/view/hello.view.html',
    controller: HelloDirectiveController,
    link: helloDirectiveLink
  };
}
