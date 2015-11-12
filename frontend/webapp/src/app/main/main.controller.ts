import {main} from "../index.module";

'use strict';

export class MainController {

  public classAnimation:string;

  /* @ngInject */
  constructor($timeout:ng.ITimeoutService) {

    this.classAnimation = '';

    this.activate($timeout);
  }

  activate($timeout:ng.ITimeoutService) {

    var self = this;

    $timeout(function () {
      self.classAnimation = 'rubberBand';
    }, 4000);
  }

}
