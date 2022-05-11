package im.yuki.waimai.common.service.util;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class JwtUtilTest {

    @Test
    public void createToken() {
        String token = JwtUtil.createToken("testId", "testSubject", null);
        log.info("token: {}", token);
    }

    @Test
    public void parseToken() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJ0ZXN0SWQiLCJzdWIiOiJ0ZXN0U3ViamVjdCIsImlzcyI6ImltLnl1a2kud2" +
                "FpbWFpIiwiaWF0IjoxNjUyMjc5NTI3LCJleHAiOjE2NTIyODMxMjd9.wRA6Iy2QV2Ka1M4EFWE_4CPoYmnebR3W1uZxMML_Ozg";
        Claims claims = JwtUtil.parseToken(token);
        log.info("id: {}", claims.getId());
        log.info("subject: {}", claims.getSubject());
        log.info("issuer: {}", claims.getIssuer());
        log.info("issuedAt: {}", claims.getIssuedAt());
        log.info("expiration: {}", claims.getExpiration());
    }
}