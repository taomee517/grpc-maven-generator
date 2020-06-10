//package com.grpc.demo.server;
//
//import com.aero.laser.api.GreetApi;
//import com.aero.laser.api.GreetServiceGrpc;
//import io.grpc.Server;
//import io.grpc.ServerBuilder;
//import io.grpc.stub.StreamObserver;
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.IOException;
//
///**
// * @author 罗涛
// * @title app
// * @date 2020/5/18 17:50
// */
//@Slf4j
//public class GrpcServer {
//    private Server server;
//
//    public static void main(String[] args) throws Exception{
//        final GrpcServer app = new GrpcServer();
//        app.start();
//        app.blockUntilShutdown();
//    }
//
//    private void start() throws IOException {
//        int port = 50051;
//        //1.forPort 指定监听客户端请求的端口
//        //2.创建我们的服务端实现类的实例GreeterImpl并将传递给构建器的addService方法
//        //3.调用build （）并 start（）在构建器上为我们的服务创建和启动RPC服务器
//        server = ServerBuilder.forPort(port)
//                .addService(new GreeterImpl())
//                .build()
//                .start();
//        log.info("Server stated , listener on port:" + port);
//        //JVM关闭时调用的钩子
//        Runtime.getRuntime().addShutdownHook(new Thread() {
//            @Override
//            public synchronized void start() {
//                System.err.println("*** shutting down gRPC server since JVM is shutting down");
//                GrpcServer.this.stop();
//                System.err.println("*** server shut down");
//            }
//        });
//    }
//
//    private void stop() {
//        if (null != server) {
//            server.shutdown();
//        }
//    }
//
//    /**
//     * Await termination on the main thread since the grpc library uses daemon threads.
//     *
//     * @throws InterruptedException
//     */
//    private void blockUntilShutdown() throws InterruptedException {
//        if (null != server) {
//            server.awaitTermination();
//        }
//    }
//
//    public class GreeterImpl extends GreetServiceGrpc.GreetServiceImplBase {
//        /**
//         * @param request          请求
//         * @param responseObserver 响应观察器
//         */
//        @Override
//        public void sayHello(GreetApi.HelloRequest request,
//                             StreamObserver<GreetApi.HelloReply> responseObserver) {
//            GreetApi.HelloReply reply = GreetApi.HelloReply.newBuilder()
//                    .setMessage("Hello" + request.getName())
//                    .build();
//            //返回 reply数据
//            responseObserver.onNext(reply);
//            //指定完成gRPC的处理
//            responseObserver.onCompleted();
//        }
//    }
//}
