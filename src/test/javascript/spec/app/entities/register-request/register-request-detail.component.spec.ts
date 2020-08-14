import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RegisterRequestTestModule } from '../../../test.module';
import { RegisterRequestDetailComponent } from 'app/entities/register-request/register-request-detail.component';
import { RegisterRequest } from 'app/shared/model/register-request.model';

describe('Component Tests', () => {
  describe('RegisterRequest Management Detail Component', () => {
    let comp: RegisterRequestDetailComponent;
    let fixture: ComponentFixture<RegisterRequestDetailComponent>;
    const route = ({ data: of({ registerRequest: new RegisterRequest(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RegisterRequestTestModule],
        declarations: [RegisterRequestDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RegisterRequestDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RegisterRequestDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load registerRequest on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.registerRequest).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
