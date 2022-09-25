import { getByTestId, render } from "@testing-library/react";

import MyApp from "../__fixtures__/_app";
import Container from "@/components/Container";

describe("Container", () => {
  const { container } = render(
    <MyApp>
      <Container data-testid="container" />
    </MyApp>
  );
  const containerElement = getByTestId(container, "container");

  it("renders a container", () => {
    expect(containerElement).toBeInTheDocument();
  });
});
