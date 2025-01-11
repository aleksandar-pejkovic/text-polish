package task.text_polish.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import task.text_polish.client.ProofreadingClient;
import task.text_polish.dto.polish.TextPolishRequestDTO;
import task.text_polish.dto.polish.TextPolishResponseDTO;
import task.text_polish.dto.proofread.ProofreadRequestDTO;
import task.text_polish.dto.proofread.ProofreadResponseDTO;
import task.text_polish.util.SimilarityCalculator;
import task.text_polish.util.converter.DTOConverter;

/**
 * Service for processing text polishing requests.
 * Integrates with the external proofreading service to validate and enhance text,
 * and calculates a similarity score between the original and proofread text.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TextPolishService {

    private final ProofreadingClient proofreadingClient;
    private final SimilarityCalculator similarityCalculator;

    /**
     * Processes a text polishing request.
     * Converts the incoming {@link TextPolishRequestDTO} to a {@link ProofreadRequestDTO},
     * sends it to the external proofreading service, and calculates a similarity score
     * between the original and proofread text.
     *
     * @param textPolishRequestDTO the request containing language, domain, and content.
     * @return a {@link TextPolishResponseDTO} containing the proofread content and similarity score.
     */
    public TextPolishResponseDTO processRequest(TextPolishRequestDTO textPolishRequestDTO) {

        ProofreadRequestDTO proofreadRequest = DTOConverter.toProofreadRequest(textPolishRequestDTO);

        log.info("Sending request to the external proofreading service.");
        ProofreadResponseDTO proofreadResponse = proofreadingClient.proofread(proofreadRequest);

        log.info("Calculating similarity score.");
        double similarityScore = similarityCalculator.calculateSimilarity(
                textPolishRequestDTO.getContent(),
                proofreadResponse.getProofread_content()
        );

        return DTOConverter.toPolishResponse(proofreadResponse, similarityScore);
    }
}
