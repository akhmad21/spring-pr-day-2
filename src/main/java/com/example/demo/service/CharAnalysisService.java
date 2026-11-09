package com.example.demo.service;

import com.example.demo.dto.request.CharAnalysisRequest;
import com.example.demo.dto.response.CharAnalysisResponse;

import java.util.List;

public interface CharAnalysisService {
    List<CharAnalysisResponse> cari(String kata);
    CharAnalysisResponse hitungDanSimpan(CharAnalysisRequest request);
}
