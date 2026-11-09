package com.example.demo.controller;

import com.example.demo.dto.request.CharAnalysisRequest;
import com.example.demo.dto.response.CharAnalysisResponse;
import com.example.demo.service.CharAnalysisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AnalyzerControllerTest {

    private MockMvc mockMvc;
    private CharAnalysisService charAnalysisService;

    @BeforeEach
    void setUp() {
        charAnalysisService = Mockito.mock(CharAnalysisService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new AnalyzerController(charAnalysisService)).build();
    }

    @Test
    void testHitungDanSimpan() throws Exception {
        CharAnalysisResponse mockResponse = new CharAnalysisResponse(
                1L,
                "gajah mati meninggalkan gading",
                27,
                Map.of('a', 6, 'g', 5)
        );
        Mockito.when(charAnalysisService.hitungDanSimpan(any(CharAnalysisRequest.class))).thenReturn(mockResponse);

        String jsonPayload = "{\"kalimat\": \"gajah mati meninggalkan gading\"}";

        mockMvc.perform(post("/api/v1")
                        .header("X-Request-Id", "req-001")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.kalimat").value("gajah mati meninggalkan gading"))
                .andExpect(jsonPath("$.data.totalHuruf").value(27));
    }
}
