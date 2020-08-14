import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRegisterRequest } from 'app/shared/model/register-request.model';

type EntityResponseType = HttpResponse<IRegisterRequest>;
type EntityArrayResponseType = HttpResponse<IRegisterRequest[]>;

@Injectable({ providedIn: 'root' })
export class RegisterRequestService {
  public resourceUrl = SERVER_API_URL + 'api/register-requests';

  constructor(protected http: HttpClient) {}

  create(registerRequest: IRegisterRequest): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(registerRequest);
    return this.http
      .post<IRegisterRequest>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(registerRequest: IRegisterRequest): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(registerRequest);
    return this.http
      .put<IRegisterRequest>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRegisterRequest>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRegisterRequest[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(registerRequest: IRegisterRequest): IRegisterRequest {
    const copy: IRegisterRequest = Object.assign({}, registerRequest, {
      createDate: registerRequest.createDate && registerRequest.createDate.isValid() ? registerRequest.createDate.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createDate = res.body.createDate ? moment(res.body.createDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((registerRequest: IRegisterRequest) => {
        registerRequest.createDate = registerRequest.createDate ? moment(registerRequest.createDate) : undefined;
      });
    }
    return res;
  }
}
