import { ElementType } from "react";

import { css, keyframes } from "@emotion/css";
import { useTheme } from "@emotion/react";

import { ModalProps } from "@/types/props";

/**
 * Modal에 넣어줄 Item은 Modal로 감싸준다.
 * modal창을 닫아주는 onSubmit(특정 페이지로 이동하는 경우, onCancel에 관한 경우 isOpen과 함께 외부에서 작성하고 넣어준다.
 * @params ModalProps<T>
 * @return JSX.Element
 */

function Modal<T extends ElementType = "div">(modalProps: ModalProps<T>) {
  const { isOpen, children, as, onSubmit, onCancel, ...props } = modalProps;
  const target = as ?? "div";

  return isOpen ? (
    <>
      <ModalContent target={target} children={children} {...props} />
      <ModalContainer onCancel={onCancel} />
    </>
  ) : (
    <></>
  );
}

function ModalContainer({ ...props }) {
  const { onCancel } = props;
  return (
    <div
      className={css`
        position: fixed;
        z-index: 1000;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgba(0, 0, 0, 0.4);
      `}
      onClick={onCancel}
    />
  );
}

function ModalContent({
  children,
  target: Component,
  ...props
}: Omit<ModalProps<ElementType>, "isOpen">) {
  const { color } = useTheme();

  const boxSlide = keyframes`
    0% {
      opacity: 0;
      bottom: -100%;
    }
    100% {
      opacity: 1;
      bottom: 0;
    }
  `;

  return (
    <Component
      className={css`
        position: fixed;
        overflow: hidden;
        z-index: 1010;
        bottom: 0;
        background-color: ${color.background.page};
        color: ${color.text.main};
        padding: 20px;
        height: 60%;
        width: 100%;
        border-radius: 1rem 1rem 0 0;
        animation: ${boxSlide} 0.3s;
        > * {
          height: 100%;
          width: 100%;
        }
      `}
      {...props}
    >
      {children}
    </Component>
  );
}

export default Modal;
