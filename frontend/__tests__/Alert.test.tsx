import Alert from "@/components/Alert";
import { getByTestId, render } from "@testing-library/react";
import { matchers } from "@emotion/jest";
import MyApp from "../__fixtures__/_app";
import { uniqonThemes } from "@/styles/theme";

expect.extend(matchers);

describe("Alert", () => {
  it("renders success", () => {
    const testId = "success";
    const { container } = render(
      <MyApp>
        <Alert isSuccess={true} data-testid={testId} />
      </MyApp>
    );
    expect(getByTestId(container, testId)).toHaveStyleRule(
      "background-color",
      uniqonThemes.darkTheme.color.status.success
    );
  });
  it("renders success with text", () => {
    const msg = "로그인 성공";
    const testId = "successWithTxt";
    const { container } = render(
      <MyApp>
        <Alert isSuccess={true} data-testid={testId} />
      </MyApp>
    );
    expect(getByTestId(container, testId)).toHaveTextContent(msg);
  });
  it("renders fail", () => {
    const testId = "fail";
    const { container } = render(
      <MyApp>
        <Alert isSuccess={false} data-testid={testId} />
      </MyApp>
    );
    expect(getByTestId(container, testId)).toHaveStyleRule(
      "background-color",
      uniqonThemes.darkTheme.color.status.fail
    );
  });
});
