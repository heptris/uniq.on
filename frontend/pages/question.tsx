import Button from "@/components/Button";
import contracts from "@/contracts/utils";

export default function Question() {
  const { checkBalance, buyToken, mintToken } = contracts.useContract();

  return (
    <>
      <Button onClick={() => checkBalance()}>Check balance</Button>
      <Button onClick={() => buyToken(22)}>Buy!</Button>
      <Button
        onClick={() =>
          mintToken({ tokenURI: "123123", totalAmount: 5, price: 1 })
        }
      >
        Mint!
      </Button>
    </>
  );
}
