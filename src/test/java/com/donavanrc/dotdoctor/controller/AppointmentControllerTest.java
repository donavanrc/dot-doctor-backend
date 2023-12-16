package com.donavanrc.dotdoctor.controller;

import com.donavanrc.dotdoctor.domain.appointment.AppointmentCreateDTO;
import com.donavanrc.dotdoctor.domain.appointment.AppointmentReadDTO;
import com.donavanrc.dotdoctor.domain.appointment.AppointmentService;
import com.donavanrc.dotdoctor.domain.doctor.MedicalSpecialtyEnum;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<AppointmentCreateDTO> appointmentCreateDTOJson;

    @Autowired
    private JacksonTester<AppointmentReadDTO> appointmentReadDTOJson;

    @MockBean
    private AppointmentService appointmentService;

    @Test
    @DisplayName("Should return http status 400 when not receiving data")
    @WithMockUser
    void scheduleSample1() throws Exception {
        var response = mockMvc
                .perform(post("/appointment"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return http status 200 when receiving valid data")
    @WithMockUser
    void scheduleSample2() throws Exception {
        var dateTime = LocalDateTime.now().plusHours(1);
        var specialty = MedicalSpecialtyEnum.CARDIOLOGY;

        var appointmentReadData = new AppointmentReadDTO(null, 1L, 2L, dateTime);
        when(appointmentService.schedule(any())).thenReturn(appointmentReadData);

        var response = mockMvc
                .perform(
                        post("/appointment")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(appointmentCreateDTOJson.write(
                                        new AppointmentCreateDTO(1L, 2L, dateTime, specialty)
                                ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var expectedJson = appointmentReadDTOJson.write(appointmentReadData).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }
}