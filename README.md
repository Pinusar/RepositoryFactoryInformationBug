# Sample project for reproducing RepositoryFactoryInformation bug

## Steps to reproduce

1. Put a break point in MyService in the postConstruct() method
2. Run the application
3. Check repositoryFactoryInformations and jpaRepositoryFactoryBeans instance fields

### Expected:
Both contain 1 element referencing MyRepository

### Actual:
Only jpaRepositoryFactoryBeans contains an element. repositoryFactoryInformations field is an empty list.

## Additional info
In Spring Boot 2.7.18 it was working as expected. This can be verified on the SB_2 branch, where necessary set up has been done.

JpaRepositoryFactoryBean actually implements RepositoryFactoryInformation:

JpaRepositoryFactoryBean -> TransactionalRepositoryFactoryBeanSupport -> RepositoryFactoryBeanSupport -> RepositoryFactoryInformation

Which means that if bean of type JpaRepositoryFactoryBean is found, RepositoryFactoryInformation bean should be available as well.