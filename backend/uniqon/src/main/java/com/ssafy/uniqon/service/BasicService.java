package com.ssafy.uniqon.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

import com.example.MintUniqonToken;
import org.springframework.stereotype.Service;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;

@Service
public class BasicService{

    private EthereumService ethereumService;

    public BasicService(EthereumService ethereumService)
    {
        this.ethereumService = ethereumService;
    }

    public int getPot() throws IOException, ExecutionException, InterruptedException {

        // 1. 호출하고자 하는 function 세팅[functionName, parameters]
        Function function = new Function("getPot",
                Collections.emptyList(),
                Arrays.asList(new TypeReference<Uint256>() {}));

        // 2. ethereum을 function 변수로 통해 호출
        return ((BigInteger)ethereumService.ethCall(function)).intValue();
    }
    public int current() throws IOException, ExecutionException, InterruptedException {

        // 1. 호출하고자 하는 function 세팅[functionName, parameters]
        Function function = new Function("current",
                Collections.emptyList(),
                Arrays.asList(new TypeReference<Uint256>() {}));

        // 2. ethereum을 function 변수로 통해 호출
        return ((BigInteger) ethereumService.ethCall(function)).intValue();
    }
    public String tokenURI(int tokenId) throws IOException, ExecutionException, InterruptedException {

        // 1. 호출하고자 하는 function 세팅[functionName, parameters]
        Function function = new Function("tokenURI",
                Arrays.asList(new Uint256(tokenId)),
                new ArrayList<>());

        // 2. ethereum을 function 변수로 통해 호출
        return (ethereumService.ethCall(function)).toString();
    }

    public void setPot(int num) throws IOException, ExecutionException, InterruptedException {
        // 1. 호출하고자 하는 function 세팅[functionName, parameters]
        Function function = new Function("setPot",
                Arrays.asList(new Uint256(num)),
                Collections.emptyList());

        // 2. sendTransaction
        String txHash = ethereumService.ethSendTransaction(function);

        // 3. getReceipt
        TransactionReceipt receipt = ethereumService.getReceipt(txHash);
        System.out.println("receipt = " + receipt);
    }
    public void create(String toAddress, String tokenURI) throws IOException, ExecutionException, InterruptedException {
        // 1. 호출하고자 하는 function 세팅[functionName, parameters]
        Function function = new Function("create",
                Arrays.asList(new Address(toAddress), new Utf8String(tokenURI)),
                Arrays.asList(new TypeReference<Uint256>() {}));

        // 2. sendTransaction
        String txHash = ethereumService.ethSendTransaction(function);

        // 3. getReceipt
        TransactionReceipt receipt = ethereumService.getReceipt(txHash);
        System.out.println("receipt = " + receipt);
    }

    void balanceOf() throws Exception {

        String ERC20_CONTRACT_ADDRESS = "0x0622875b58bC12de953D3F1fb7C8Ef32Cc46CCfD";
        String USER_ADDRESS = "0xF7246cf0D87361C013139837DF2b4D68DF482F70";
        String USER_PRIVATE_KEY = "711a21bdca78391d9ae4b311adc1e9002afe3d458d9c4d63b1050516c12dde31"; // 사용자의 개인키
        long TX_END_CHECK_DURATION = 3000;
        int TX_END_CHECK_RETRY = 3;

        // 로컬 환경에서 프라이빗 네트워크를 띄운 상태에서 진행한다.
        Web3j web3j = Web3j.build(new HttpService("http://localhost:7545"));
        Credentials credential = Credentials.create(USER_PRIVATE_KEY);
        ContractGasProvider gasProvider = new DefaultGasProvider();
        FastRawTransactionManager manager = new FastRawTransactionManager(
                web3j,
                credential,
                new PollingTransactionReceiptProcessor(web3j, TX_END_CHECK_DURATION, TX_END_CHECK_RETRY)
        );

        // 위에서 생성한 Wrapper의 이름 SsafyNFT
        MintUniqonToken erc20 = MintUniqonToken.load(ERC20_CONTRACT_ADDRESS, web3j, manager, gasProvider);
        BigInteger balance = erc20.balanceOf(USER_ADDRESS).send();

        System.out.println(balance);
    }

    void transfer() throws Exception {
        String ERC20_CONTRACT_ADDRESS = "0x0622875b58bC12de953D3F1fb7C8Ef32Cc46CCfD";

        // ERC20을 보내는 사용자
        // 내가 상대방에게 보내는 상황이다.
        String USER_ADDRESS = "0xF7246cf0D87361C013139837DF2b4D68DF482F70";
        String USER_PRIVATE_KEY = "711a21bdca78391d9ae4b311adc1e9002afe3d458d9c4d63b1050516c12dde31";

        // ERC20을 받는 사용자
        String RECEIVER_ADDRESS = "0xca889D93C95De06D4ab224E13e2Dee84B96c2Bab";

        // 트랜잭션 처리에 대한 변수값 -> 기본적으로 3000, 3을 할당한다.
        // 네트워크 상황에 따라 변경가능하다.
        long TX_END_CHECK_DURATION = 3000;
        int TX_END_CHECK_RETRY = 3;

        // 이전에 만든 프라이빗 네트워크에서 사용한 CHAIN ID가 필요하다.
        // 트랜잭션을 블록에 작성하는 경우에는 필수적이나, 조회하는 경우에는 써도 되고 안써도 된다.
        long CHAIN_ID = 1337;

        String amount = "10"; // ERC20 10개를 RECEIVER_ADDRESS에 전송

        // 로컬 환경에서 프라이빗 네트워크를 띄운 상태에서 진행한다.
        Web3j web3j = Web3j.build(new HttpService("http://localhost:7545"));
        Credentials credential = Credentials.create(USER_PRIVATE_KEY);
        ContractGasProvider gasProvider = new DefaultGasProvider();
        FastRawTransactionManager manager = new FastRawTransactionManager(
                web3j,
                credential,
                CHAIN_ID,
                new PollingTransactionReceiptProcessor(web3j, TX_END_CHECK_DURATION, TX_END_CHECK_RETRY)
        );

        MintUniqonToken erc20 = MintUniqonToken.load(ERC20_CONTRACT_ADDRESS, web3j, manager, gasProvider);
        erc20.transferFrom(USER_ADDRESS, RECEIVER_ADDRESS, new BigInteger(amount)).send();
    }

}