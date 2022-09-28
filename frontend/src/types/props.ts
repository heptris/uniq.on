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

export type CardBaseProps = {
  clickable?: boolean;
};
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

export type AlertBaseProps = {
  isSuccess: boolean;
  message?: string;
};
export type AlertProps<T extends ElementType> = OverridableProps<
  T,
  AlertBaseProps
>;

export type SelectTabBaseProps = {
  menus: string[];
  type?: "purple" | "blue";
  onSelectHandler(val: string): void;
};
export type SelectTabProps<T extends ElementType> = CombineElementProps<
  T,
  SelectTabBaseProps
>;

export type FileUploadBaseProps = {
  text: string;
};
export type FileUploadProps<T extends ElementType> = CombineElementProps<
  T,
  FileUploadBaseProps
>;

export type CircleBarBaseProps = {
  total?: number;
  current?: number;
  menus?: String[];
};
export type CircleBarProps<T extends ElementType> = CombineElementProps<
  T,
  CircleBarBaseProps
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

export type LabelInputBaseProps = {
  labelText?: string;
};
export type LabelInputProps<T extends ElementType> = OverridableProps<
  T,
  LabelInputBaseProps
>;

export type CarouselItem = {
  corpName: string;
  image: string | StaticImageData;
};
export type CarouselProps = {
  items: CarouselItem[];
};
