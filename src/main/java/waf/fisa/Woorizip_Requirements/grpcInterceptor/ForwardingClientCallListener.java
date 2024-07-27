package waf.fisa.Woorizip_Requirements.grpcInterceptor;

import io.grpc.ClientCall;
import io.grpc.Metadata;
import io.grpc.Status;

public abstract class ForwardingClientCallListener<RespT> extends ClientCall.Listener<RespT> {
    private final ClientCall.Listener<RespT> delegate;

    protected ForwardingClientCallListener(ClientCall.Listener<RespT> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void onMessage(RespT message) {
        delegate.onMessage(message);
    }

    @Override
    public void onHeaders(Metadata headers) {
        delegate.onHeaders(headers);
    }

    @Override
    public void onClose(Status status, Metadata trailers) {
        delegate.onClose(status, trailers);
    }

    @Override
    public void onReady() {
        delegate.onReady();
    }

    public static class SimpleForwardingClientCallListener<RespT> extends ForwardingClientCallListener<RespT> {
        protected SimpleForwardingClientCallListener(ClientCall.Listener<RespT> delegate) {
            super(delegate);
        }
    }
}