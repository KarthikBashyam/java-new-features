package com.example.virtualthreads;

import jdk.incubator.concurrent.StructuredTaskScope;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class StructureConcurrency {

    public void shutdownOnFailure() {

        try(var scope = new StructuredTaskScope.ShutdownOnFailure()) {

            Future<String> serviceA = scope.fork(() -> callServiceA());
            Future<String> serviceB = scope.fork(() -> callServiceB());

            scope.join();
            scope.throwIfFailed();

            System.out.println("Response:" + serviceA.get() + " - "+ serviceB.get());

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String callServiceA() {
        return "Response from Service A";
    }

    private String callServiceB() {
        return "Response from Service B";
    }

}
