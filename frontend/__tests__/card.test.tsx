import { getByRole, getByTestId, render } from "@testing-library/react";

import MyApp from "../__fixtures__/_app";
import Card from "@/components/Card";

describe("Card", () => {
  const { container } = render(
    <MyApp>
      <Card data-testid="card" />
    </MyApp>
  );
  const card = getByTestId(container, "card");
  const name = getByRole(container, "name");
  const title = getByRole(container, "title");
  const date = getByRole(container, "date");
  const nftImage = getByRole(container, "nftImage");

  it("renders a card", () => {
    expect(card).toBeInTheDocument();
  });
  it("renders a card with name", () => {
    expect(name).toBeInTheDocument();
  });
  it("renders a card with title", () => {
    expect(title).toBeInTheDocument();
  });
  it("renders a card with date", () => {
    expect(date).toBeInTheDocument();
  });
  it("renders a card with NFT", () => {
    expect(nftImage).toBeInTheDocument();
  });
});
