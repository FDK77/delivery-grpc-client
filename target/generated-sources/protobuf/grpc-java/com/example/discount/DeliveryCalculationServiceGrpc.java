package com.example.discount;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.42.2)",
    comments = "Source: discount.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class DeliveryCalculationServiceGrpc {

  private DeliveryCalculationServiceGrpc() {}

  public static final String SERVICE_NAME = "discount.DeliveryCalculationService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.discount.DeliveryRequest,
      com.example.discount.DeliveryResponse> getCalculateDeliveryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CalculateDelivery",
      requestType = com.example.discount.DeliveryRequest.class,
      responseType = com.example.discount.DeliveryResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.discount.DeliveryRequest,
      com.example.discount.DeliveryResponse> getCalculateDeliveryMethod() {
    io.grpc.MethodDescriptor<com.example.discount.DeliveryRequest, com.example.discount.DeliveryResponse> getCalculateDeliveryMethod;
    if ((getCalculateDeliveryMethod = DeliveryCalculationServiceGrpc.getCalculateDeliveryMethod) == null) {
      synchronized (DeliveryCalculationServiceGrpc.class) {
        if ((getCalculateDeliveryMethod = DeliveryCalculationServiceGrpc.getCalculateDeliveryMethod) == null) {
          DeliveryCalculationServiceGrpc.getCalculateDeliveryMethod = getCalculateDeliveryMethod =
              io.grpc.MethodDescriptor.<com.example.discount.DeliveryRequest, com.example.discount.DeliveryResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CalculateDelivery"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.discount.DeliveryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.discount.DeliveryResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DeliveryCalculationServiceMethodDescriptorSupplier("CalculateDelivery"))
              .build();
        }
      }
    }
    return getCalculateDeliveryMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DeliveryCalculationServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DeliveryCalculationServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DeliveryCalculationServiceStub>() {
        @java.lang.Override
        public DeliveryCalculationServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DeliveryCalculationServiceStub(channel, callOptions);
        }
      };
    return DeliveryCalculationServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DeliveryCalculationServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DeliveryCalculationServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DeliveryCalculationServiceBlockingStub>() {
        @java.lang.Override
        public DeliveryCalculationServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DeliveryCalculationServiceBlockingStub(channel, callOptions);
        }
      };
    return DeliveryCalculationServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DeliveryCalculationServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DeliveryCalculationServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DeliveryCalculationServiceFutureStub>() {
        @java.lang.Override
        public DeliveryCalculationServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DeliveryCalculationServiceFutureStub(channel, callOptions);
        }
      };
    return DeliveryCalculationServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class DeliveryCalculationServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void calculateDelivery(com.example.discount.DeliveryRequest request,
        io.grpc.stub.StreamObserver<com.example.discount.DeliveryResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCalculateDeliveryMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCalculateDeliveryMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.example.discount.DeliveryRequest,
                com.example.discount.DeliveryResponse>(
                  this, METHODID_CALCULATE_DELIVERY)))
          .build();
    }
  }

  /**
   */
  public static final class DeliveryCalculationServiceStub extends io.grpc.stub.AbstractAsyncStub<DeliveryCalculationServiceStub> {
    private DeliveryCalculationServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DeliveryCalculationServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DeliveryCalculationServiceStub(channel, callOptions);
    }

    /**
     */
    public void calculateDelivery(com.example.discount.DeliveryRequest request,
        io.grpc.stub.StreamObserver<com.example.discount.DeliveryResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCalculateDeliveryMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class DeliveryCalculationServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<DeliveryCalculationServiceBlockingStub> {
    private DeliveryCalculationServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DeliveryCalculationServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DeliveryCalculationServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.discount.DeliveryResponse calculateDelivery(com.example.discount.DeliveryRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCalculateDeliveryMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class DeliveryCalculationServiceFutureStub extends io.grpc.stub.AbstractFutureStub<DeliveryCalculationServiceFutureStub> {
    private DeliveryCalculationServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DeliveryCalculationServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DeliveryCalculationServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.discount.DeliveryResponse> calculateDelivery(
        com.example.discount.DeliveryRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCalculateDeliveryMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CALCULATE_DELIVERY = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DeliveryCalculationServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DeliveryCalculationServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CALCULATE_DELIVERY:
          serviceImpl.calculateDelivery((com.example.discount.DeliveryRequest) request,
              (io.grpc.stub.StreamObserver<com.example.discount.DeliveryResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class DeliveryCalculationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DeliveryCalculationServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.discount.DiscountProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DeliveryCalculationService");
    }
  }

  private static final class DeliveryCalculationServiceFileDescriptorSupplier
      extends DeliveryCalculationServiceBaseDescriptorSupplier {
    DeliveryCalculationServiceFileDescriptorSupplier() {}
  }

  private static final class DeliveryCalculationServiceMethodDescriptorSupplier
      extends DeliveryCalculationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DeliveryCalculationServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (DeliveryCalculationServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DeliveryCalculationServiceFileDescriptorSupplier())
              .addMethod(getCalculateDeliveryMethod())
              .build();
        }
      }
    }
    return result;
  }
}
