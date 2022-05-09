package im.yuki.waimai.api.service.feign;

import im.yuki.waimai.api.service.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "user-service")
public interface UserFeign {

    @GetMapping(value = "/user/findByPage")
    List<User> findByPage(@RequestParam int pageNum, @RequestParam int pageSize);

    @GetMapping(value = "/user/doLogin")
    String doLogin(@RequestParam String username, @RequestParam String password);
}
