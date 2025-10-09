package com.fanwei;

import com.alibaba.cloud.nacos.discovery.NacosDiscoveryClient;
import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import com.alibaba.nacos.api.exception.NacosException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

@SpringBootTest
public class DiscoveryTest {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    NacosServiceDiscovery nacosServiceDiscovery;

    @Test
    void nacosServiceDiscoveryTest() throws NacosException {
        for(String service : nacosServiceDiscovery.getServices()){
            List<ServiceInstance> instances = nacosServiceDiscovery.getInstances(service);
            System.out.println("service: " + service);

            instances.forEach(instance -> {
                System.out.println("host: " + instance.getHost() + ", port: " + instance.getPort());
            });
        }
    }
    @Test
    void DiscoveryClientTest() {

        for (String service : discoveryClient.getServices()) {
            System.out.println("service: " + service);

            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            // 获取ip+port
            instances.forEach(instance -> {
                System.out.println("host: " + instance.getHost() + ", port: " + instance.getPort());
            });
        }

    }
}
