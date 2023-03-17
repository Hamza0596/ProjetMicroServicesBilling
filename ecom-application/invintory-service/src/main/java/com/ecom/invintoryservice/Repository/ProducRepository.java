package com.ecom.invintoryservice.Repository;

import com.ecom.invintoryservice.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProducRepository extends JpaRepository<Product, Long> {
}
