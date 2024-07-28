package waf.fisa.Woorizip_Requirements.grpcInterceptor;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientLoggingInterceptor implements ClientInterceptor {

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
            MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
        log.info("** Calling method: {}", method.getFullMethodName());

        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)) {
            @Override
            public void start(ClientCall.Listener<RespT> responseListener, Metadata headers) {
                super.start(new ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT>(responseListener) {
                    @Override
                    public void onMessage(RespT message) {
                        log.info("** Received response: {}", message);
                        super.onMessage(message);
                    }
                }, headers);
            }

            @Override
            public void sendMessage(ReqT message) {
                log.info("** Sending request: {}", message);
                super.sendMessage(message);
            }
        };
    }
}