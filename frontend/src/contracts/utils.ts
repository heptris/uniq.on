import { useEffect, useState } from "react";
import Web3 from "web3";
import { Contract } from "web3-eth-contract";

import { NFTStorage } from "nft.storage";

import type { AbiItem } from "web3-utils";
import type { Maybe } from "@metamask/providers/dist/utils";
import type { StoreNFTParams } from "@/types/api_requests";
import { useAppDispatch, useAppSelector } from "@/hooks";
import { _setAccount } from "@/store";

const contracts = {
  APIToken: process.env.NFT_STORAGE_API_TOKEN,

  storeNFT: async (params: StoreNFTParams) => {
    const { startupId, nftImage, startupName, price } = params;
    if (!contracts.APIToken || !nftImage) return;

    const description = `${startupId}::${price}`;
    const nftstorage = new NFTStorage({ token: contracts.APIToken });

    return nftstorage.store({
      image: nftImage,
      name: startupName,
      description,
    });
  },

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

  useContract: () => {
    const { account } = contracts.useAccount();
    const { web3, mintUniqonNFTContract, uniqonTokenContract } =
      contracts.useWeb3();

    const checkBalance = async () => {
      if (!mintUniqonNFTContract || !uniqonTokenContract) return;
      const res = await uniqonTokenContract.methods.balanceOf(account).call();
      console.log(res);
    };
    const buyToken = async (tokenId: number) => {
      if (!mintUniqonNFTContract || !uniqonTokenContract) return;
      const res = new Array(3);

      res[0] = await uniqonTokenContract.methods.balanceOf(account).call();
      res[1] = await uniqonTokenContract.methods
        .approve("0xb619e5e1dF0E6295346949F4393a7e54a1500B3D", res[0])
        .send({ from: account });
      if (!res[0] || !res[1]) {
        alert("토큰 잔액이 부족합니다.");
        return;
      }

      res[2] = await mintUniqonNFTContract.methods
        .purchaseUniqonToken(tokenId)
        .send({ from: account });
      console.log(res);
    };
    const mintToken = async ({
      tokenURI,
      totalAmount,
      price,
    }: {
      tokenURI: string;
      totalAmount: number;
      price: number;
    }) => {
      if (!mintUniqonNFTContract || !uniqonTokenContract) return;
      const res = await mintUniqonNFTContract.methods
        .create(account, tokenURI, totalAmount, price)
        .send({ from: account });
      console.log(res);
    };

    return { checkBalance, buyToken, mintToken };
  },
};

export default contracts;
