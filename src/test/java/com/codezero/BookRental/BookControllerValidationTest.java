package com.codezero.BookRental;

import com.codezero.BookRental.repositories.BookRepository;
import com.codezero.BookRental.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerValidationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @MockitoBean
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 유효하지않은_입력은_400에러와_필드별_메시지를_반환한다() throws Exception {
        BookRequest bookRequest = new BookRequest("","",null);
        String json = objectMapper.writeValueAsString(bookRequest);

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("제목은 필수입니다."))
                .andExpect(jsonPath("$.author").value("저자는 필수입니다."))
                .andExpect(jsonPath("$.publishedDate").value("출판일은 필수입니다."));
    }
}
