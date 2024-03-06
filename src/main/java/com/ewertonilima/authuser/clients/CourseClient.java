package com.ewertonilima.authuser.clients;

import com.ewertonilima.authuser.dtos.CourseDto;
import com.ewertonilima.authuser.dtos.ResponsePageDto;
import com.ewertonilima.authuser.service.UtilsService;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@Component
public class CourseClient {

    final RestTemplate restTemplate;
    final UtilsService utilsService;

    public CourseClient(RestTemplate restTemplate, UtilsService utilsService) {
        this.restTemplate = restTemplate;
        this.utilsService = utilsService;
    }

    @Value("${ead.api.url.course}")
    String REQUEST_URL_COURSE;

    @Retry(name = "retryInstance", fallbackMethod = "retryFallback")
    public Page<CourseDto> getAllCoursesByUser(UUID userId, Pageable pageable) {
        List<CourseDto> searchResult;
        String url = REQUEST_URL_COURSE + utilsService.createUrl(userId, pageable);
        log.debug("Request URL: {} ", url);
        log.info("Request URL: {} ", url);

        ResponseEntity<ResponsePageDto<CourseDto>> result = null;
        try {
            ParameterizedTypeReference<ResponsePageDto<CourseDto>> responseType =
                    new ParameterizedTypeReference<>() {
                    };
            result = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
            searchResult = result.getBody().getContent();
            log.debug("Response Number of Elements: {} ", searchResult.size());
        } catch (HttpStatusCodeException e) {
            log.error("Error request /courses {} ", e);
        }
        log.info("Ending request /courses userId {} ", userId);
        return result.getBody();
    }

    public Page<CourseDto> retryFallback(UUID userId, Pageable pageable, Throwable t) {
        log.error("Inside retry fallback, cause - {}", t.toString());
        List<CourseDto> searchResult = new ArrayList<>();
        return new PageImpl<>(searchResult);
    }
}
