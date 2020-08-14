import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRegisterRequest, RegisterRequest } from 'app/shared/model/register-request.model';
import { RegisterRequestService } from './register-request.service';

@Component({
  selector: 'jhi-register-request-update',
  templateUrl: './register-request-update.component.html',
})
export class RegisterRequestUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    firstName: [null, [Validators.required]],
    lastName: [null, [Validators.required]],
    email: [null, []],
    reEnrollment: [null, []],
    phoneNumber: [],
    whatsapp: [],
    cellNumber: [],
    emergencyNumber: [],
    address: [null, [Validators.required]],
    workPlace: [null, [Validators.required]],
    workPhoneNumber: [null, [Validators.required]],
    acceptPaymnetDate: [null, [Validators.required]],
    attendMeetings: [null, [Validators.required]],
    paidOnTime: [null, [Validators.required]],
    suggestion: [],
    createDate: [null, [Validators.required]],
    status: [null, [Validators.required]],
  });

  constructor(
    protected registerRequestService: RegisterRequestService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ registerRequest }) => {
      if (!registerRequest.id) {
        const today = moment().startOf('day');
        registerRequest.createDate = today;
      }

      this.updateForm(registerRequest);
    });
  }

  updateForm(registerRequest: IRegisterRequest): void {
    this.editForm.patchValue({
      id: registerRequest.id,
      firstName: registerRequest.firstName,
      lastName: registerRequest.lastName,
      email: registerRequest.email,
      reEnrollment: registerRequest.reEnrollment,
      phoneNumber: registerRequest.phoneNumber,
      whatsapp: registerRequest.whatsapp,
      cellNumber: registerRequest.cellNumber,
      emergencyNumber: registerRequest.emergencyNumber,
      address: registerRequest.address,
      workPlace: registerRequest.workPlace,
      workPhoneNumber: registerRequest.workPhoneNumber,
      acceptPaymnetDate: registerRequest.acceptPaymnetDate,
      attendMeetings: registerRequest.attendMeetings,
      paidOnTime: registerRequest.paidOnTime,
      suggestion: registerRequest.suggestion,
      createDate: registerRequest.createDate ? registerRequest.createDate.format(DATE_TIME_FORMAT) : null,
      status: registerRequest.status,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const registerRequest = this.createFromForm();
    if (registerRequest.id !== undefined) {
      this.subscribeToSaveResponse(this.registerRequestService.update(registerRequest));
    } else {
      this.subscribeToSaveResponse(this.registerRequestService.create(registerRequest));
    }
  }

  private createFromForm(): IRegisterRequest {
    return {
      ...new RegisterRequest(),
      id: this.editForm.get(['id'])!.value,
      firstName: this.editForm.get(['firstName'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      email: this.editForm.get(['email'])!.value,
      reEnrollment: this.editForm.get(['reEnrollment'])!.value,
      phoneNumber: this.editForm.get(['phoneNumber'])!.value,
      whatsapp: this.editForm.get(['whatsapp'])!.value,
      cellNumber: this.editForm.get(['cellNumber'])!.value,
      emergencyNumber: this.editForm.get(['emergencyNumber'])!.value,
      address: this.editForm.get(['address'])!.value,
      workPlace: this.editForm.get(['workPlace'])!.value,
      workPhoneNumber: this.editForm.get(['workPhoneNumber'])!.value,
      acceptPaymnetDate: this.editForm.get(['acceptPaymnetDate'])!.value,
      attendMeetings: this.editForm.get(['attendMeetings'])!.value,
      paidOnTime: this.editForm.get(['paidOnTime'])!.value,
      suggestion: this.editForm.get(['suggestion'])!.value,
      createDate: this.editForm.get(['createDate'])!.value ? moment(this.editForm.get(['createDate'])!.value, DATE_TIME_FORMAT) : undefined,
      status: this.editForm.get(['status'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRegisterRequest>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
