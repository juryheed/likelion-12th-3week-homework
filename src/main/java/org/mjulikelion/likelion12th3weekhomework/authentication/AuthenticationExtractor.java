package org.mjulikelion.likelion12th3weekhomework.authentication;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;
import org.mjulikelion.likelion12th3weekhomework.error.exception.UnathorizedException;

public class AuthenticationExtractor {
    private static final String TOKEN_COOKIE_NAME = "AccessToken";

    public static String extract(final HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (TOKEN_COOKIE_NAME.equals(cookie.getName())) {
                    //return JwtEncoder.decodeJwtBearerToken(cookie.getValue());
                    return cookie.getValue();  //Access토큰을 추출해 디코딩 한다
                }
            }
        }
        throw new UnathorizedException(ErrorCode.TOKEN_NOT_FOUND);//없으면 오류 출력
    }
}
