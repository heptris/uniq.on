import { useEffect, useState } from "react";
import Web3 from "web3";
import { Contract } from "web3-eth-contract";

import type { AbiItem } from "web3-utils";
import type { Maybe } from "@metamask/providers/dist/utils";

const contracts = {
  useWeb3: () => {
    const [web3, setWeb3] = useState<Web3>();
    const [mintUniqonTokenContract, setMintUniqonTokenContract] =
      useState<Contract>();
    const [saleUniqonTokenContract, setSaleUniqonTokenContract] =
      useState<Contract>();

    const getWeb3 = () => {
      if (!window.ethereum) {
        alert("Metamask를 설치해주세요!");
        throw new Error("Metamask is not installed.");
      }

      setWeb3(new Web3(window.ethereum as any));
    };

    const getMint = (networkId: number) => {
      if (!web3) return;

      const mintContractJSON = require("./MintUniqonToken.json");
      const mintUniqonTokenContractAbi: AbiItem[] = mintContractJSON.abi;
      const mintUniqonTokenContractAccount: string =
        mintContractJSON.networks[networkId].address;
      const contractInstance = new web3.eth.Contract(
        mintUniqonTokenContractAbi,
        mintUniqonTokenContractAccount
      );
      setMintUniqonTokenContract(contractInstance);
    };

    const getSale = (networkId: number) => {
      if (!web3) return;

      const saleContractJSON = require("./SaleUniqonToken.json");
      const saleUniqonTokenContractAbi: AbiItem[] = saleContractJSON.abi;
      const saleUniqonTokenContractAccount: string =
        saleContractJSON.networks[networkId].address;
      const contractInstance = new web3.eth.Contract(
        saleUniqonTokenContractAbi,
        saleUniqonTokenContractAccount
      );
      setSaleUniqonTokenContract(contractInstance);
    };

    useEffect(() => {
      if (!web3) getWeb3();
      else {
        (async () => {
          const networkId: number = await web3.eth.net.getId();
          getMint(networkId);
          getSale(networkId);
        })();
      }
    }, [web3]);

    return { web3, mintUniqonTokenContract, saleUniqonTokenContract };
  },

  useAccount: () => {
    const [account, _setAccount] = useState("");

    const connect = () => {
      if (!window.ethereum) {
        alert("Metamask를 설치해주세요!");
        throw new Error("Metamask is not installed.");
      }

      return window.ethereum
        .request({
          method: "eth_requestAccounts",
        })
        .then(handleAccountsChanged)
        .catch((error) => {
          console.error(error);
        });
    };

    const checkConnection = () => {
      if (!window.ethereum) {
        alert("Metamask를 설치해주세요!");
        throw new Error("Metamask is not installed.");
      }
      return window.ethereum
        .request({ method: "eth_accounts" })
        .then(handleAccountsChanged)
        .catch(console.error);
    };

    function handleAccountsChanged(response: Maybe<unknown>) {
      if (!(response && Array.isArray(response))) return;

      // 0이면 지갑 미연결 상태
      if (response.length === 0) return false;
      // 지갑 연결되어 잇으면 공개주소를 account에 넣고 연결되어 있음 반환
      else {
        _setAccount(response[0]);
        return true;
      }
    }

    return { account, connect, checkConnection };
  },
};

export default contracts;
