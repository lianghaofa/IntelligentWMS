package org.iwms.asn.feign;


import org.iwms.asn.fallback.ProviderClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Component
@FeignClient(name = "supplier-service", fallback = ProviderClientFallBack.class)
//会扫描指定包下，标记FeignClient注解的接口
//会根据服务名，从注册中心找到对应的IP地址
public interface ProviderClient {
    //这里跟提供者接口的URL一致
    @RequestMapping("/provider/list")
    List<String> list();
}
