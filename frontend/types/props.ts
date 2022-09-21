/* 추상화된 컴포넌트들의 props 타입 */

import { ElementType, ReactElement } from "react";
import { CombineElementProps, OverridableProps } from "@/types/utils";
import { StaticImageData } from "next/image";

export type TextBaseProps = {
  typography?: "content";
};
export type TextProps<T extends ElementType> = OverridableProps<
  T,
  TextBaseProps
>;

export type ButtonBaseProps = {
  size?: "fit" | "full";
  type?: "purple" | "blue";
  disabled?: boolean;
};
export type ButtonProps<T extends ElementType> = OverridableProps<
  T,
  ButtonBaseProps
>;

export type ContainerBaseProps = {};
export type ContainerProps<T extends ElementType> = OverridableProps<
  T,
  ContainerBaseProps
>;

export type GridBaseProps = {
  column?: "mono" | "double";
};
export type GridProps<T extends ElementType> = OverridableProps<
  T,
  GridBaseProps
>;

export type CardBaseProps = {};
export type CardProps<T extends ElementType> = OverridableProps<
  T,
  CardBaseProps
>;

export type AvatarBaseProps = {
  image: string | StaticImageData;
  outline?: boolean;
};
export type AvatarProps<T extends ElementType> = OverridableProps<
  T,
  AvatarBaseProps
>;

export type AlertBaseProps = {};
export type AlertProps<T extends ElementType> = OverridableProps<
  T,
  AlertBaseProps
>;

export type SelectTabBaseProps = {};
export type SelectTabProps<T extends ElementType> = OverridableProps<
  T,
  AlertBaseProps
>;

export type ProgressBarBaseProps = {
  progress: number;
  maxProgress?: number;
  type?: "purple" | "blue";
};
export type ProgressBarProps<T extends ElementType> = CombineElementProps<
  T,
  ProgressBarBaseProps
>;

export type ModalBaseProps = {
  isOpen: boolean;
  onSubmit: () => void;
  onCancel: () => void;
};
export type ModalProps<T extends ElementType> = OverridableProps<
  T,
  ModalBaseProps
>;
