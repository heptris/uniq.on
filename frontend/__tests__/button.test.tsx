import { getByTestId, render } from "@testing-library/react";
import { matchers } from "@emotion/jest";

import MyApp from "../__fixtures__/_app";
import { uniqonThemes } from "@/styles/theme";
import Button from "@/components/Button";

expect.extend(matchers);

describe("Button", () => {
  it("renders a button", () => {
    const { container } = render(
      <MyApp>
        <Button size={"fit"} data-testid="btn" />
      </MyApp>
    );
    const button = getByTestId(container, "btn");

    expect(button).toBeInTheDocument();
  });

  it("renders a button has a label", () => {
    const { container } = render(
      <MyApp>
        <Button size={"fit"} data-testid="btn">
          Invite
        </Button>
      </MyApp>
    );
    const button = getByTestId(container, "btn");

    expect(button).toHaveTextContent("Invite");
  });

  it("renders a button can receive HTMLButtonElement props", () => {
    const { container } = render(
      <MyApp>
        <Button
          data-testid="btn"
          size={"fit"}
          style={{ backgroundColor: "black", color: "white" }}
        />
      </MyApp>
    );
    const button = getByTestId(container, "btn");

    expect(button).toHaveStyle(`
      background-color: black;
      color: white;
    `);
  });

  it("renders a button can change its size", () => {
    const { container } = render(
      <MyApp>
        <Button data-testid="btn" size={"full"} />
      </MyApp>
    );
    const button = getByTestId(container, "btn");

    expect(button).toHaveStyleRule("border", "0");
  });

  it("renders a button can change its type", () => {
    const { container } = render(
      <MyApp>
        <Button data-testid="btn" size={"fit"} type={"purple"} />
      </MyApp>
    );
    const button = getByTestId(container, "btn");

    expect(button).toHaveStyleRule(
      "background-color",
      uniqonThemes.darkTheme.color.background.main
    );
  });
});
