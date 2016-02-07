import {Injectable} from 'angular2/core';
import {NetworkService} from '../network/network.service';

export class Resource {
  public id: string = '';
  public url: string = '';
}

@Injectable({
  providers: [ NetworkService ]
})
export class ResourceManager {

  private resources: { [s: string]: Resource; } = {};

  constructor(private networkService: NetworkService) {
  }

  public add(resource: Resource) : void {
    this.resources[resource.id] = resource;
  }

  public get(id: string): Resource {
    return this.resources[id];
  }

}
