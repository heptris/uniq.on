/* 테마 모듈 */

export interface UniqonTheme {
  name: string;
  color: {
    background: {
      main: string;
      emphasis: string;
      page: string;
      card: string;
      item: string;
    };
    hover: {
      main: string;
      emphasis: string;
    };
    text: {
      main: string;
      sub: string;
      hover: string;
    };
    status: {
      success: string;
      fail: string;
      disabled: string;
    };
    border: {
      main: string;
    };
  };
}
const darkTheme: UniqonTheme = {
  name: "DarkTheme",
  color: {
    background: {
      main: "var(--vividPurple)",
      emphasis: "var(--vividBlue)",
      page: "var(--ink900)",
      card: "var(--ink800)",
      item: "var(--ink700)",
    },
    hover: {
      main: "var(--purple500)",
      emphasis: "var(--blue900)",
    },
    text: {
      main: "var(--white)",
      sub: "var(--ink300)",
      hover: "var(--ink200)",
    },
    status: {
      success: "var(--vividGreen)",
      fail: "var(--vividPink)",
      disabled: "var(--ink500)",
    },
    border: {
      main: "var(--ink600)",
    },
  },
};
export const uniqonThemes = {
  darkTheme: darkTheme,
};
