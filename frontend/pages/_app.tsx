import "@/styles/global.css";
import type { AppProps } from "next/app";

import { ThemeProvider } from "@emotion/react";
import { darkTheme } from "@/styles/theme";

export default function MyApp({ Component, pageProps }: AppProps) {
  return (
    <ThemeProvider theme={darkTheme}>
      <Component {...pageProps} />
    </ThemeProvider>
  );
}
