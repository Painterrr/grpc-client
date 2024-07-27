package waf.fisa.Woorizip_Requirements.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.devh.boot.grpc.client.channelfactory.GrpcChannelConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import waf.fisa.Woorizip_Requirements.grpcInterceptor.ClientLoggingInterceptor;

@Configuration
public class GrpcClientConfig {

    @Bean
    public GrpcChannelConfigurer globalClientInterceptorConfigurerAdapter() {
        return (channelBuilder, name) -> {
            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                    .usePlaintext()
                    .build();
            channelBuilder.intercept(new ClientLoggingInterceptor());
        };
    }
}