import { Theme, ThemeProvider, ThemeProviderProps } from "@emotion/react";
import { darkTheme } from "@/styles/theme";

import { Combine } from "@/types/utils";

type MyAppProps = Combine<{ theme?: Theme }, ThemeProviderProps>;

function MyApp(props: MyAppProps) {
  return <ThemeProvider theme={darkTheme} {...props} />;
}

export default MyApp;
