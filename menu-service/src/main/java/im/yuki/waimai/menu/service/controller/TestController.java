package im.yuki.waimai.menu.service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class TestController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/getServerPort")
    public String getServerPort() {
        return "菜单服务运行端口：" + serverPort;
    }
}
