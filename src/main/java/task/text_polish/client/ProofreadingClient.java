package task.text_polish.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import task.text_polish.config.FeignClientConfiguration;
import task.text_polish.dto.proofread.ProofreadRequestDTO;
import task.text_polish.dto.proofread.ProofreadResponseDTO;

/**
 * Feign client for interacting with the external Proofreading Service API.
 * Provides methods to fetch supported languages, domains, and to proofread text content.
 */
@FeignClient(
        name = "${proofreading-service.name}",
        url = "${proofreading-service.url}",
        fallback = ProofreadingFallback.class,
        configuration = FeignClientConfiguration.class)
public interface ProofreadingClient {

    /**
     * Fetches the list of supported language codes from the external service.
     *
     * @return a list of supported language codes (e.g., ["en-US", "fr-FR"]).
     */
    @GetMapping("/languages")
    List<String> fetchSupportedLanguages();

    /**
     * Fetches the list of supported content domains from the external service.
     *
     * @return a list of supported content domains (e.g., ["academic", "business"]).
     */
    @GetMapping("/domains")
    List<String> fetchSupportedDomains();

    /**
     * Sends a request to proofread the given content.
     *
     * @param request the request object containing language, domain, and content.
     * @return the proofread content from the external service.
     */
    @PostMapping("/proofread")
    ProofreadResponseDTO proofread(@RequestBody ProofreadRequestDTO request);
}
