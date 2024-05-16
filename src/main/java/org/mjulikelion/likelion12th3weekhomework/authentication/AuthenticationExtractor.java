package org.mjulikelion.likelion12th3weekhomework.authentication;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;
import org.mjulikelion.likelion12th3weekhomework.error.exception.UnauthorizedException;

public class AuthenticationExtractor {
    private static final String TOKEN_COOKIE_NAME = "AccessToken";

    public static String extract(final HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (TOKEN_COOKIE_NAME.equals(cookie.getName())) {
                    //return Encoder.decodeJwtBearerToken(cookieVlaue);
                    return cookie.getValue().replace("+", " ").substring("Bearer ".length()); //디코딩
                }
            }
        }
        throw new UnauthorizedException(ErrorCode.TOKEN_NOT_FOUND);//없으면 오류 출력
    }


}
