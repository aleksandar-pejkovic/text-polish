package task.text_polish.dto.polish;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import task.text_polish.validation.domain.ValidDomain;
import task.text_polish.validation.language.ValidLanguage;
import task.text_polish.validation.wordcount.WordCount;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TextPolishRequestDTO {

    @NotBlank(message = "Language cannot be blank")
    @ValidLanguage
    private String language;

    @NotBlank(message = "Domain cannot be blank")
    @ValidDomain
    private String domain;

    @NotBlank(message = "Content cannot be blank")
    @WordCount(max = 30, message = "Content must not exceed 30 words")
    private String content;
}
