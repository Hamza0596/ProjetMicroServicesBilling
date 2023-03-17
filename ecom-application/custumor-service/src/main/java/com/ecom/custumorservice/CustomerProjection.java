package com.ecom.custumorservice;

import com.ecom.custumorservice.Entities.Customer;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullCustomer", types = Customer.class )
public interface CustomerProjection {
    public String getName();
}
