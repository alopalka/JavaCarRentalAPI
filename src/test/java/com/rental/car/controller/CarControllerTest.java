package com.rental.car.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rental.car.car.CarRepository;
import com.rental.car.car.CarService;
import com.rental.car.car.model.Car;
import com.rental.car.car.model.CarDto;
import com.rental.car.car.model.CarType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carServiceMock;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldFindAll_ShouldReturnAllCars() throws Exception {
        CarDto car1 = CarDto.builder()
                .make("Audi")
                .model("S6 C7")
                .carType(CarType.SUPERSPORT)
                .build();
        CarDto car2 = CarDto.builder()
                .make("Mercedes-Benz")
                .model("S63 W222 Coupe")
                .carType(CarType.LUXURY)
                .build();

        when(carServiceMock.findAll()).thenReturn(List.of(car1.toEntity(), car2.toEntity()));

        when(carServiceMock.save(car1.toEntity())).thenReturn(car1.toEntity());
        when(carServiceMock.save(car2.toEntity())).thenReturn(car2.toEntity());

        mockMvc.perform(post("/api/v1/cars")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").password("admin"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(car1)))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/v1/cars")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").password("admin"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(car2)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/v1/cars")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").password("admin")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].make", equalTo("Audi")))
                .andExpect(jsonPath("$.[1].make", equalTo("Mercedes-Benz")));
    }

}
