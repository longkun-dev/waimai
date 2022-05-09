package im.yuki.waimai.api.service.api;

import im.yuki.waimai.api.service.entity.ResponseEntity;
import im.yuki.waimai.api.service.entity.User;
import im.yuki.waimai.api.service.feign.UserFeign;
import im.yuki.waimai.api.service.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/waimai/api/user")
public class UserApiController {

    @Autowired
    private UserFeign userFeign;

    @GetMapping(value = "/findByPage")
    public ResponseEntity<List<User>> findByPage(@RequestParam int pageNum, @RequestParam int pageSize) {
        return ResponseUtil.success(userFeign.findByPage(pageNum, pageSize));
    }

    @GetMapping(value = "/doLogin")
    public ResponseEntity<Object> doLogin(@RequestParam String username, @RequestParam String password) {
        return ResponseUtil.success(userFeign.doLogin(username, password));
    }
}
