import { getByTestId, render } from "@testing-library/react";

import img from "../assets/nfts/1.png";

import MyApp from "../__fixtures__/_app";
import Avatar from "@/components/Avatar";
import { uniqonThemes } from "@/styles/theme";

describe("Avatar", () => {
  const { container } = render(
    <MyApp>
      <Avatar image={img} data-testid="avatar" />
    </MyApp>
  );
  const avatar = getByTestId(container, "avatar");

  it("renders a avatar", () => {
    expect(avatar).toBeInTheDocument();
    expect(avatar).toHaveStyle(
      `filter: drop-shadow(0 0 10px ${uniqonThemes.darkTheme.color.background.item})`
    );
  });
  it("renders a avatar change its outline", () => {
    const { container } = render(
      <MyApp>
        <Avatar image={img} outline={false} data-testid="avatar" />
      </MyApp>
    );
    const avatar = getByTestId(container, "avatar");
    expect(avatar).toHaveStyle(
      `outline: 1px solid ${uniqonThemes.darkTheme.color.border.main}`
    );
  });
});
