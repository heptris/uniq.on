import { getByTestId, render } from "@testing-library/react";
import { matchers } from "@emotion/jest";

import MyApp from "../__fixtures__/_app";
import Grid from "@/components/Grid";

expect.extend(matchers);

describe("Grid", () => {
  const { container } = render(
    <MyApp>
      <Grid column="double" data-testid="grid" />
    </MyApp>
  );
  const grid = getByTestId(container, "grid");

  it("renders a grid", () => {
    expect(grid).toBeInTheDocument();
  });
  //   it("renders a grid with double columns", () => {
  //     expect(grid).toHaveStyle("display: grid");
  //     expect(grid).toHaveStyle("grid-template-columns: repeat(2, 50%)");
  //   });
});
