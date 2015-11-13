	'use strict';

	export class EventBus {

		private events: { [key: string]: Rx.ISubject<{}> } = {};

		public publish(eventName: string, data: any = undefined): void {
			this.getOrCreateEvent(eventName).onNext(data);
		}

		public onEvent(eventName: string, $scope: angular.IScope = undefined, subscriber: (value: {}) => void = undefined): Rx.IObservable<{}> {
			var observable: Rx.IObservable<{}> = this.getOrCreateEvent(eventName);
			if (!_.isUndefined($scope) && !_.isUndefined(subscriber)) {
				var disposable: Rx.IDisposable = observable.subscribe(subscriber);
				$scope.$on('$destroy', () => {
					disposable.dispose();
				});
			}
			return observable;
		}

		private getOrCreateEvent(eventName: string): Rx.ISubject<{}> {
			var event: Rx.ISubject<{}> = this.events[eventName];
			if (_.isUndefined(event)) {
				this.events[eventName] = new Rx.Subject();
				event = this.events[eventName];
			}
			return event;
		}

	}
