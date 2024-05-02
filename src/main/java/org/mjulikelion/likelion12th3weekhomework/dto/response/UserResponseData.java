package org.mjulikelion.likelion12th3weekhomework.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.mjulikelion.likelion12th3weekhomework.model.User;

@Getter
@Builder
public class UserResponseData {
    User user;
}
