package com.ecom.billingservice.Web;

import com.ecom.billingservice.Entities.Bill;
import com.ecom.billingservice.Repository.BillRepository;
import com.ecom.billingservice.Repository.ProductItemRepository;
import com.ecom.billingservice.ServicesCommunication.CustomerRestClient;
import com.ecom.billingservice.ServicesCommunication.ProductRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BillController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerRestClient customerRestClient;
    @Autowired
    private ProductRestClient productRestClient;

    @GetMapping("/fullBill/{id}")
    public Bill findBillById(@PathVariable Long id){
        Bill bill=billRepository.findById(id).orElseThrow(()->new RuntimeException("bill not found"));
        bill.setCustomer(customerRestClient.findCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(i->{
            i.setProduct(productRestClient.findProductById(i.getProductId()));
        });
        return bill;
    }

}
