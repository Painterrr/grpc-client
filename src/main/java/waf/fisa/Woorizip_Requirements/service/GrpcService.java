package waf.fisa.Woorizip_Requirements.service;

import com.example.grpcinterface.lib.AccountIdReq;
import com.example.grpcinterface.lib.PhoneResp;
import com.example.grpcinterface.lib.RequirementAccountServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class GrpcService {

    @GrpcClient("requirement")
    private RequirementAccountServiceGrpc.RequirementAccountServiceBlockingStub stub;

    public String getPhone(final String accountId) {
        try {
            PhoneResp resp = this.stub.getPhone(AccountIdReq.newBuilder().setAccountId(accountId).build());
            return resp.getPhone();
        } catch (Exception e) {
            return "Failed to retrieve phone: " + e.getMessage();
        }
    }
}
