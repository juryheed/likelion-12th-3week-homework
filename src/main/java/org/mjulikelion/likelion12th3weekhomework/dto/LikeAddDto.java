package org.mjulikelion.likelion12th3weekhomework.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mjulikelion.likelion12th3weekhomework.model.Memo;
import org.mjulikelion.likelion12th3weekhomework.model.User;

@Getter
@AllArgsConstructor
public class LikeAddDto {


    private User user;
    private Memo memo;
}
