import { useEffect, useState } from "react";
import Web3 from "web3";
import { Contract } from "web3-eth-contract";
import { AbiItem } from "web3-utils";

const contracts = {
  useWeb3: () => {
    const [web3, setWeb3] = useState<Web3>();
    const [mintUniqonTokenContract, setMintUniqonTokenContract] =
      useState<Contract>();
    const [saleUniqonTokenContract, setSaleUniqonTokenContract] =
      useState<Contract>();

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

    const setAccount = async () => {
      if (!window.ethereum) {
        alert("MetaMask를 설치해주세요!");
        throw new Error("MetaMask is not installed.");
      }

      const accounts = await window.ethereum
        .request({
          method: "eth_requestAccounts",
        })
        .catch((error) => {
          console.error(error);
        });
      if (!(accounts && Array.isArray(accounts))) return;

      _setAccount(accounts[0]);
    };

    useEffect(() => {
      setAccount();
    }, []);

    return { account, setAccount };
  },
};

export default contracts;
