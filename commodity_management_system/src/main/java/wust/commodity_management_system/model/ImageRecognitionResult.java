package wust.commodity_management_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageRecognitionResult {
    private long log_id;
    private long result_num;
    List<Result> result;
}
