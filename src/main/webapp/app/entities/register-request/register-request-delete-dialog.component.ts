import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRegisterRequest } from 'app/shared/model/register-request.model';
import { RegisterRequestService } from './register-request.service';

@Component({
  templateUrl: './register-request-delete-dialog.component.html',
})
export class RegisterRequestDeleteDialogComponent {
  registerRequest?: IRegisterRequest;

  constructor(
    protected registerRequestService: RegisterRequestService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.registerRequestService.delete(id).subscribe(() => {
      this.eventManager.broadcast('registerRequestListModification');
      this.activeModal.close();
    });
  }
}
