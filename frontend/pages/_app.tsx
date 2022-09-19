import "@/styles/reset.css";
import "@/styles/global.css";
import "@/styles/palette.css";
import type { AppProps } from "next/app";

import { ThemeProvider } from "@emotion/react";
import { uniqonThemes } from "@/styles/theme";

export default function MyApp({ Component, pageProps }: AppProps) {
  return (
    <ThemeProvider theme={uniqonThemes.darkTheme}>
      <Component {...pageProps} />
    </ThemeProvider>
  );
}
