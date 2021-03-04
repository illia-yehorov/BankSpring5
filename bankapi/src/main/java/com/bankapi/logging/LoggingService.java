package com.bankapi.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class LoggingService {
    private static final String REQUEST_ID = "request_id";

//    private Marker importantMarker = MarkerFactory.getMarker("IMPORTANT");

    private ObjectMapper mapper = new ObjectMapper();


    @SneakyThrows
    public void logRequest(HttpServletRequest httpServletRequest, Object body) {
        if (httpServletRequest.getRequestURI().contains("medias")) {
            return;
        }
        Object requestId = httpServletRequest.getAttribute(REQUEST_ID);
        StringBuilder data = new StringBuilder();
        data.append("\nLOGGING REQUEST BODY-----------------------------------\n")
                .append("[REQUEST-ID]: ").append(requestId).append("\n")
                .append("[BODY REQUEST]: ").append("\n\n")
                .append(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(body))
                .append("\n\n")
                .append("LOGGING REQUEST BODY-----------------------------------\n");

        Marker importantMarker = MarkerFactory.getMarker("IMPORTANT");
        log.info(importantMarker, data.toString());
    }

    @SneakyThrows
    public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {
        if (httpServletRequest.getRequestURI().contains("medias")) {
            return;
        }
        Object requestId = httpServletRequest.getAttribute(REQUEST_ID);
        StringBuilder data = new StringBuilder();
        data.append("\nLOGGING RESPONSE-----------------------------------\n")
                .append("[REQUEST-ID]: ").append(requestId).append("\n")
                .append("[BODY RESPONSE]: ").append("\n\n")
                .append(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(body))
                .append("\n\n")
                .append("LOGGING RESPONSE-----------------------------------\n");

        log.info(data.toString());
    }
}