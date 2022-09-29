import Button from "@/components/Button";
import contracts from "@/contracts/utils";

export default function Question() {
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

  return (
    <>
      <Button onClick={() => checkBalance()}>Check balance</Button>
      <Button
        onClick={() =>
          mintToken({ tokenURI: "123123", totalAmount: 5, price: 1 })
        }
      >
        Mint!
      </Button>
      <Button onClick={() => buyToken(22)}>Buy!</Button>
    </>
  );
}
