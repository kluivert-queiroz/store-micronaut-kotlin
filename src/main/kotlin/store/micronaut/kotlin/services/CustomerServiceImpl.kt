package store.micronaut.kotlin.services

import jakarta.inject.Singleton
import store.micronaut.kotlin.datavalidation.CreateCustomerDTO
import store.micronaut.kotlin.exceptions.ResourceNotCreated
import store.micronaut.kotlin.repositories.CustomerRepository
import store.micronaut.kotlin.domain.Customer as DomainCustomer

@Singleton
class CustomerServiceImpl(
    private val customerRepository: CustomerRepository
) : CustomerService {
    override fun create(createCustomerDTO: CreateCustomerDTO): DomainCustomer {
        val customerDao = DomainCustomer()
        customerDao.email = createCustomerDTO.email
        customerDao.name = createCustomerDTO.name
        customerDao.phone = createCustomerDTO.phone
        return if (customerRepository.save(customerDao)) {
            customerDao
        } else {
            throw ResourceNotCreated()
        }
    }
}