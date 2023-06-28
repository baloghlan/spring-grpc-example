package com.baloghlan.grpc.client.service;

import com.baloghlan.grpc.client.error.exception.ResourceNotFoundException;
import com.baloghlan.grpc.client.model.CustomerResponse;
import com.baloghlan.grpc.customer.CustomerGetRequest;
import com.baloghlan.grpc.customer.CustomerServiceGrpc;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @GrpcClient("customer-grpc-service")
    private CustomerServiceGrpc.CustomerServiceBlockingStub customerServiceStub;

    public CustomerResponse getCustomer(String id) {
        CustomerGetRequest customerGetRequest = CustomerGetRequest.newBuilder().setId(id).build();
        try {
            com.baloghlan.grpc.customer.CustomerResponse customer = customerServiceStub.getCustomer(customerGetRequest);
            return convertToResponse(customer);
        } catch (StatusRuntimeException exception) {
            if (Status.NOT_FOUND.equals(exception.getStatus())) {
                throw new ResourceNotFoundException("Customer not found by id " + id);
            }
            throw new RuntimeException();
        }


    }

    private CustomerResponse convertToResponse(com.baloghlan.grpc.customer.CustomerResponse customer) {
        return new CustomerResponse(customer.getId(), customer.getFullName());
    }
}
