package com.baloghlan.grpc.server.error;

import com.baloghlan.grpc.server.error.exception.ResourceNotFoundException;
import io.grpc.Status;
import io.grpc.StatusException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class GrpcGlobalExceptionHandler {

    @GrpcExceptionHandler
    public StatusException handleResourceNotFoundException(ResourceNotFoundException exception) {
        Status status = Status.NOT_FOUND.withDescription(exception.getMessage());
        return status.asException();
    }

}
