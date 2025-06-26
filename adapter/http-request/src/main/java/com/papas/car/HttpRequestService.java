package com.papas.car;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class HttpRequestService implements HttpRequestPort {

    @Value("${api.user.nickname}")
    private String nicknameApiPath;

    @Value("${api.hashtag.name}")
    private String hashtagApiPath;

    private final WebClient webClient;

    public HttpRequestService() {
        this.webClient = WebClient.builder().build();
    }

    @Override
    public Optional<String> getUserNickname(Long userId) {
        NicknameResponse nicknameResponse = getResponse(nicknameApiPath, Map.of("userId", userId), NicknameResponse.class);
        if (nicknameResponse == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(nicknameResponse.getData());
    }

    @Override
    public List<String> getHashtags(Long carId) {
        HashtagResponse hashtagResponse = getResponse(hashtagApiPath, Map.of("carId", carId), HashtagResponse.class);
        if (hashtagResponse == null) {
            return List.of();
        }
        return (hashtagResponse.getData() != null) ? hashtagResponse.getData() : List.of();
    }

    ////////////////////////////////////ㅋ여기서만 쓰이기도하고~, 귀찮기 때문에 분리하지 않음ㅋ////////////////////////////////////
    private <T> T getResponse(String reqPath, Map<String, Long> idParam, Class<T> clazz) {
        return webClient.get()
                .uri(reqPath, idParam)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> {
                    // 상태코드가 4xx, 5xx면 에러 처리 로직 (예외 발생 X)
                    return response.bodyToMono(String.class)
                            .flatMap(errorBody -> {
                                // 로그 찍고 그냥 Mono.empty() 반환해서 에러를 삼킴
                                log.warn("Error response from nickname API: {}", errorBody);
                                return Mono.empty(); // 예외를 던지지 않음
                            });
                })
                .bodyToMono(clazz)
                .onErrorResume(e -> {
                    // 네트워크 오류, 타임아웃 등 예외도 처리
                    log.error("WebClient error", e);
                    return Mono.empty(); // == null
                })
                .block();
    }

    @Data
    static class NicknameResponse {
        private String data;
    }
    @Data
    static class HashtagResponse {
        private List<String> data;
    }
}
