/* 추상화된 컴포넌트들의 props 타입 */

import { ElementType } from "react";
import { OverridableProps } from "@/types/utils";

export type TextBaseProps = {
  typography?: string;
};
export type TextProps<T extends ElementType> = OverridableProps<
  T,
  TextBaseProps
>;

export type ButtonBaseProps = {
  size?: "fit" | "full";
  type?: "purple" | "blue";
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

export type GridBaseProps = {};
export type GridProps<T extends ElementType> = OverridableProps<
  T,
  GridBaseProps
>;

export type CardBaseProps = {};
export type CardProps<T extends ElementType> = OverridableProps<
  T,
  CardBaseProps
>;

export type AvatarBaseProps = {};
export type AvatarProps<T extends ElementType> = OverridableProps<
  T,
  AvatarBaseProps
>;

export type AlertBaseProps = {};
export type AlertProps<T extends ElementType> = OverridableProps<
  T,
  AlertBaseProps
>;
