package im.yuki.waimai.gateway.service.ratelimit;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.ratelimit.AbstractRateLimiter;
import org.springframework.cloud.gateway.support.ConfigurationService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author longkun
 * @desc 网关限流
 * @since 5/15/2022 10:48 PM
 */
@Component
@Primary
@Slf4j
public class DefaultGatewayRateLimiter extends AbstractRateLimiter<DefaultGatewayRateLimiter.Config> {

    /**
     * 和配置文件中的属性名对应
     */
    private static final String DEFAULT_GATEWAY_RATE_LIMITER = "default-gateway-rate-limiter";

    private RateLimiter rateLimiter = RateLimiter.create(1);

    protected DefaultGatewayRateLimiter(ConfigurationService configurationService) {
        super(DefaultGatewayRateLimiter.Config.class, DEFAULT_GATEWAY_RATE_LIMITER, configurationService);
    }

    @Override
    public Mono<Response> isAllowed(String routeId, String id) {

        log.info("网关默认的限流 routeId:[{}],id:[{}]", routeId, id);

        Config config = getConfig().get(routeId);

        return Mono.fromSupplier(() -> {
            boolean acquire = rateLimiter.tryAcquire(config.requestTokens);
            if (acquire) {
                return new Response(true, Maps.newHashMap());
            } else {
                return new Response(false, Maps.newHashMap());
            }
        });
    }

    @Data
    public static class Config {

        /**
         * 每次请求多少个 token
         */
        private Integer requestTokens;

    }
}
