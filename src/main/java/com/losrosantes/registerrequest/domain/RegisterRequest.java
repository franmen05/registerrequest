package com.losrosantes.registerrequest.domain;

import com.losrosantes.registerrequest.domain.enumeration.ResquestStatus;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * The Student entity.
 */
@Entity
@Table(name = "register_request")
public class RegisterRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The firstname attribute.
     */
    @NotEmpty(message = "Nombre no puede estar vacio")
    @NotNull(message = "Apellido no puede estar null")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotEmpty(message = "Apellido no puede estar vacio")
    @NotNull(message = "Apellido no puede estar null")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotEmpty(message = "Email no puede estar vacio")
    @Email(message = "No es un email valido")
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "re_enrollment", columnDefinition = "boolean default true")
    private Boolean reEnrollment;

    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull(message = "WhatsApp no puede estar vacio")
    @Column(name = "whatsapp")
    private String whatsapp;

    @NotEmpty(message = "Celular no puede estar vacio")
    @NotNull(message = "Celular no puede estar null")
    @Column(name = "cell_number")
    private String cellNumber;

    @Column(name = "emergency_number")
    private String emergencyNumber;

    @NotEmpty(message = "Direccion no puede estar vacio")
    @NotNull(message = "Direccion no puede estar null")
    @Column(name = "address", nullable = false)
    private String address;

    @NotEmpty(message = "Lugar de trabajo no puede estar vacio")
    @NotNull(message = "Lugar de trabajo no puede estar null")
    @Column(name = "work_place", nullable = false)
    private String workPlace;

    @NotEmpty(message = "Telefono de trabajo no puede estar vacio")
    @Column(name = "work_phone_number", nullable = false)
    private String workPhoneNumber;

    @NotNull(message = "Fecha de Pago no puede estar vacio")
    @Column(name = "accept_paymnet_date", nullable = false)
    private Boolean acceptPaymnetDate;

    @NotNull
    @Column(name = "attend_meetings", nullable = false)
    private Boolean attendMeetings;

    @NotNull
    @Column(name = "paid_on_time", nullable = false)
    private Boolean paidOnTime;

    @Column(name = "suggestion")
    private String suggestion;

    //    @NotNull
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    //    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ResquestStatus status;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public RegisterRequest firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public RegisterRequest lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public RegisterRequest email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isReEnrollment() {
        return reEnrollment;
    }

    public RegisterRequest reEnrollment(Boolean reEnrollment) {
        this.reEnrollment = reEnrollment;
        return this;
    }

    public void setReEnrollment(Boolean reEnrollment) {
        this.reEnrollment = reEnrollment;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public RegisterRequest phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public RegisterRequest whatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
        return this;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public RegisterRequest cellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
        return this;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getEmergencyNumber() {
        return emergencyNumber;
    }

    public RegisterRequest emergencyNumber(String emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
        return this;
    }

    public void setEmergencyNumber(String emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
    }

    public String getAddress() {
        return address;
    }

    public RegisterRequest address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public RegisterRequest workPlace(String workPlace) {
        this.workPlace = workPlace;
        return this;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getWorkPhoneNumber() {
        return workPhoneNumber;
    }

    public RegisterRequest workPhoneNumber(String workPhoneNumber) {
        this.workPhoneNumber = workPhoneNumber;
        return this;
    }

    public void setWorkPhoneNumber(String workPhoneNumber) {
        this.workPhoneNumber = workPhoneNumber;
    }

    public Boolean isAcceptPaymnetDate() {
        return acceptPaymnetDate;
    }

    public RegisterRequest acceptPaymnetDate(Boolean acceptPaymnetDate) {
        this.acceptPaymnetDate = acceptPaymnetDate;
        return this;
    }

    public void setAcceptPaymnetDate(Boolean acceptPaymnetDate) {
        this.acceptPaymnetDate = acceptPaymnetDate;
    }

    public Boolean isAttendMeetings() {
        return attendMeetings;
    }

    public RegisterRequest attendMeetings(Boolean attendMeetings) {
        this.attendMeetings = attendMeetings;
        return this;
    }

    public void setAttendMeetings(Boolean attendMeetings) {
        this.attendMeetings = attendMeetings;
    }

    public Boolean isPaidOnTime() {
        return paidOnTime;
    }

    public RegisterRequest paidOnTime(Boolean paidOnTime) {
        this.paidOnTime = paidOnTime;
        return this;
    }

    public void setPaidOnTime(Boolean paidOnTime) {
        this.paidOnTime = paidOnTime;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public RegisterRequest suggestion(String suggestion) {
        this.suggestion = suggestion;
        return this;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public RegisterRequest createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public ResquestStatus getStatus() {
        return status;
    }

    public RegisterRequest status(ResquestStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(ResquestStatus status) {
        this.status = status;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RegisterRequest)) {
            return false;
        }
        return id != null && id.equals(((RegisterRequest) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RegisterRequest{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", reEnrollment='" + isReEnrollment() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", whatsapp='" + getWhatsapp() + "'" +
            ", cellNumber='" + getCellNumber() + "'" +
            ", emergencyNumber='" + getEmergencyNumber() + "'" +
            ", address='" + getAddress() + "'" +
            ", workPlace='" + getWorkPlace() + "'" +
            ", workPhoneNumber='" + getWorkPhoneNumber() + "'" +
            ", acceptPaymnetDate='" + isAcceptPaymnetDate() + "'" +
            ", attendMeetings='" + isAttendMeetings() + "'" +
            ", paidOnTime='" + isPaidOnTime() + "'" +
            ", suggestion='" + getSuggestion() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
