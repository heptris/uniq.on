package com.ssafy.uniqon.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Component;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

@Component
public class EthereumService {

    private String from = "0xdb264f26F89c23CA82e5fEe8Fb54c4645bC41581";

    private String contract = "0xd9145CCE52D386f254917e481eB44e9943F39138";

    // hardcording because of testing
    private String pwd = "2e3dda448ce268be6bffeb7274a34cde626b5e2589f1b3bcda1a6b5e654b2a48";

    private Admin web3j = null;

    public EthereumService()
    {
        web3j = Admin.build(new HttpService("http://localhost:7545")); // default server : http://localhost:8545
    }

    public Object ethCall(Function function) throws IOException {
        // 1. Account Lock 해제
        PersonalUnlockAccount personalUnlockAccount = web3j.personalUnlockAccount(from, pwd).send();

        if (personalUnlockAccount.accountUnlocked()) { // unlock 일때

            //2. transaction 제작
            Transaction transaction = Transaction.createEthCallTransaction(from, contract,
                    FunctionEncoder.encode(function));

            //3. ethereum 호출 후 결과 가져오기
            EthCall ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();
            //4. 결과값 decode
            List<Type> decode = FunctionReturnDecoder.decode(ethCall.getResult(),
                    function.getOutputParameters());

            System.out.println("ethCall.getResult() = " + ethCall.getResult());
            System.out.println("getValue = " + decode.get(0).getValue());
            System.out.println("getType = " + decode.get(0).getTypeAsString());

            return decode.get(0).getValue();
        }
        else
        {
            throw new PersonalLockException("check ethereum personal Lock");
        }
    }

    public String ethSendTransaction(Function function)
            throws IOException, InterruptedException, ExecutionException {

        // 1. Account Lock 해제
//        PersonalUnlockAccount personalUnlockAccount = web3j.personalUnlockAccount(from, pwd).send();

//        if (personalUnlockAccount.accountUnlocked()) { // unlock 일때

            //2. account에 대한 nonce값 가져오기.
            EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                    from, DefaultBlockParameterName.LATEST).sendAsync().get();

            BigInteger nonce = ethGetTransactionCount.getTransactionCount();

            //3. Transaction값 제작
            Transaction transaction = Transaction.createFunctionCallTransaction(from, nonce,
                    Transaction.DEFAULT_GAS,
                    null, contract,
                    FunctionEncoder.encode(function));

            // 4. ethereum Call &
            EthSendTransaction ethSendTransaction = web3j.ethSendTransaction(transaction).send();

            // transaction에 대한 transaction Hash값 얻기.
            String transactionHash = ethSendTransaction.getTransactionHash();

            // ledger에 쓰여지기 까지 기다리기.
            Thread.sleep(5000);

            return transactionHash;
//        }
//        else {
//            throw new PersonalLockException("check ethereum personal Lock");
//        }
    }

    public TransactionReceipt getReceipt(String transactionHash) throws IOException {

        EthGetTransactionReceipt transactionReceipt = web3j.ethGetTransactionReceipt(transactionHash).send();

        if(transactionReceipt.getTransactionReceipt().isPresent())
        {
            System.out.println("transactionReceipt.getResult().getContractAddress() = " +
                    transactionReceipt.getResult());
        }
        else
        {
            System.out.println("transaction complete not yet");
        }

        return transactionReceipt.getResult();
    }

    private class PersonalLockException extends RuntimeException
    {
        public PersonalLockException(String msg)
        {
            super(msg);
        }
    }

}