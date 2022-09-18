import { Theme, ThemeProvider, ThemeProviderProps } from "@emotion/react";
import { uniqonThemes } from "@/styles/theme";

import { Combine } from "@/types/utils";

type MyAppProps = Combine<{ theme?: Theme }, ThemeProviderProps>;

function MyApp(props: MyAppProps) {
  return <ThemeProvider theme={uniqonThemes.darkTheme} {...props} />;
}

export default MyApp;
