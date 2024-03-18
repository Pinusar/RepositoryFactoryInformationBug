package com.example.demo;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactoryInformation;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class MyService {
    private final List<RepositoryFactoryInformation<?, ?>> repositoryFactoryInformations;
    private final List<JpaRepositoryFactoryBean<?, ?, ?>> jpaRepositoryFactoryBeans;

    public MyService(List<RepositoryFactoryInformation<?, ?>> repositoryFactoryInformations, List<JpaRepositoryFactoryBean<?, ?, ?>> jpaRepositoryFactoryBeans) {
        this.repositoryFactoryInformations = repositoryFactoryInformations;
        this.jpaRepositoryFactoryBeans = jpaRepositoryFactoryBeans;
    }

    @PostConstruct
    public void postConstruct() {
        // put a break point here and inspect repositoryFactoryInformations and jpaRepositoryFactoryBeans fields
        // expected: both contain 1 element referencing MyRepository
        // actual repositoryFactoryInformations is empty
        System.out.println(repositoryFactoryInformations);
        System.out.println(jpaRepositoryFactoryBeans);
        // JpaRepositoryFactoryBean actually implements RepositoryFactoryInformation
        // JpaRepositoryFactoryBean -> TransactionalRepositoryFactoryBeanSupport -> RepositoryFactoryBeanSupport -> RepositoryFactoryInformation

        // in Spring boot 2.7.11 it works as expected. In Spring boot 3.2.3 it doesn't
    }
}
