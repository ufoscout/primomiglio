import {NetworkService} from "../network/network.service";

export class Resource {
  public name:string = "";
  public url:string = ""
}

export class IResourceManager {

  /* @ngInject */
  constructor(private networkService:NetworkService, private resources:{ [s: string]: Resource; }) {
  }

  public get<T>(resourceType:Object):T {
    return null;
  }

}

export class ResourceManager implements angular.IServiceProvider {

  private resources:{ [s: string]: Resource; } = {};

  public addResource(resource:Resource) {
    this.resources[resource.name] = resource;
  }

  /* @ngInject */
  $get(networkService:NetworkService):IResourceManager {
    return new IResourceManager(networkService, this.resources);
  }

}
