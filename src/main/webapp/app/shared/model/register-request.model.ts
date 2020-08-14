import { Moment } from 'moment';
import { ResquestStatus } from 'app/shared/model/enumerations/resquest-status.model';

export interface IRegisterRequest {
  id?: number;
  firstName?: string;
  lastName?: string;
  email?: string;
  reEnrollment?: boolean;
  phoneNumber?: string;
  whatsapp?: string;
  cellNumber?: string;
  emergencyNumber?: string;
  address?: string;
  workPlace?: string;
  workPhoneNumber?: string;
  acceptPaymnetDate?: boolean;
  attendMeetings?: boolean;
  paidOnTime?: boolean;
  suggestion?: string;
  createDate?: Moment;
  status?: ResquestStatus;
}

export class RegisterRequest implements IRegisterRequest {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public reEnrollment?: boolean,
    public phoneNumber?: string,
    public whatsapp?: string,
    public cellNumber?: string,
    public emergencyNumber?: string,
    public address?: string,
    public workPlace?: string,
    public workPhoneNumber?: string,
    public acceptPaymnetDate?: boolean,
    public attendMeetings?: boolean,
    public paidOnTime?: boolean,
    public suggestion?: string,
    public createDate?: Moment,
    public status?: ResquestStatus
  ) {
    this.reEnrollment = this.reEnrollment || false;
    this.acceptPaymnetDate = this.acceptPaymnetDate || false;
    this.attendMeetings = this.attendMeetings || false;
    this.paidOnTime = this.paidOnTime || false;
  }
}
