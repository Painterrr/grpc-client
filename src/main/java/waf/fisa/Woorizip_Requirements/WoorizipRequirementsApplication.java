package waf.fisa.Woorizip_Requirements;

import io.grpc.ClientInterceptors;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import waf.fisa.Woorizip_Requirements.grpcInterceptor.ClientLoggingInterceptor;

@SpringBootApplication
public class WoorizipRequirementsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WoorizipRequirementsApplication.class, args);
	}

}
