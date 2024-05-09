package org.mjulikelion.likelion12th3weekhomework.dto.response.user;

import lombok.Builder;
import lombok.Getter;
import org.mjulikelion.likelion12th3weekhomework.model.User;

import java.util.List;

@Getter
@Builder
public class UserListResponseData {
    private List<User> user;
}
