import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RegisterRequestTestModule } from '../../../test.module';
import { RegisterRequestComponent } from 'app/entities/register-request/register-request.component';
import { RegisterRequestService } from 'app/entities/register-request/register-request.service';
import { RegisterRequest } from 'app/shared/model/register-request.model';

describe('Component Tests', () => {
  describe('RegisterRequest Management Component', () => {
    let comp: RegisterRequestComponent;
    let fixture: ComponentFixture<RegisterRequestComponent>;
    let service: RegisterRequestService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RegisterRequestTestModule],
        declarations: [RegisterRequestComponent],
      })
        .overrideTemplate(RegisterRequestComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RegisterRequestComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RegisterRequestService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new RegisterRequest(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.registerRequests && comp.registerRequests[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
