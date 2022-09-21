import Modal from "@/components/Modal";
import { fireEvent, getByTestId, render } from "@testing-library/react";
import { matchers } from "@emotion/jest";
import MyApp from "../__fixtures__/_app";

expect.extend(matchers);

describe("Modal", () => {
  const handleModalClose = jest.fn();
  // useSelector((state) => state.isOpend);

  const handleModalSubmit = () => {};

  const handleModalCancel = () => {};

  const modal = "modal";
  const txt = "Content 내부 Card";
  const card = <div>{txt}</div>;

  const { container } = render(
    <MyApp>
      <Modal
        data-testid={modal}
        isOpen={true}
        onCancel={handleModalCancel}
        onSubmit={handleModalSubmit}
      >
        {card}
      </Modal>
    </MyApp>
  );
  const vModal = getByTestId(container, modal);

  it("renders Container which fills the full page", () => {
    // const fullContainer = vModal.children;
    // console.log(vModal);
    // expect(vModal.lastElementChild).toHaveStyle(
    //   `position: fixed;
    //     z-index: 1000;
    //     left: 0;
    //     top: 0;
    //     width: 100%;
    //     height: 100%;
    //     overflow: auto;
    //     background-color: rgba(0, 0, 0, 0.4);s`
    // );
  });
  it("renders Content", () => {
    expect(vModal).toHaveTextContent(txt);
  });
  it("is closed when Container is clicked", () => {
    // fireEvent.click(vModal);
    // expect(handleModalClose).toBeCalled();
  });
  it("is opend when triggered", () => {});
  it("", () => {});
});
