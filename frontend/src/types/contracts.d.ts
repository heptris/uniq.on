import { MetaMaskInpageProvider } from "@metamask/providers";
import { Contract, EventData } from "web3-eth-contract";

declare global {
  interface Window {
    ethereum?: MetaMaskInpageProvider;
  }
}

interface UniqonContract {
  methods: {
    create: (
      to: string,
      tokenURI: string,
      repeat: number,
      price: number
    ) => number;
    send: () => {};
  };
}
declare module "web3-eth-contract" {
  export interface Contract extends UniqonContract {}
}

export type Transaction = {
  blockHash: string;
  blockNumber: number;
  contractAddress: string | null;
  cumulativeGasUsed: number;
  effectiveGasPrice: number;
  events: EventData[];
  from: string;
  gasUsed: number;
  logsBloom: string;
  status: boolean;
  to: string;
  transactionHash: string;
  transactionIndex: string;
};
