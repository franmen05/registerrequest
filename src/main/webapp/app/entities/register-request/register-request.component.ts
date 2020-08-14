import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRegisterRequest } from 'app/shared/model/register-request.model';
import { RegisterRequestService } from './register-request.service';
import { RegisterRequestDeleteDialogComponent } from './register-request-delete-dialog.component';

@Component({
  selector: 'jhi-register-request',
  templateUrl: './register-request.component.html',
})
export class RegisterRequestComponent implements OnInit, OnDestroy {
  registerRequests?: IRegisterRequest[];
  eventSubscriber?: Subscription;

  constructor(
    protected registerRequestService: RegisterRequestService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.registerRequestService.query().subscribe((res: HttpResponse<IRegisterRequest[]>) => (this.registerRequests = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInRegisterRequests();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRegisterRequest): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRegisterRequests(): void {
    this.eventSubscriber = this.eventManager.subscribe('registerRequestListModification', () => this.loadAll());
  }

  delete(registerRequest: IRegisterRequest): void {
    const modalRef = this.modalService.open(RegisterRequestDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.registerRequest = registerRequest;
  }
}
