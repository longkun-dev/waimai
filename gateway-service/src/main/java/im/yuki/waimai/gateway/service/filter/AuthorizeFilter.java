package im.yuki.waimai.gateway.service.filter;

import im.yuki.waimai.common.service.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author longkun-dev
 * @time 2022-05-11 22:49
 * @desc 网关鉴权过滤器
 */
@Component
@Slf4j
public class AuthorizeFilter implements GlobalFilter, Ordered {

    private static final String AUTHORIZE_KEY = "token";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // 如果是登录请求，则放行
        if (request.getURI().getPath().contains("/user/doLogin")) {
            return chain.filter(exchange);
        }

        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst(AUTHORIZE_KEY);

        log.info("token is : {}", token);
        log.info("headers is : {}", headers);

        if (StringUtils.isBlank(token)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        try {
            JwtUtil.parseToken(token);
        } catch (Exception e) {
            // token 解析异常，则表示 token 伪造或过期等
            log.error("token 解析异常");
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        // token 正常，正常放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
