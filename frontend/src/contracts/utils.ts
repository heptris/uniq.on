import { useEffect, useState } from "react";
import Web3 from "web3";
import { Contract } from "web3-eth-contract";

import type { AbiItem } from "web3-utils";
import type { Maybe } from "@metamask/providers/dist/utils";
import { useAppDispatch, useAppSelector } from "@/hooks";
import { _setAccount } from "@/store";

const contracts = {
  useWeb3: () => {
    const [web3, setWeb3] = useState<Web3>();
    const [mintUniqonNFTContract, setMintUniqonNFTContract] =
      useState<Contract>();
    const [uniqonTokenContract, setUniqonTokenContract] = useState<Contract>();

    const getWeb3 = () => {
      if (!window.ethereum) {
        alert("MetaMask를 설치해주세요!");
        throw new Error("MetaMask is not installed.");
      }

      setWeb3(new Web3(window.ethereum as any));
    };

    const getMint = (networkId: number) => {
      if (!web3) return;

      const mintContractJSON = require("./MintUniqonToken.json");
      const mintUniqonNFTContractAbi: AbiItem[] = mintContractJSON.abi;
      const mintUniqonNFTContractAccount: string =
        mintContractJSON.networks[networkId].address;
      const contractInstance = new web3.eth.Contract(
        mintUniqonNFTContractAbi,
        mintUniqonNFTContractAccount
      );
      setMintUniqonNFTContract(contractInstance);
    };

    const getToken = (networkId: number) => {
      if (!web3) return;

      const tokenContractJSON = require("./UniqonToken.json");
      const uniqonTokenContractAbi: AbiItem[] = tokenContractJSON.abi;
      const uniqonTokenContractAccount: string =
        tokenContractJSON.networks[networkId].address;
      const contractInstance = new web3.eth.Contract(
        uniqonTokenContractAbi,
        uniqonTokenContractAccount
      );
      setUniqonTokenContract(contractInstance);
    };

    useEffect(() => {
      if (!web3) getWeb3();
      else {
        (async () => {
          const networkId: number = await web3.eth.net.getId();
          getMint(networkId);
          getToken(networkId);
        })();
      }
    }, [web3]);

    return { web3, mintUniqonNFTContract, uniqonTokenContract };
  },

  useAccount: () => {
    const { walletAddress: account } = useAppSelector((state) => state.auth);
    const dispatch = useAppDispatch();

    const connect = async () => {
      if (!window.ethereum) {
        alert("MetaMask를 설치해주세요!");
        throw new Error("MetaMask is not installed.");
      }

      return await window.ethereum
        .request({
          method: "eth_requestAccounts",
        })
        .then(handleAccountsChanged)
        .catch((error) => {
          console.error(error);
        });
    };

    const checkConnection = async () => {
      if (!window.ethereum) {
        alert("Metamask를 설치해주세요!");
        throw new Error("Metamask is not installed.");
      }
      return await window.ethereum
        .request({ method: "eth_accounts" })
        .then(handleAccountsChanged)
        .catch(console.error);
    };

    function handleAccountsChanged(response: Maybe<unknown>) {
      if (!(response && Array.isArray(response))) return;

      // 0이면 지갑 미연결 상태
      if (response.length === 0) return false;
      // 지갑 연결되어 있으면 공개주소를 account에 넣고 연결되어 있음 반환
      else {
        dispatch(_setAccount(response[0]));
        return true;
      }
    }

    return { account, connect, checkConnection };
  },
};

export default contracts;
