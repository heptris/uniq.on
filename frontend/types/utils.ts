/* 타입 정의 시 유용하게 쓸 수 있는 유틸 타입들 */

import { ComponentPropsWithoutRef, ElementType } from "react";

export type Combine<T, K> = T & Omit<K, keyof T>;
export type CombineElementProps<T extends ElementType, K = unknown> = Combine<
  K,
  ComponentPropsWithoutRef<T>
>;
export type OverridableProps<T extends ElementType, K = unknown> = {
  as?: T;
} & CombineElementProps<T, K>;

export type ColorMap = {
  background: {
    purple: string;
    blue: string;
    disabled?: string;
  };
  hover?: {
    purple: string;
    blue: string;
  };
};
