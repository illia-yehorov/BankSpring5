package com.bankapi.logging;

import static com.bankapi.logging.MDCConstants.LOG_REF;
import static com.bankapi.logging.MDCConstants.REQUEST_ID_HEADERS;
import static com.bankapi.logging.MDCConstants.RESPONSE_ID_HEADERS;

import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@EqualsAndHashCode(callSuper = true)
@Data
@Component
public class MDCFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain)
            throws java.io.IOException, ServletException {
        try {
            final String token;
            if (!StringUtils.isEmpty(request.getHeader(REQUEST_ID_HEADERS))) {
                token = request.getHeader(REQUEST_ID_HEADERS);
            } else {
                token = UUID.randomUUID().toString().toUpperCase().replace("-", "").substring(0, 5);
            }
            MDC.put(LOG_REF, token);
            if (!StringUtils.isEmpty(RESPONSE_ID_HEADERS)) {
                response.addHeader(RESPONSE_ID_HEADERS, token);
            }
            chain.doFilter(request, response);
        } finally {
            MDC.remove(LOG_REF);
        }
    }
}