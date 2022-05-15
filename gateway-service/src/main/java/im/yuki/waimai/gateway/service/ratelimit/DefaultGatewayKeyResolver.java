package im.yuki.waimai.gateway.service.ratelimit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;

/**
 * @author longkun
 * @desc 限流
 * @since 5/15/2022 10:10 PM
 */
@Component
@Slf4j
public class DefaultGatewayKeyResolver implements KeyResolver {

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);

        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        log.info("当前返回的 url 是: {}", path);

        String hostString = Objects.requireNonNull(request.getRemoteAddress()).getHostString();
        log.info("当前请求来自 IP 地址: {}", hostString);

        return Mono.just(Optional.ofNullable(hostString).orElse(""));
    }
}
