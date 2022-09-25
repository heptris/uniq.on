import { getByTestId, render } from "@testing-library/react";
import { matchers } from "@emotion/jest";

import MyApp from "../__fixtures__/_app";
import ProgressBar from "@/components/ProgressBar";

expect.extend(matchers);

describe("ProgressBar", () => {
  const { container } = render(
    <MyApp>
      <ProgressBar
        data-testid="progress-bar"
        progress={38}
        maxProgress={100}
        type={"blue"}
      />
    </MyApp>
  );
  const progressBar = getByTestId(container, "progress-bar");

  it("renders a ProgressBar", () => {
    expect(progressBar).toBeInTheDocument();
  });
  it("renders a PB with progress value", () => {
    expect(progressBar).toHaveValue(38);
  });
  it("renders a PB can change its type", () => {
    expect(progressBar).toHaveStyleRule(
      "background-color",
      "var(--vividBlue)",
      { target: "::-webkit-progress-value" }
    );
  });
});
