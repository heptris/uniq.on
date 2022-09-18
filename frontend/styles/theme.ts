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
    text: {
      main: string;
      sub: string;
    };
    status: {
      success: string;
      fail: string;
      disabled: string;
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
    text: {
      main: "var(--white)",
      sub: "var(--ink600)",
    },
    status: {
      success: "var(--vividGreen)",
      fail: "var(--vividPink)",
      disabled: "var(--ink500)",
    },
  },
};
export const uniqonThemes = {
  darkTheme: darkTheme,
};
