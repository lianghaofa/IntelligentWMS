package org.iwms.asn.fallback;

import org.iwms.asn.feign.ProviderClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author leung
 */
@Component
public class ProviderClientFallBack implements ProviderClient {


    @Override
    public List<String> list() {
        return new ArrayList<>();
    }
}
