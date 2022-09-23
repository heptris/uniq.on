import contracts from "@/contracts/utils";

import img from "@/assets/nfts/animals/1.png";
import nft from "@/assets/nfts/1.png";

import Layout from "@/components/Layout";
import Avatar from "@/components/Avatar";
import Text from "@/components/Text";
import Grid from "@/components/Grid";
import NFTItemCard from "@/components/Card/NFTItemCard";

function MyPage() {
  const { account, setAccount } = contracts.useAccount();
  const member = {
    image: img,
    nickname: "Tester",
    walletAddress: account,
  };
  const nfts = [
    {
      nftImage: nft,
      tokenName: "SNKRZ #1243",
      corpName: "test",
      price: 0.99,
      progress: 60,
    },
    {
      nftImage: nft,
      tokenName: "SNKRZ #1243",
      corpName: "test",
      price: 0.99,
      progress: 60,
    },
    {
      nftImage: nft,
      tokenName: "SNKRZ #1243",
      corpName: "test",
      price: 0.99,
      progress: 60,
    },
  ];

  return (
    <Layout>
      <Avatar image={member.image} />
      <Text as="h1">{member.nickname}</Text>
      <Text>{member.walletAddress}</Text>

      <Grid column="double">
        {nfts.map((nft, i) => (
          <NFTItemCard
            key={i}
            nftImage={nft.nftImage}
            tokenName={nft.tokenName}
            corpName={nft.corpName}
            price={nft.price}
            progress={nft.progress}
          />
        ))}
      </Grid>
    </Layout>
  );
}

export default MyPage;
