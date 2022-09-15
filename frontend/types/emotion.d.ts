/// <reference types="@emotion/react/types/css-prop" />

import "@emotion/react";

declare module "@emotion/react" {
  export interface Theme {
    colors: {
      pageBgColor: string;
      cardItemBgColor: string;
      lineItemBgColor: string;
      txtMainColor: string;
      txtSubColor: string;
      mainColor: string;
      emphasisColor: string;
      successColor: string;
      failColor: string;
      disabledColor: string;
    };
  }
}
