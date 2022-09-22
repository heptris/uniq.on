package com.example.contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class SsafyNFT extends Contract {
    public static final String BINARY = "0x60806040523480156200001157600080fd5b506040518060400160405280600b81526020017f556e69712e6f6e2d4e46540000000000000000000000000000000000000000008152506040518060400160405280600481526020017f554e465400000000000000000000000000000000000000000000000000000000815250816000908051906020019062000096929190620000c0565b508060019080519060200190620000af929190620000c0565b5050506000600681905550620001d5565b828054620000ce906200019f565b90600052602060002090601f016020900481019282620000f257600085556200013e565b82601f106200010d57805160ff19168380011785556200013e565b828001600101855582156200013e579182015b828111156200013d57825182559160200191906001019062000120565b5b5090506200014d919062000151565b5090565b5b808211156200016c57600081600090555060010162000152565b5090565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b60006002820490506001821680620001b857607f821691505b60208210811415620001cf57620001ce62000170565b5b50919050565b61248080620001e56000396000f3fe608060405234801561001057600080fd5b50600436106100f55760003560e01c806370a0823111610097578063a22cb46511610066578063a22cb46514610298578063b88d4fde146102b4578063c87b56dd146102d0578063e985e9c514610300576100f5565b806370a08231146101fc57806395d89b411461022c5780639fa6a6e31461024a578063a15ab08d14610268576100f5565b8063095ea7b3116100d3578063095ea7b31461017857806323b872dd1461019457806342842e0e146101b05780636352211e146101cc576100f5565b806301ffc9a7146100fa57806306fdde031461012a578063081812fc14610148575b600080fd5b610114600480360381019061010f919061167f565b610330565b60405161012191906116c7565b60405180910390f35b610132610412565b60405161013f919061177b565b60405180910390f35b610162600480360381019061015d91906117d3565b6104a4565b60405161016f9190611841565b60405180910390f35b610192600480360381019061018d9190611888565b6104ea565b005b6101ae60048036038101906101a991906118c8565b610602565b005b6101ca60048036038101906101c591906118c8565b610662565b005b6101e660048036038101906101e191906117d3565b610682565b6040516101f39190611841565b60405180910390f35b6102166004803603810190610211919061191b565b610709565b6040516102239190611957565b60405180910390f35b6102346107c1565b604051610241919061177b565b60405180910390f35b610252610853565b60405161025f9190611957565b60405180910390f35b610282600480360381019061027d9190611aa7565b61085d565b60405161028f9190611957565b60405180910390f35b6102b260048036038101906102ad9190611b2f565b6108b8565b005b6102ce60048036038101906102c99190611c10565b6108ce565b005b6102ea60048036038101906102e591906117d3565b610930565b6040516102f7919061177b565b60405180910390f35b61031a60048036038101906103159190611c93565b6109d5565b60405161032791906116c7565b60405180910390f35b60007f80ac58cd000000000000000000000000000000000000000000000000000000007bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916827bffffffffffffffffffffffffffffffffffffffffffffffffffffffff191614806103fb57507f5b5e139f000000000000000000000000000000000000000000000000000000007bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916827bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916145b8061040b575061040a82610a69565b5b9050919050565b60606000805461042190611d02565b80601f016020809104026020016040519081016040528092919081815260200182805461044d90611d02565b801561049a5780601f1061046f5761010080835404028352916020019161049a565b820191906000526020600020905b81548152906001019060200180831161047d57829003601f168201915b5050505050905090565b60006104af82610ad3565b6004600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b60006104f582610682565b90508073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff161415610566576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161055d90611da6565b60405180910390fd5b8073ffffffffffffffffffffffffffffffffffffffff16610585610b1e565b73ffffffffffffffffffffffffffffffffffffffff1614806105b457506105b3816105ae610b1e565b6109d5565b5b6105f3576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016105ea90611e38565b60405180910390fd5b6105fd8383610b26565b505050565b61061361060d610b1e565b82610bdf565b610652576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161064990611eca565b60405180910390fd5b61065d838383610c74565b505050565b61067d838383604051806020016040528060008152506108ce565b505050565b60008061068e83610f6a565b9050600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415610700576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016106f790611f36565b60405180910390fd5b80915050919050565b60008073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff16141561077a576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161077190611fc8565b60405180910390fd5b600360008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b6060600180546107d090611d02565b80601f01602080910402602001604051908101604052809291908181526020018280546107fc90611d02565b80156108495780601f1061081e57610100808354040283529160200191610849565b820191906000526020600020905b81548152906001019060200180831161082c57829003601f168201915b5050505050905090565b6000600654905090565b600080600161086a610853565b6108749190612017565b90508260076000838152602001908152602001600020908051906020019061089d929190611570565b50806006819055506108b184600654610fa7565b5092915050565b6108ca6108c3610b1e565b83836111c1565b5050565b6108df6108d9610b1e565b83610bdf565b61091e576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161091590611eca565b60405180910390fd5b61092a8484848461132e565b50505050565b606060076000838152602001908152602001600020805461095090611d02565b80601f016020809104026020016040519081016040528092919081815260200182805461097c90611d02565b80156109c95780601f1061099e576101008083540402835291602001916109c9565b820191906000526020600020905b8154815290600101906020018083116109ac57829003601f168201915b50505050509050919050565b6000600560008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16905092915050565b60007f01ffc9a7000000000000000000000000000000000000000000000000000000007bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916827bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916149050919050565b610adc8161138a565b610b1b576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610b1290611f36565b60405180910390fd5b50565b600033905090565b816004600083815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550808273ffffffffffffffffffffffffffffffffffffffff16610b9983610682565b73ffffffffffffffffffffffffffffffffffffffff167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b92560405160405180910390a45050565b600080610beb83610682565b90508073ffffffffffffffffffffffffffffffffffffffff168473ffffffffffffffffffffffffffffffffffffffff161480610c2d5750610c2c81856109d5565b5b80610c6b57508373ffffffffffffffffffffffffffffffffffffffff16610c53846104a4565b73ffffffffffffffffffffffffffffffffffffffff16145b91505092915050565b8273ffffffffffffffffffffffffffffffffffffffff16610c9482610682565b73ffffffffffffffffffffffffffffffffffffffff1614610cea576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610ce1906120df565b60405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415610d5a576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610d5190612171565b60405180910390fd5b610d658383836113cb565b8273ffffffffffffffffffffffffffffffffffffffff16610d8582610682565b73ffffffffffffffffffffffffffffffffffffffff1614610ddb576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610dd2906120df565b60405180910390fd5b6004600082815260200190815260200160002060006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556001600360008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825403925050819055506001600360008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008282540192505081905550816002600083815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550808273ffffffffffffffffffffffffffffffffffffffff168473ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef60405160405180910390a4610f658383836113d0565b505050565b60006002600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415611017576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161100e906121dd565b60405180910390fd5b6110208161138a565b15611060576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161105790612249565b60405180910390fd5b61106c600083836113cb565b6110758161138a565b156110b5576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016110ac90612249565b60405180910390fd5b6001600360008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008282540192505081905550816002600083815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550808273ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef60405160405180910390a46111bd600083836113d0565b5050565b8173ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff161415611230576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401611227906122b5565b60405180910390fd5b80600560008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055508173ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff167f17307eab39ab6107e8899845ad3d59bd9653f200f220920489ca2b5937696c318360405161132191906116c7565b60405180910390a3505050565b611339848484610c74565b611345848484846113d5565b611384576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161137b90612347565b60405180910390fd5b50505050565b60008073ffffffffffffffffffffffffffffffffffffffff166113ac83610f6a565b73ffffffffffffffffffffffffffffffffffffffff1614159050919050565b505050565b505050565b60006113f68473ffffffffffffffffffffffffffffffffffffffff1661155d565b15611550578373ffffffffffffffffffffffffffffffffffffffff1663150b7a0261141f610b1e565b8786866040518563ffffffff1660e01b815260040161144194939291906123bc565b6020604051808303816000875af192505050801561147d57506040513d601f19601f8201168201806040525081019061147a919061241d565b60015b611500573d80600081146114ad576040519150601f19603f3d011682016040523d82523d6000602084013e6114b2565b606091505b506000815114156114f8576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016114ef90612347565b60405180910390fd5b805181602001fd5b63150b7a0260e01b7bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916817bffffffffffffffffffffffffffffffffffffffffffffffffffffffff191614915050611555565b600190505b949350505050565b600080823b905060008111915050919050565b82805461157c90611d02565b90600052602060002090601f01602090048101928261159e57600085556115e5565b82601f106115b757805160ff19168380011785556115e5565b828001600101855582156115e5579182015b828111156115e45782518255916020019190600101906115c9565b5b5090506115f291906115f6565b5090565b5b8082111561160f5760008160009055506001016115f7565b5090565b6000604051905090565b600080fd5b600080fd5b60007fffffffff0000000000000000000000000000000000000000000000000000000082169050919050565b61165c81611627565b811461166757600080fd5b50565b60008135905061167981611653565b92915050565b6000602082840312156116955761169461161d565b5b60006116a38482850161166a565b91505092915050565b60008115159050919050565b6116c1816116ac565b82525050565b60006020820190506116dc60008301846116b8565b92915050565b600081519050919050565b600082825260208201905092915050565b60005b8381101561171c578082015181840152602081019050611701565b8381111561172b576000848401525b50505050565b6000601f19601f8301169050919050565b600061174d826116e2565b61175781856116ed565b93506117678185602086016116fe565b61177081611731565b840191505092915050565b600060208201905081810360008301526117958184611742565b905092915050565b6000819050919050565b6117b08161179d565b81146117bb57600080fd5b50565b6000813590506117cd816117a7565b92915050565b6000602082840312156117e9576117e861161d565b5b60006117f7848285016117be565b91505092915050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b600061182b82611800565b9050919050565b61183b81611820565b82525050565b60006020820190506118566000830184611832565b92915050565b61186581611820565b811461187057600080fd5b50565b6000813590506118828161185c565b92915050565b6000806040838503121561189f5761189e61161d565b5b60006118ad85828601611873565b92505060206118be858286016117be565b9150509250929050565b6000806000606084860312156118e1576118e061161d565b5b60006118ef86828701611873565b935050602061190086828701611873565b9250506040611911868287016117be565b9150509250925092565b6000602082840312156119315761193061161d565b5b600061193f84828501611873565b91505092915050565b6119518161179d565b82525050565b600060208201905061196c6000830184611948565b92915050565b600080fd5b600080fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6119b482611731565b810181811067ffffffffffffffff821117156119d3576119d261197c565b5b80604052505050565b60006119e6611613565b90506119f282826119ab565b919050565b600067ffffffffffffffff821115611a1257611a1161197c565b5b611a1b82611731565b9050602081019050919050565b82818337600083830152505050565b6000611a4a611a45846119f7565b6119dc565b905082815260208101848484011115611a6657611a65611977565b5b611a71848285611a28565b509392505050565b600082601f830112611a8e57611a8d611972565b5b8135611a9e848260208601611a37565b91505092915050565b60008060408385031215611abe57611abd61161d565b5b6000611acc85828601611873565b925050602083013567ffffffffffffffff811115611aed57611aec611622565b5b611af985828601611a79565b9150509250929050565b611b0c816116ac565b8114611b1757600080fd5b50565b600081359050611b2981611b03565b92915050565b60008060408385031215611b4657611b4561161d565b5b6000611b5485828601611873565b9250506020611b6585828601611b1a565b9150509250929050565b600067ffffffffffffffff821115611b8a57611b8961197c565b5b611b9382611731565b9050602081019050919050565b6000611bb3611bae84611b6f565b6119dc565b905082815260208101848484011115611bcf57611bce611977565b5b611bda848285611a28565b509392505050565b600082601f830112611bf757611bf6611972565b5b8135611c07848260208601611ba0565b91505092915050565b60008060008060808587031215611c2a57611c2961161d565b5b6000611c3887828801611873565b9450506020611c4987828801611873565b9350506040611c5a878288016117be565b925050606085013567ffffffffffffffff811115611c7b57611c7a611622565b5b611c8787828801611be2565b91505092959194509250565b60008060408385031215611caa57611ca961161d565b5b6000611cb885828601611873565b9250506020611cc985828601611873565b9150509250929050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b60006002820490506001821680611d1a57607f821691505b60208210811415611d2e57611d2d611cd3565b5b50919050565b7f4552433732313a20617070726f76616c20746f2063757272656e74206f776e6560008201527f7200000000000000000000000000000000000000000000000000000000000000602082015250565b6000611d906021836116ed565b9150611d9b82611d34565b604082019050919050565b60006020820190508181036000830152611dbf81611d83565b9050919050565b7f4552433732313a20617070726f76652063616c6c6572206973206e6f7420746f60008201527f6b656e206f776e6572206f7220617070726f76656420666f7220616c6c000000602082015250565b6000611e22603d836116ed565b9150611e2d82611dc6565b604082019050919050565b60006020820190508181036000830152611e5181611e15565b9050919050565b7f4552433732313a2063616c6c6572206973206e6f7420746f6b656e206f776e6560008201527f72206f7220617070726f76656400000000000000000000000000000000000000602082015250565b6000611eb4602d836116ed565b9150611ebf82611e58565b604082019050919050565b60006020820190508181036000830152611ee381611ea7565b9050919050565b7f4552433732313a20696e76616c696420746f6b656e2049440000000000000000600082015250565b6000611f206018836116ed565b9150611f2b82611eea565b602082019050919050565b60006020820190508181036000830152611f4f81611f13565b9050919050565b7f4552433732313a2061646472657373207a65726f206973206e6f74206120766160008201527f6c6964206f776e65720000000000000000000000000000000000000000000000602082015250565b6000611fb26029836116ed565b9150611fbd82611f56565b604082019050919050565b60006020820190508181036000830152611fe181611fa5565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b60006120228261179d565b915061202d8361179d565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff0382111561206257612061611fe8565b5b828201905092915050565b7f4552433732313a207472616e736665722066726f6d20696e636f72726563742060008201527f6f776e6572000000000000000000000000000000000000000000000000000000602082015250565b60006120c96025836116ed565b91506120d48261206d565b604082019050919050565b600060208201905081810360008301526120f8816120bc565b9050919050565b7f4552433732313a207472616e7366657220746f20746865207a65726f2061646460008201527f7265737300000000000000000000000000000000000000000000000000000000602082015250565b600061215b6024836116ed565b9150612166826120ff565b604082019050919050565b6000602082019050818103600083015261218a8161214e565b9050919050565b7f4552433732313a206d696e7420746f20746865207a65726f2061646472657373600082015250565b60006121c76020836116ed565b91506121d282612191565b602082019050919050565b600060208201905081810360008301526121f6816121ba565b9050919050565b7f4552433732313a20746f6b656e20616c7265616479206d696e74656400000000600082015250565b6000612233601c836116ed565b915061223e826121fd565b602082019050919050565b6000602082019050818103600083015261226281612226565b9050919050565b7f4552433732313a20617070726f766520746f2063616c6c657200000000000000600082015250565b600061229f6019836116ed565b91506122aa82612269565b602082019050919050565b600060208201905081810360008301526122ce81612292565b9050919050565b7f4552433732313a207472616e7366657220746f206e6f6e20455243373231526560008201527f63656976657220696d706c656d656e7465720000000000000000000000000000602082015250565b60006123316032836116ed565b915061233c826122d5565b604082019050919050565b6000602082019050818103600083015261236081612324565b9050919050565b600081519050919050565b600082825260208201905092915050565b600061238e82612367565b6123988185612372565b93506123a88185602086016116fe565b6123b181611731565b840191505092915050565b60006080820190506123d16000830187611832565b6123de6020830186611832565b6123eb6040830185611948565b81810360608301526123fd8184612383565b905095945050505050565b60008151905061241781611653565b92915050565b6000602082840312156124335761243261161d565b5b600061244184828501612408565b9150509291505056fea26469706673582212209923ad3f65bb9ca7be8df717cf57bdfd1ba62b73a48093d01a89eb998554904f64736f6c634300080a0033";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_GETAPPROVED = "getApproved";

    public static final String FUNC_ISAPPROVEDFORALL = "isApprovedForAll";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OWNEROF = "ownerOf";

    public static final String FUNC_safeTransferFrom = "safeTransferFrom";

    public static final String FUNC_SETAPPROVALFORALL = "setApprovalForAll";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_CURRENT = "current";

    public static final String FUNC_TOKENURI = "tokenURI";

    public static final String FUNC_CREATE = "create";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event APPROVALFORALL_EVENT = new Event("ApprovalForAll", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bool>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event CREATENFT_EVENT = new Event("createNFT", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("1337", "0x6F41b06946AF120a011422e7131C6866dE87eE83");
        _addresses.put("5777", "0x2D08c1EC98d19562C0a42AB7d3F1Ff8563A5a407");
    }

    @Deprecated
    protected SsafyNFT(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SsafyNFT(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SsafyNFT(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SsafyNFT(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.approved = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.approved = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public List<ApprovalForAllEventResponse> getApprovalForAllEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVALFORALL_EVENT, transactionReceipt);
        ArrayList<ApprovalForAllEventResponse> responses = new ArrayList<ApprovalForAllEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalForAllEventResponse>() {
            @Override
            public ApprovalForAllEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVALFORALL_EVENT, log);
                ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVALFORALL_EVENT));
        return approvalForAllEventFlowable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public List<CreateNFTEventResponse> getCreateNFTEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CREATENFT_EVENT, transactionReceipt);
        ArrayList<CreateNFTEventResponse> responses = new ArrayList<CreateNFTEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CreateNFTEventResponse typedResponse = new CreateNFTEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._tokenId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._owner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CreateNFTEventResponse> createNFTEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, CreateNFTEventResponse>() {
            @Override
            public CreateNFTEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CREATENFT_EVENT, log);
                CreateNFTEventResponse typedResponse = new CreateNFTEventResponse();
                typedResponse.log = log;
                typedResponse._tokenId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._owner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CreateNFTEventResponse> createNFTEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CREATENFT_EVENT));
        return createNFTEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getApproved(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETAPPROVED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> isApprovedForAll(String owner, String operator) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISAPPROVEDFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(owner), 
                new org.web3j.abi.datatypes.Address(operator)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> ownerOf(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNEROF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_safeTransferFrom, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(from), 
                new org.web3j.abi.datatypes.Address(to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId, byte[] data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_safeTransferFrom, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(from), 
                new org.web3j.abi.datatypes.Address(to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId), 
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setApprovalForAll(String operator, Boolean approved) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETAPPROVALFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(operator), 
                new org.web3j.abi.datatypes.Bool(approved)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(from), 
                new org.web3j.abi.datatypes.Address(to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> current() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CURRENT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> tokenURI(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKENURI, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> create(String to, String _tokenURI) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(to), 
                new org.web3j.abi.datatypes.Utf8String(_tokenURI)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static SsafyNFT load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SsafyNFT(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SsafyNFT load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SsafyNFT(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SsafyNFT load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SsafyNFT(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SsafyNFT load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SsafyNFT(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SsafyNFT> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SsafyNFT.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<SsafyNFT> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SsafyNFT.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SsafyNFT> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SsafyNFT.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SsafyNFT> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SsafyNFT.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String approved;

        public BigInteger tokenId;
    }

    public static class ApprovalForAllEventResponse extends BaseEventResponse {
        public String owner;

        public String operator;

        public Boolean approved;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger tokenId;
    }

    public static class CreateNFTEventResponse extends BaseEventResponse {
        public BigInteger _tokenId;

        public String _owner;
    }
}
