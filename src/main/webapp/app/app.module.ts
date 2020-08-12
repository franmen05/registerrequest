import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { RegisterRequestSharedModule } from 'app/shared/shared.module';
import { RegisterRequestCoreModule } from 'app/core/core.module';
import { RegisterRequestAppRoutingModule } from './app-routing.module';
import { RegisterRequestHomeModule } from './home/home.module';
import { RegisterRequestEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    RegisterRequestSharedModule,
    RegisterRequestCoreModule,
    RegisterRequestHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    RegisterRequestEntityModule,
    RegisterRequestAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class RegisterRequestAppModule {}
