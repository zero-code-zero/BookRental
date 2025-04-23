package com.codezero.BookRental.controllers;

import com.codezero.BookRental.dto.RentalRequest;
import com.codezero.BookRental.dto.RentalResponse;
import com.codezero.BookRental.repositories.BookRepository;
import com.codezero.BookRental.repositories.MemberRepository;
import com.codezero.BookRental.repositories.RentalRepository;
import com.codezero.BookRental.service.RentalService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RentalController.class)
class RentalControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RentalService rentalService;
    @MockitoBean
    private BookRepository bookRepository;
    @MockitoBean
    private MemberRepository memberRepository;
    @MockitoBean
    private RentalRepository rentalRepository;


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 책_대여() throws Exception {
        RentalRequest request = new RentalRequest(1L,1L);
        when(rentalService.rentBook(request))
                .thenReturn(new RentalResponse(1L,"스프링부트 기초강의", "코드제로", LocalDate.now()));

        mockMvc.perform(post("/rental")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.rentalId").value(1L))
                .andExpect(jsonPath("$.bookTitle").value("스프링부트 기초강의"));
    }
}