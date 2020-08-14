import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRegisterRequest } from 'app/shared/model/register-request.model';

@Component({
  selector: 'jhi-register-request-detail',
  templateUrl: './register-request-detail.component.html',
})
export class RegisterRequestDetailComponent implements OnInit {
  registerRequest: IRegisterRequest | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ registerRequest }) => (this.registerRequest = registerRequest));
  }

  previousState(): void {
    window.history.back();
  }
}
