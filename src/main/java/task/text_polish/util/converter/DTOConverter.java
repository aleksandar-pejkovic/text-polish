package task.text_polish.util.converter;

import task.text_polish.dto.polish.TextPolishRequestDTO;
import task.text_polish.dto.polish.TextPolishResponseDTO;
import task.text_polish.dto.proofread.ProofreadRequestDTO;
import task.text_polish.dto.proofread.ProofreadResponseDTO;

public final class DTOConverter {

    private DTOConverter() {
    }

    /**
     * Converts a {@link TextPolishRequestDTO} to a {@link ProofreadRequestDTO}.
     * This is used to prepare the request for the external proofreading service.
     *
     * @param textPolishRequestDTO the input DTO containing language, domain, and content.
     * @return a {@link ProofreadRequestDTO} formatted for the external API.
     */
    public static ProofreadRequestDTO toProofreadRequest(TextPolishRequestDTO textPolishRequestDTO) {
        return new ProofreadRequestDTO(
                textPolishRequestDTO.getLanguage(),
                textPolishRequestDTO.getDomain(),
                textPolishRequestDTO.getContent()
        );
    }

    /**
     * Converts a {@link ProofreadResponseDTO} and similarity score into a {@link TextPolishResponseDTO}.
     * This combines the proofread content and the calculated similarity score to form the final response.
     *
     * @param proofreadResponseDTO the response from the external proofreading service.
     * @param similarityScore      the calculated similarity score between the original and proofread content.
     * @return a {@link TextPolishResponseDTO} containing the proofread content and similarity score.
     */
    public static TextPolishResponseDTO toPolishResponse(
            ProofreadResponseDTO proofreadResponseDTO,
            double similarityScore) {
        return new TextPolishResponseDTO(
                proofreadResponseDTO.getProofread_content(),
                similarityScore
        );
    }
}
