import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable()
export class StatusService {

  private url = environment.context + '/api/system/time';

  constructor(private httpClient: HttpClient) { }

  serverTimestamp(): Observable<any> {
    return this.httpClient.get(this.url);
  }

}
