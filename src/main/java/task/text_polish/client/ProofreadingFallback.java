package task.text_polish.client;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import task.text_polish.dto.proofread.ProofreadRequestDTO;
import task.text_polish.dto.proofread.ProofreadResponseDTO;
import task.text_polish.service.CacheService;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProofreadingFallback implements ProofreadingClient {

    private final CacheService cacheService;

    /**
     * Fetches cached supported languages as a fallback.
     *
     * @return a list of supported languages from the cache.
     */
    @Override
    public List<String> fetchSupportedLanguages() {
        log.warn("Fallback triggered: Fetching supported languages from cache.");
        return cacheService.getSupportedLanguages();
    }

    /**
     * Fetches cached supported domains as a fallback.
     *
     * @return a list of supported domains from the cache.
     */
    @Override
    public List<String> fetchSupportedDomains() {
        log.warn("Fallback triggered: Fetching supported domains from cache.");
        return cacheService.getSupportedDomains();
    }

    /**
     * Returns a default proofread response as a fallback.
     *
     * @param request the proofread request that could not be processed.
     * @return a default response indicating the fallback was triggered.
     */
    @Override
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ProofreadResponseDTO proofread(ProofreadRequestDTO request) {
        log.error("Fallback triggered: Unable to process proofread request.");
        return new ProofreadResponseDTO("Service unavailable. Please try again later.");
    }
}
