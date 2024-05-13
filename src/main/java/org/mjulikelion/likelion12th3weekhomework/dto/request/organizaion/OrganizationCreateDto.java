package org.mjulikelion.likelion12th3weekhomework.dto.request.organizaion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class OrganizationCreateDto {

    @NotBlank(message = "이름이 누락되었습니다")
    @Size(min = 1, max = 30, message = "이름은 한글자에서 30글자 사이입니다")
    private String name;
}
