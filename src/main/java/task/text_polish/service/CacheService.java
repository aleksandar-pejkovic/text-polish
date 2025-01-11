package task.text_polish.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import task.text_polish.client.ProofreadingClient;

/**
 * Service for managing cached data from the external proofreading service.
 * Provides methods to fetch and store supported languages and domains for validation.
 * The cache is updated daily using a scheduled task.
 */
@Service
@Getter
@RequiredArgsConstructor
@Slf4j
public class CacheService {

    private final ProofreadingClient proofreadingClient;

    private List<String> supportedLanguages = new ArrayList<>(List.of("en-US", "en-GB"));
    private List<String> supportedDomains = new ArrayList<>(List.of("general", "business"));

    /**
     * Scheduled task that updates the cache with the latest supported languages
     * and domains from the external proofreading service.
     * Runs every day at midnight.
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateCache() {
        log.info("Updating cache with supported languages and domains.");
        supportedLanguages = proofreadingClient.fetchSupportedLanguages();
        supportedDomains = proofreadingClient.fetchSupportedDomains();
        log.info("Cache updated successfully. Languages: {}, Domains: {}", supportedLanguages, supportedDomains);
    }
}
