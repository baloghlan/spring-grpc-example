package com.baloghlan.grpc.server.service;

import com.baloghlan.grpc.customer.CustomerGetRequest;
import com.baloghlan.grpc.customer.CustomerResponse;
import com.baloghlan.grpc.customer.CustomerServiceGrpc;
import com.baloghlan.grpc.server.error.exception.ResourceNotFoundException;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@GrpcService
public class CustomerService extends CustomerServiceGrpc.CustomerServiceImplBase {

    public final Map<String, CustomerResponse> customers;

    public CustomerService() {
        customers = getDefaultCustomers();
    }

    @Override
    public void getCustomer(CustomerGetRequest request, StreamObserver<CustomerResponse> responseObserver) {
        CustomerResponse customerResponse = customers.get(request.getId());

        if (customerResponse == null) {
            throw new ResourceNotFoundException("Customer not found by id " + request.getId());
        }
        responseObserver.onNext(customerResponse);
        responseObserver.onCompleted();
    }


    public Map<String, CustomerResponse> getDefaultCustomers() {
        Map<String, CustomerResponse> customers = new HashMap<>();
        customers.put("1", CustomerResponse.newBuilder().setId("1").setFullName("TestCustomer1").build());
        customers.put("2", CustomerResponse.newBuilder().setId("2").setFullName("TestCustomer2").build());
        customers.put("3", CustomerResponse.newBuilder().setId("3").setFullName("TestCustomer3").build());
        return customers;
    }
}
