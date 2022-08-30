package com.dxs.distribute.lock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.dxs.distribute.lock.mapper")
@SpringBootApplication
public class SpringbootDistributeLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDistributeLockApplication.class , args);
    }

}
