package com.losrosantes.registerrequest.web.rest;

import com.losrosantes.registerrequest.RegisterRequestApp;
import com.losrosantes.registerrequest.domain.RegisterRequest;
import com.losrosantes.registerrequest.repository.RegisterRequestRepository;
import com.losrosantes.registerrequest.service.RegisterRequestService;
import com.losrosantes.registerrequest.service.dto.RegisterRequestDTO;
import com.losrosantes.registerrequest.service.mapper.RegisterRequestMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.losrosantes.registerrequest.domain.enumeration.ResquestStatus;
/**
 * Integration tests for the {@link RegisterRequestResource} REST controller.
 */
@SpringBootTest(classes = RegisterRequestApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RegisterRequestResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_RE_ENROLLMENT = false;
    private static final Boolean UPDATED_RE_ENROLLMENT = true;

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_WHATSAPP = "AAAAAAAAAA";
    private static final String UPDATED_WHATSAPP = "BBBBBBBBBB";

    private static final String DEFAULT_CELL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CELL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_EMERGENCY_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_EMERGENCY_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_WORK_PLACE = "AAAAAAAAAA";
    private static final String UPDATED_WORK_PLACE = "BBBBBBBBBB";

    private static final String DEFAULT_WORK_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_WORK_PHONE_NUMBER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACCEPT_PAYMNET_DATE = false;
    private static final Boolean UPDATED_ACCEPT_PAYMNET_DATE = true;

    private static final Boolean DEFAULT_ATTEND_MEETINGS = false;
    private static final Boolean UPDATED_ATTEND_MEETINGS = true;

    private static final Boolean DEFAULT_PAID_ON_TIME = false;
    private static final Boolean UPDATED_PAID_ON_TIME = true;

    private static final String DEFAULT_SUGGESTION = "AAAAAAAAAA";
    private static final String UPDATED_SUGGESTION = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final ResquestStatus DEFAULT_STATUS = ResquestStatus.SUBMIT;
    private static final ResquestStatus UPDATED_STATUS = ResquestStatus.CANCELLED;

    @Autowired
    private RegisterRequestRepository registerRequestRepository;

    @Autowired
    private RegisterRequestMapper registerRequestMapper;

    @Autowired
    private RegisterRequestService registerRequestService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRegisterRequestMockMvc;

    private RegisterRequest registerRequest;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RegisterRequest createEntity(EntityManager em) {
        RegisterRequest registerRequest = new RegisterRequest()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .email(DEFAULT_EMAIL)
            .reEnrollment(DEFAULT_RE_ENROLLMENT)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .whatsapp(DEFAULT_WHATSAPP)
            .cellNumber(DEFAULT_CELL_NUMBER)
            .emergencyNumber(DEFAULT_EMERGENCY_NUMBER)
            .address(DEFAULT_ADDRESS)
            .workPlace(DEFAULT_WORK_PLACE)
            .workPhoneNumber(DEFAULT_WORK_PHONE_NUMBER)
            .acceptPaymnetDate(DEFAULT_ACCEPT_PAYMNET_DATE)
            .attendMeetings(DEFAULT_ATTEND_MEETINGS)
            .paidOnTime(DEFAULT_PAID_ON_TIME)
            .suggestion(DEFAULT_SUGGESTION)
            .createDate(DEFAULT_CREATE_DATE)
            .status(DEFAULT_STATUS);
        return registerRequest;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RegisterRequest createUpdatedEntity(EntityManager em) {
        RegisterRequest registerRequest = new RegisterRequest()
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .email(UPDATED_EMAIL)
            .reEnrollment(UPDATED_RE_ENROLLMENT)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .whatsapp(UPDATED_WHATSAPP)
            .cellNumber(UPDATED_CELL_NUMBER)
            .emergencyNumber(UPDATED_EMERGENCY_NUMBER)
            .address(UPDATED_ADDRESS)
            .workPlace(UPDATED_WORK_PLACE)
            .workPhoneNumber(UPDATED_WORK_PHONE_NUMBER)
            .acceptPaymnetDate(UPDATED_ACCEPT_PAYMNET_DATE)
            .attendMeetings(UPDATED_ATTEND_MEETINGS)
            .paidOnTime(UPDATED_PAID_ON_TIME)
            .suggestion(UPDATED_SUGGESTION)
            .createDate(UPDATED_CREATE_DATE)
            .status(UPDATED_STATUS);
        return registerRequest;
    }

    @BeforeEach
    public void initTest() {
        registerRequest = createEntity(em);
    }

    @Test
    @Transactional
    public void createRegisterRequest() throws Exception {
        int databaseSizeBeforeCreate = registerRequestRepository.findAll().size();
        // Create the RegisterRequest
        RegisterRequestDTO registerRequestDTO = registerRequestMapper.toDto(registerRequest);
        restRegisterRequestMockMvc.perform(post("/api/register-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(registerRequestDTO)))
            .andExpect(status().isCreated());

        // Validate the RegisterRequest in the database
        List<RegisterRequest> registerRequestList = registerRequestRepository.findAll();
        assertThat(registerRequestList).hasSize(databaseSizeBeforeCreate + 1);
        RegisterRequest testRegisterRequest = registerRequestList.get(registerRequestList.size() - 1);
        assertThat(testRegisterRequest.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testRegisterRequest.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testRegisterRequest.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testRegisterRequest.isReEnrollment()).isEqualTo(DEFAULT_RE_ENROLLMENT);
        assertThat(testRegisterRequest.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testRegisterRequest.getWhatsapp()).isEqualTo(DEFAULT_WHATSAPP);
        assertThat(testRegisterRequest.getCellNumber()).isEqualTo(DEFAULT_CELL_NUMBER);
        assertThat(testRegisterRequest.getEmergencyNumber()).isEqualTo(DEFAULT_EMERGENCY_NUMBER);
        assertThat(testRegisterRequest.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testRegisterRequest.getWorkPlace()).isEqualTo(DEFAULT_WORK_PLACE);
        assertThat(testRegisterRequest.getWorkPhoneNumber()).isEqualTo(DEFAULT_WORK_PHONE_NUMBER);
        assertThat(testRegisterRequest.isAcceptPaymnetDate()).isEqualTo(DEFAULT_ACCEPT_PAYMNET_DATE);
        assertThat(testRegisterRequest.isAttendMeetings()).isEqualTo(DEFAULT_ATTEND_MEETINGS);
        assertThat(testRegisterRequest.isPaidOnTime()).isEqualTo(DEFAULT_PAID_ON_TIME);
        assertThat(testRegisterRequest.getSuggestion()).isEqualTo(DEFAULT_SUGGESTION);
        assertThat(testRegisterRequest.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testRegisterRequest.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createRegisterRequestWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = registerRequestRepository.findAll().size();

        // Create the RegisterRequest with an existing ID
        registerRequest.setId(1L);
        RegisterRequestDTO registerRequestDTO = registerRequestMapper.toDto(registerRequest);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRegisterRequestMockMvc.perform(post("/api/register-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(registerRequestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RegisterRequest in the database
        List<RegisterRequest> registerRequestList = registerRequestRepository.findAll();
        assertThat(registerRequestList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = registerRequestRepository.findAll().size();
        // set the field null
        registerRequest.setFirstName(null);

        // Create the RegisterRequest, which fails.
        RegisterRequestDTO registerRequestDTO = registerRequestMapper.toDto(registerRequest);


        restRegisterRequestMockMvc.perform(post("/api/register-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(registerRequestDTO)))
            .andExpect(status().isBadRequest());

        List<RegisterRequest> registerRequestList = registerRequestRepository.findAll();
        assertThat(registerRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = registerRequestRepository.findAll().size();
        // set the field null
        registerRequest.setLastName(null);

        // Create the RegisterRequest, which fails.
        RegisterRequestDTO registerRequestDTO = registerRequestMapper.toDto(registerRequest);


        restRegisterRequestMockMvc.perform(post("/api/register-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(registerRequestDTO)))
            .andExpect(status().isBadRequest());

        List<RegisterRequest> registerRequestList = registerRequestRepository.findAll();
        assertThat(registerRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = registerRequestRepository.findAll().size();
        // set the field null
        registerRequest.setAddress(null);

        // Create the RegisterRequest, which fails.
        RegisterRequestDTO registerRequestDTO = registerRequestMapper.toDto(registerRequest);


        restRegisterRequestMockMvc.perform(post("/api/register-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(registerRequestDTO)))
            .andExpect(status().isBadRequest());

        List<RegisterRequest> registerRequestList = registerRequestRepository.findAll();
        assertThat(registerRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWorkPlaceIsRequired() throws Exception {
        int databaseSizeBeforeTest = registerRequestRepository.findAll().size();
        // set the field null
        registerRequest.setWorkPlace(null);

        // Create the RegisterRequest, which fails.
        RegisterRequestDTO registerRequestDTO = registerRequestMapper.toDto(registerRequest);


        restRegisterRequestMockMvc.perform(post("/api/register-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(registerRequestDTO)))
            .andExpect(status().isBadRequest());

        List<RegisterRequest> registerRequestList = registerRequestRepository.findAll();
        assertThat(registerRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWorkPhoneNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = registerRequestRepository.findAll().size();
        // set the field null
        registerRequest.setWorkPhoneNumber(null);

        // Create the RegisterRequest, which fails.
        RegisterRequestDTO registerRequestDTO = registerRequestMapper.toDto(registerRequest);


        restRegisterRequestMockMvc.perform(post("/api/register-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(registerRequestDTO)))
            .andExpect(status().isBadRequest());

        List<RegisterRequest> registerRequestList = registerRequestRepository.findAll();
        assertThat(registerRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAcceptPaymnetDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = registerRequestRepository.findAll().size();
        // set the field null
        registerRequest.setAcceptPaymnetDate(null);

        // Create the RegisterRequest, which fails.
        RegisterRequestDTO registerRequestDTO = registerRequestMapper.toDto(registerRequest);


        restRegisterRequestMockMvc.perform(post("/api/register-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(registerRequestDTO)))
            .andExpect(status().isBadRequest());

        List<RegisterRequest> registerRequestList = registerRequestRepository.findAll();
        assertThat(registerRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAttendMeetingsIsRequired() throws Exception {
        int databaseSizeBeforeTest = registerRequestRepository.findAll().size();
        // set the field null
        registerRequest.setAttendMeetings(null);

        // Create the RegisterRequest, which fails.
        RegisterRequestDTO registerRequestDTO = registerRequestMapper.toDto(registerRequest);


        restRegisterRequestMockMvc.perform(post("/api/register-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(registerRequestDTO)))
            .andExpect(status().isBadRequest());

        List<RegisterRequest> registerRequestList = registerRequestRepository.findAll();
        assertThat(registerRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPaidOnTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = registerRequestRepository.findAll().size();
        // set the field null
        registerRequest.setPaidOnTime(null);

        // Create the RegisterRequest, which fails.
        RegisterRequestDTO registerRequestDTO = registerRequestMapper.toDto(registerRequest);


        restRegisterRequestMockMvc.perform(post("/api/register-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(registerRequestDTO)))
            .andExpect(status().isBadRequest());

        List<RegisterRequest> registerRequestList = registerRequestRepository.findAll();
        assertThat(registerRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = registerRequestRepository.findAll().size();
        // set the field null
        registerRequest.setCreateDate(null);

        // Create the RegisterRequest, which fails.
        RegisterRequestDTO registerRequestDTO = registerRequestMapper.toDto(registerRequest);


        restRegisterRequestMockMvc.perform(post("/api/register-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(registerRequestDTO)))
            .andExpect(status().isBadRequest());

        List<RegisterRequest> registerRequestList = registerRequestRepository.findAll();
        assertThat(registerRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = registerRequestRepository.findAll().size();
        // set the field null
        registerRequest.setStatus(null);

        // Create the RegisterRequest, which fails.
        RegisterRequestDTO registerRequestDTO = registerRequestMapper.toDto(registerRequest);


        restRegisterRequestMockMvc.perform(post("/api/register-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(registerRequestDTO)))
            .andExpect(status().isBadRequest());

        List<RegisterRequest> registerRequestList = registerRequestRepository.findAll();
        assertThat(registerRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRegisterRequests() throws Exception {
        // Initialize the database
        registerRequestRepository.saveAndFlush(registerRequest);

        // Get all the registerRequestList
        restRegisterRequestMockMvc.perform(get("/api/register-requests?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(registerRequest.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].reEnrollment").value(hasItem(DEFAULT_RE_ENROLLMENT.booleanValue())))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].whatsapp").value(hasItem(DEFAULT_WHATSAPP)))
            .andExpect(jsonPath("$.[*].cellNumber").value(hasItem(DEFAULT_CELL_NUMBER)))
            .andExpect(jsonPath("$.[*].emergencyNumber").value(hasItem(DEFAULT_EMERGENCY_NUMBER)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].workPlace").value(hasItem(DEFAULT_WORK_PLACE)))
            .andExpect(jsonPath("$.[*].workPhoneNumber").value(hasItem(DEFAULT_WORK_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].acceptPaymnetDate").value(hasItem(DEFAULT_ACCEPT_PAYMNET_DATE.booleanValue())))
            .andExpect(jsonPath("$.[*].attendMeetings").value(hasItem(DEFAULT_ATTEND_MEETINGS.booleanValue())))
            .andExpect(jsonPath("$.[*].paidOnTime").value(hasItem(DEFAULT_PAID_ON_TIME.booleanValue())))
            .andExpect(jsonPath("$.[*].suggestion").value(hasItem(DEFAULT_SUGGESTION)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getRegisterRequest() throws Exception {
        // Initialize the database
        registerRequestRepository.saveAndFlush(registerRequest);

        // Get the registerRequest
        restRegisterRequestMockMvc.perform(get("/api/register-requests/{id}", registerRequest.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(registerRequest.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.reEnrollment").value(DEFAULT_RE_ENROLLMENT.booleanValue()))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.whatsapp").value(DEFAULT_WHATSAPP))
            .andExpect(jsonPath("$.cellNumber").value(DEFAULT_CELL_NUMBER))
            .andExpect(jsonPath("$.emergencyNumber").value(DEFAULT_EMERGENCY_NUMBER))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.workPlace").value(DEFAULT_WORK_PLACE))
            .andExpect(jsonPath("$.workPhoneNumber").value(DEFAULT_WORK_PHONE_NUMBER))
            .andExpect(jsonPath("$.acceptPaymnetDate").value(DEFAULT_ACCEPT_PAYMNET_DATE.booleanValue()))
            .andExpect(jsonPath("$.attendMeetings").value(DEFAULT_ATTEND_MEETINGS.booleanValue()))
            .andExpect(jsonPath("$.paidOnTime").value(DEFAULT_PAID_ON_TIME.booleanValue()))
            .andExpect(jsonPath("$.suggestion").value(DEFAULT_SUGGESTION))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingRegisterRequest() throws Exception {
        // Get the registerRequest
        restRegisterRequestMockMvc.perform(get("/api/register-requests/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRegisterRequest() throws Exception {
        // Initialize the database
        registerRequestRepository.saveAndFlush(registerRequest);

        int databaseSizeBeforeUpdate = registerRequestRepository.findAll().size();

        // Update the registerRequest
        RegisterRequest updatedRegisterRequest = registerRequestRepository.findById(registerRequest.getId()).get();
        // Disconnect from session so that the updates on updatedRegisterRequest are not directly saved in db
        em.detach(updatedRegisterRequest);
        updatedRegisterRequest
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .email(UPDATED_EMAIL)
            .reEnrollment(UPDATED_RE_ENROLLMENT)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .whatsapp(UPDATED_WHATSAPP)
            .cellNumber(UPDATED_CELL_NUMBER)
            .emergencyNumber(UPDATED_EMERGENCY_NUMBER)
            .address(UPDATED_ADDRESS)
            .workPlace(UPDATED_WORK_PLACE)
            .workPhoneNumber(UPDATED_WORK_PHONE_NUMBER)
            .acceptPaymnetDate(UPDATED_ACCEPT_PAYMNET_DATE)
            .attendMeetings(UPDATED_ATTEND_MEETINGS)
            .paidOnTime(UPDATED_PAID_ON_TIME)
            .suggestion(UPDATED_SUGGESTION)
            .createDate(UPDATED_CREATE_DATE)
            .status(UPDATED_STATUS);
        RegisterRequestDTO registerRequestDTO = registerRequestMapper.toDto(updatedRegisterRequest);

        restRegisterRequestMockMvc.perform(put("/api/register-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(registerRequestDTO)))
            .andExpect(status().isOk());

        // Validate the RegisterRequest in the database
        List<RegisterRequest> registerRequestList = registerRequestRepository.findAll();
        assertThat(registerRequestList).hasSize(databaseSizeBeforeUpdate);
        RegisterRequest testRegisterRequest = registerRequestList.get(registerRequestList.size() - 1);
        assertThat(testRegisterRequest.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testRegisterRequest.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testRegisterRequest.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testRegisterRequest.isReEnrollment()).isEqualTo(UPDATED_RE_ENROLLMENT);
        assertThat(testRegisterRequest.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testRegisterRequest.getWhatsapp()).isEqualTo(UPDATED_WHATSAPP);
        assertThat(testRegisterRequest.getCellNumber()).isEqualTo(UPDATED_CELL_NUMBER);
        assertThat(testRegisterRequest.getEmergencyNumber()).isEqualTo(UPDATED_EMERGENCY_NUMBER);
        assertThat(testRegisterRequest.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testRegisterRequest.getWorkPlace()).isEqualTo(UPDATED_WORK_PLACE);
        assertThat(testRegisterRequest.getWorkPhoneNumber()).isEqualTo(UPDATED_WORK_PHONE_NUMBER);
        assertThat(testRegisterRequest.isAcceptPaymnetDate()).isEqualTo(UPDATED_ACCEPT_PAYMNET_DATE);
        assertThat(testRegisterRequest.isAttendMeetings()).isEqualTo(UPDATED_ATTEND_MEETINGS);
        assertThat(testRegisterRequest.isPaidOnTime()).isEqualTo(UPDATED_PAID_ON_TIME);
        assertThat(testRegisterRequest.getSuggestion()).isEqualTo(UPDATED_SUGGESTION);
        assertThat(testRegisterRequest.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testRegisterRequest.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingRegisterRequest() throws Exception {
        int databaseSizeBeforeUpdate = registerRequestRepository.findAll().size();

        // Create the RegisterRequest
        RegisterRequestDTO registerRequestDTO = registerRequestMapper.toDto(registerRequest);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRegisterRequestMockMvc.perform(put("/api/register-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(registerRequestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RegisterRequest in the database
        List<RegisterRequest> registerRequestList = registerRequestRepository.findAll();
        assertThat(registerRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRegisterRequest() throws Exception {
        // Initialize the database
        registerRequestRepository.saveAndFlush(registerRequest);

        int databaseSizeBeforeDelete = registerRequestRepository.findAll().size();

        // Delete the registerRequest
        restRegisterRequestMockMvc.perform(delete("/api/register-requests/{id}", registerRequest.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RegisterRequest> registerRequestList = registerRequestRepository.findAll();
        assertThat(registerRequestList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
