import { getByTestId, render } from "@testing-library/react";

import MyApp from "../__fixtures__/_app";
import Card from "@/components/Card";
import CorporationCard from "@/components/Card/CorporationCard";
import NFTItemCard from "@/components/Card/NFTItemCard";

import nft from "@/assets/nfts/1.png";
import corp from "@/assets/corps/1.png";

describe("Card", () => {
  const { container } = render(
    <MyApp>
      <Card data-testid="card" />
    </MyApp>
  );
  const card = getByTestId(container, "card");

  it("renders a card", () => {
    expect(card).toBeInTheDocument();
  });
  it("renders a CorporationCard", () => {
    const { container } = render(
      <MyApp>
        <CorporationCard
          data-testid="corporation-card"
          corpName={"Tester"}
          corpAvatar={corp}
          title={"testTitle"}
          date={"testDate"}
          progress={38}
        />
      </MyApp>
    );
    const corporationCard = getByTestId(container, "corporation-card");

    expect(corporationCard).toBeInTheDocument();
    expect(corporationCard).toHaveTextContent("Tester");
    expect(corporationCard).toHaveTextContent("testTitle");
    expect(corporationCard).toHaveTextContent("testDate");
  });
  it("renders a NFTItemCard", () => {
    const { container } = render(
      <MyApp>
        <NFTItemCard
          data-testid="nft-item-card"
          nftImage={nft}
          tokenId={199}
          corpName={"Tester"}
          price={1}
          status={"망함"}
          progress={38}
        />
      </MyApp>
    );
    const nftItemCard = getByTestId(container, "nft-item-card");

    expect(nftItemCard).toBeInTheDocument();
    expect(nftItemCard).toHaveTextContent("Tester #199");
    expect(nftItemCard).toHaveTextContent("Tester");
    // expect(nftItemCard).toHaveTextContent("망함");
    expect(nftItemCard).toHaveTextContent("1");
  });
});
