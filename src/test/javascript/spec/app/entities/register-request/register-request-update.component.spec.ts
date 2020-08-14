import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RegisterRequestTestModule } from '../../../test.module';
import { RegisterRequestUpdateComponent } from 'app/entities/register-request/register-request-update.component';
import { RegisterRequestService } from 'app/entities/register-request/register-request.service';
import { RegisterRequest } from 'app/shared/model/register-request.model';

describe('Component Tests', () => {
  describe('RegisterRequest Management Update Component', () => {
    let comp: RegisterRequestUpdateComponent;
    let fixture: ComponentFixture<RegisterRequestUpdateComponent>;
    let service: RegisterRequestService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RegisterRequestTestModule],
        declarations: [RegisterRequestUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RegisterRequestUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RegisterRequestUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RegisterRequestService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RegisterRequest(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new RegisterRequest();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
