package com.sway3i.dto.Course.Response;

import com.sway3i.entities.Fees;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class CourseDetailsResponseDTO {

    private long price;
    private List<Fees> fees;

}
