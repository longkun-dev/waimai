package im.yuki.waimai.gateway.service.filter;

import com.alibaba.fastjson2.JSONObject;
import im.yuki.waimai.common.service.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

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

        String requestPath = request.getURI().getPath();
        // 如果是登录请求或者退出登录，则放行
        if (requestPath.contains("/user/doLogin") || requestPath.contains("/user/doLogout")) {
            return chain.filter(exchange);
        }

        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst(AUTHORIZE_KEY);

        JSONObject responseBody = this.buildResponse(401, "会话已过期，请重新登录", null);
        byte[] responseBodyBytes = responseBody.toJSONString().getBytes(StandardCharsets.UTF_8);
        DataBuffer dataBuffer = response.bufferFactory().wrap(responseBodyBytes);

        if (StringUtils.isBlank(token)) {
            response.getHeaders().add("Content-Type", "application/json");
            return response.writeWith(Mono.just(dataBuffer));
        }

        try {
            JwtUtil.parseToken(token);
        } catch (Exception e) {
            // token 解析异常，则表示 token 伪造或过期等
            log.error("token 解析异常");
            response.getHeaders().add("Content-Type", "application/json");
            return response.writeWith(Mono.just(dataBuffer));
        }

        // token 正常，正常放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @SuppressWarnings("unchecked")
    private JSONObject buildResponse(int code, String message, Object data) {
        JSONObject response = new JSONObject();
        response.put("code", code);
        response.put("message", message);
        response.put("data", data);

        return response;
    }
}
