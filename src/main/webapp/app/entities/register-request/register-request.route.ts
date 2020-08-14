import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRegisterRequest, RegisterRequest } from 'app/shared/model/register-request.model';
import { RegisterRequestService } from './register-request.service';
import { RegisterRequestComponent } from './register-request.component';
import { RegisterRequestDetailComponent } from './register-request-detail.component';
import { RegisterRequestUpdateComponent } from './register-request-update.component';

@Injectable({ providedIn: 'root' })
export class RegisterRequestResolve implements Resolve<IRegisterRequest> {
  constructor(private service: RegisterRequestService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRegisterRequest> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((registerRequest: HttpResponse<RegisterRequest>) => {
          if (registerRequest.body) {
            return of(registerRequest.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RegisterRequest());
  }
}

export const registerRequestRoute: Routes = [
  {
    path: '',
    component: RegisterRequestComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'RegisterRequests',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RegisterRequestDetailComponent,
    resolve: {
      registerRequest: RegisterRequestResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'RegisterRequests',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RegisterRequestUpdateComponent,
    resolve: {
      registerRequest: RegisterRequestResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'RegisterRequests',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RegisterRequestUpdateComponent,
    resolve: {
      registerRequest: RegisterRequestResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'RegisterRequests',
    },
    canActivate: [UserRouteAccessService],
  },
];
