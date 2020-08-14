import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RegisterRequestSharedModule } from 'app/shared/shared.module';
import { RegisterRequestComponent } from './register-request.component';
import { RegisterRequestDetailComponent } from './register-request-detail.component';
import { RegisterRequestUpdateComponent } from './register-request-update.component';
import { RegisterRequestDeleteDialogComponent } from './register-request-delete-dialog.component';
import { registerRequestRoute } from './register-request.route';

@NgModule({
  imports: [RegisterRequestSharedModule, RouterModule.forChild(registerRequestRoute)],
  declarations: [
    RegisterRequestComponent,
    RegisterRequestDetailComponent,
    RegisterRequestUpdateComponent,
    RegisterRequestDeleteDialogComponent,
  ],
  entryComponents: [RegisterRequestDeleteDialogComponent],
})
export class RegisterRequestRegisterRequestModule {}
