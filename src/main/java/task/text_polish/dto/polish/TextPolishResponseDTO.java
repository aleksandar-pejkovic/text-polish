package task.text_polish.dto.polish;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TextPolishResponseDTO {

    private String polished_content;
    private double similarity;
}
