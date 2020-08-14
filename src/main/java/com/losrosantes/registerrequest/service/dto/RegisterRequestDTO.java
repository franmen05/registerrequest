package com.losrosantes.registerrequest.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.losrosantes.registerrequest.domain.enumeration.ResquestStatus;

/**
 * A DTO for the {@link com.losrosantes.registerrequest.domain.RegisterRequest} entity.
 */
@ApiModel(description = "The Student entity.")
public class RegisterRequestDTO implements Serializable {
    
    private Long id;

    /**
     * The firstname attribute.
     */
    @NotNull
    @ApiModelProperty(value = "The firstname attribute.", required = true)
    private String firstName;

    @NotNull
    private String lastName;

    
    private String email;

    private Boolean reEnrollment;

    private String phoneNumber;

    private String whatsapp;

    private String cellNumber;

    private String emergencyNumber;

    @NotNull
    private String address;

    @NotNull
    private String workPlace;

    @NotNull
    private String workPhoneNumber;

    @NotNull
    private Boolean acceptPaymnetDate;

    @NotNull
    private Boolean attendMeetings;

    @NotNull
    private Boolean paidOnTime;

    private String suggestion;

    @NotNull
    private Instant createDate;

    @NotNull
    private ResquestStatus status;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isReEnrollment() {
        return reEnrollment;
    }

    public void setReEnrollment(Boolean reEnrollment) {
        this.reEnrollment = reEnrollment;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getEmergencyNumber() {
        return emergencyNumber;
    }

    public void setEmergencyNumber(String emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getWorkPhoneNumber() {
        return workPhoneNumber;
    }

    public void setWorkPhoneNumber(String workPhoneNumber) {
        this.workPhoneNumber = workPhoneNumber;
    }

    public Boolean isAcceptPaymnetDate() {
        return acceptPaymnetDate;
    }

    public void setAcceptPaymnetDate(Boolean acceptPaymnetDate) {
        this.acceptPaymnetDate = acceptPaymnetDate;
    }

    public Boolean isAttendMeetings() {
        return attendMeetings;
    }

    public void setAttendMeetings(Boolean attendMeetings) {
        this.attendMeetings = attendMeetings;
    }

    public Boolean isPaidOnTime() {
        return paidOnTime;
    }

    public void setPaidOnTime(Boolean paidOnTime) {
        this.paidOnTime = paidOnTime;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public ResquestStatus getStatus() {
        return status;
    }

    public void setStatus(ResquestStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RegisterRequestDTO)) {
            return false;
        }

        return id != null && id.equals(((RegisterRequestDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RegisterRequestDTO{" +
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
