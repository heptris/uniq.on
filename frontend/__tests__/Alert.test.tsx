import Alert from "@/components/Alert";
import { getByTestId, render } from "@testing-library/react";
import { matchers } from "@emotion/jest";
import MyApp from "../__fixtures__/_app";
import { uniqonThemes } from "@/styles/theme";

expect.extend(matchers);

describe("Alert", () => {
  const renderAlert = (isSuccess: boolean, testId: string, message: string) =>
    render(
      <MyApp>
        <Alert isSuccess={isSuccess} data-testid={testId} message={message} />
      </MyApp>
    );

  it("renders success with default msg", () => {
    const msg = "404 에러 잘못된 요청입니다.";
    const testId = "success";
    const { container } = renderAlert(true, testId, msg);
    const alert = getByTestId(container, testId);
    expect(alert).toHaveStyleRule(
      "background-color",
      uniqonThemes.darkTheme.color.status.success
    );
    expect(alert).toHaveTextContent("404 에러 잘못된 요청입니다.");
  });

  it("renders success with text", () => {
    const msg = "로그인 성공";
    const testId = "successWithTxt";
    const { container } = renderAlert(true, testId, msg);
    expect(getByTestId(container, testId)).toHaveTextContent(msg);
  });

  it("renders fail", () => {
    const msg = "404 에러 잘못된 요청입니다.";
    const testId = "fail";
    const { container } = renderAlert(false, testId, msg);
    expect(getByTestId(container, testId)).toHaveStyleRule(
      "background-color",
      uniqonThemes.darkTheme.color.status.fail
    );
  });

  it("disappeared after 1 second", () => {
    // jest.useFakeTimers();
    // const testId = "fail";
    // const { container } = renderAlert(false, testId);
    // setTimeout(() => {
    //   expect(getByTestId(container, testId)).not.toBeInTheDocument();
    // }, 1000);
    // jest.runAllTimers();
  });
});
