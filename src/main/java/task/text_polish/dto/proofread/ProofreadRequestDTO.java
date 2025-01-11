package task.text_polish.dto.proofread;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProofreadRequestDTO {

    private String language;
    private String domain;
    private String content;
}
