import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { RegisterRequestService } from 'app/entities/register-request/register-request.service';
import { IRegisterRequest, RegisterRequest } from 'app/shared/model/register-request.model';
import { ResquestStatus } from 'app/shared/model/enumerations/resquest-status.model';

describe('Service Tests', () => {
  describe('RegisterRequest Service', () => {
    let injector: TestBed;
    let service: RegisterRequestService;
    let httpMock: HttpTestingController;
    let elemDefault: IRegisterRequest;
    let expectedResult: IRegisterRequest | IRegisterRequest[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(RegisterRequestService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new RegisterRequest(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        false,
        false,
        'AAAAAAA',
        currentDate,
        ResquestStatus.SUBMIT
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            createDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a RegisterRequest', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            createDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createDate: currentDate,
          },
          returnedFromService
        );

        service.create(new RegisterRequest()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a RegisterRequest', () => {
        const returnedFromService = Object.assign(
          {
            firstName: 'BBBBBB',
            lastName: 'BBBBBB',
            email: 'BBBBBB',
            reEnrollment: true,
            phoneNumber: 'BBBBBB',
            whatsapp: 'BBBBBB',
            cellNumber: 'BBBBBB',
            emergencyNumber: 'BBBBBB',
            address: 'BBBBBB',
            workPlace: 'BBBBBB',
            workPhoneNumber: 'BBBBBB',
            acceptPaymnetDate: true,
            attendMeetings: true,
            paidOnTime: true,
            suggestion: 'BBBBBB',
            createDate: currentDate.format(DATE_TIME_FORMAT),
            status: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of RegisterRequest', () => {
        const returnedFromService = Object.assign(
          {
            firstName: 'BBBBBB',
            lastName: 'BBBBBB',
            email: 'BBBBBB',
            reEnrollment: true,
            phoneNumber: 'BBBBBB',
            whatsapp: 'BBBBBB',
            cellNumber: 'BBBBBB',
            emergencyNumber: 'BBBBBB',
            address: 'BBBBBB',
            workPlace: 'BBBBBB',
            workPhoneNumber: 'BBBBBB',
            acceptPaymnetDate: true,
            attendMeetings: true,
            paidOnTime: true,
            suggestion: 'BBBBBB',
            createDate: currentDate.format(DATE_TIME_FORMAT),
            status: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a RegisterRequest', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
