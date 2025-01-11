package task.text_polish.util;

import org.apache.commons.text.similarity.JaroWinklerSimilarity;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public final class SimilarityCalculator {

    private final JaroWinklerSimilarity similarityAlgorithm;

    /**
     * Calculates the similarity score between two strings.
     * Excludes tags like <.../> from the content if present.
     *
     * @param originalText  The original text.
     * @param proofreadText The proofread text.
     * @return Similarity score as a double value.
     */
    public double calculateSimilarity(String originalText, String proofreadText) {
        if (originalText == null) {
            throw new IllegalArgumentException("Original content must not be null.");
        }if (proofreadText == null) {
            throw new IllegalArgumentException("Proofread content must not be null.");
        }
        String cleanedOriginal = ContentProcessor.removeMetaTags(originalText);
        String cleanedProofread = ContentProcessor.removeMetaTags(proofreadText);

        return similarityAlgorithm.apply(cleanedOriginal, cleanedProofread);
    }
}
