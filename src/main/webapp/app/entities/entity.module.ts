import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'register-request',
        loadChildren: () => import('./register-request/register-request.module').then(m => m.RegisterRequestRegisterRequestModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class RegisterRequestEntityModule {}
