import { MetaMaskInpageProvider } from "@metamask/providers";
import { Contract } from "web3-eth-contract";

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
