package com.sway3i.dto.Course.Response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class CourseDetailsResponseDTO {

    private long price;
    private List<Long> feeIds;
}
