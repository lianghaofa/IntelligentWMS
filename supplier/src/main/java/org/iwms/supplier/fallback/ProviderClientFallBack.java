package org.iwms.supplier.fallback;

import org.iwms.supplier.feign.ProviderClient;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author leung
 */
@Component
public class ProviderClientFallBack implements ProviderClient {


    @Override
    public String list() {
        return Arrays.asList("调用fallBack接口", "返回未知结果").toString();
    }
}
