import {
  act,
  fireEvent,
  getByTestId,
  render,
  screen,
} from "@testing-library/react";
import { matchers } from "@emotion/jest";

import MyApp from "../__fixtures__/_app";
import LabelInput from "@/components/LabelInput";

import { uniqonThemes } from "@/styles/theme";

expect.extend(matchers);

describe("LabelInput", () => {
  const renderLabelInput = (
    testId: string,
    value: string,
    onChange: React.ChangeEventHandler,
    placeholder?: string,
    labelText?: string
  ) =>
    render(
      <MyApp>
        <LabelInput
          data-testid={testId}
          placeholder={placeholder}
          labelText={labelText}
          value={value}
          onChange={onChange}
        />
      </MyApp>
    );

  it("renders without label", () => {
    const testId = "inp-w/o-lbl";
    const { container } = renderLabelInput(testId, "", () => {});
    const input = getByTestId(container, testId);
    expect(input).toBeInTheDocument();
  });

  it("renders with placeholder text", () => {
    const plchldr = "placeHolder Text";
    const testId = "inp-w/-plchldr";
    renderLabelInput(testId, "", () => {}, plchldr);
    const input = screen.getByPlaceholderText(plchldr);
    expect(input).toBeInTheDocument();
    expect(input).toHaveStyle(
      `border-radius: 8px;
      `
    );
  });

  it("renders with label", () => {
    const labelText = "label Text";
    const testId = "inp-w/-label";
    renderLabelInput(testId, "", () => {}, "", labelText);
    const inputNode = screen.getByLabelText(labelText);
    expect(inputNode).toBeInTheDocument();
  });

  it("focused when label clicked", () => {
    const labelText = "labelText";
    const testId = "inp-w/-label";
    const plchldr = "placeholder text to disappear";
    const { getByPlaceholderText, getByText, container } = renderLabelInput(
      testId,
      "",
      () => {},
      plchldr,
      labelText
    );
    const labelNode = getByText(labelText);
    // console.log(labelNode);
    // const user = userEvent.setup();
    // user.click(labelNode);
    fireEvent.click(labelNode);

    // expect(getByPlaceholderText(plchldr)).toHaveFocus();
    // expect(getByPlaceholderText(plchldr).focus()).toBe();
  });

  it("renders with border colored when focused", () => {
    const labelText = "labelText";
    const testId = "inp-w/-label";
    const plchldr = "placeholder text to disappear";
    const { getByPlaceholderText } = renderLabelInput(
      testId,
      "",
      () => {},
      plchldr,
      labelText
    );

    // fireEvent.click(labelNode);

    const inputNode = getByPlaceholderText(plchldr);
    act(() => {
      inputNode.focus();
    });
    expect(inputNode).toHaveFocus();
    // expect(inputNode).toHaveStyleRule(
    //   `outline`,
    //   `2px solid ${uniqonThemes.darkTheme.color.border.main}`
    // );
    // expect(getByPlaceholderText(plchldr).focus()).toBe();
  });

  it("value changed when typing events", () => {
    const testId = "inp-typed";

    const onChange = jest.fn();

    let value = "";

    const { getByTestId, getByDisplayValue } = renderLabelInput(
      testId,
      value,
      (e) => onChange(e)
    );
    const inputNode = getByTestId(testId);
    // fireEvent.change(inputNode, { target: { value: "123" } });
    // expect(getByDisplayValue("123") === inputNode).toBe(true);
    expect(inputNode).toHaveTextContent(value);
  });
});
