import { render, screen } from "@testing-library/react";

import MyApp from "../__fixtures__/_app";
import Home from "@/pages/index";

describe("Home", () => {
  it("renders a heading", () => {
    render(
      <MyApp>
        <Home />
      </MyApp>
    );

    const heading = screen.getByRole("heading", {
      name: /welcome to next\.js!/i,
    });

    expect(heading).toBeInTheDocument();
  });
});
