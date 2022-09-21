package com.ssafy.uniqon;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

@SpringBootTest
public class EthServiceTest {
    @Test
    public void getEthClientVersionSync() throws Exception{
        Web3j web3j = Web3j.build(new HttpService("http://localhost:7545"));
        Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
        System.out.println(web3ClientVersion.getWeb3ClientVersion());
    }
}
