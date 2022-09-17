import { render, screen } from "@testing-library/react";
import { ThemeProvider } from "@emotion/react";
import { darkTheme } from "@/styles/theme";

import Home from "@/pages/index";

describe("Home", () => {
  it("renders a heading", () => {
    render(
      <ThemeProvider theme={darkTheme}>
        <Home />
      </ThemeProvider>
    );

    const heading = screen.getByRole("heading", {
      name: /welcome to next\.js!/i,
    });

    expect(heading).toBeInTheDocument();
  });
});
