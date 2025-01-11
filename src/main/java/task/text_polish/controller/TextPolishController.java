package task.text_polish.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import task.text_polish.dto.polish.TextPolishRequestDTO;
import task.text_polish.dto.polish.TextPolishResponseDTO;
import task.text_polish.service.TextPolishService;

/**
 * REST controller for handling text polishing requests.
 * Provides an endpoint to validate and enhance text using an external proofreading service.
 */
@RestController
@RequestMapping("/polish")
@RequiredArgsConstructor
@Slf4j
public class TextPolishController {

    private final TextPolishService textPolishService;

    /**
     * Endpoint for polishing text. Accepts a request containing language, domain, and content,
     * validates the input, processes the request using an external proofreading service, and
     * returns the proofread content along with a similarity score.
     *
     * @param requestDTO the incoming request containing the language, domain, and content.
     * @return a response containing the proofread content and the similarity score.
     */
    @PostMapping
    public TextPolishResponseDTO polishText(@RequestBody @Valid TextPolishRequestDTO requestDTO) {
        log.info("POST /polish endpoint called");
        TextPolishResponseDTO response = textPolishService.processRequest(requestDTO);
        log.info("POST /polish endpoint successfully processed. Response prepared.");
        return response;
    }
}
