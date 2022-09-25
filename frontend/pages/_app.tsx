import "@/styles/reset.css";
import "@/styles/global.css";
import "@/styles/palette.css";
import type { AppProps } from "next/app";

import { ThemeProvider } from "@emotion/react";
import { uniqonThemes } from "@/styles/theme";
import Layout from "@/components/Layout";
import Head from "next/head";

export default function MyApp({ Component, pageProps }: AppProps) {
  return (
    <ThemeProvider theme={uniqonThemes.darkTheme}>
      <Head>
        <title>uniq.on | NFT를 통한 스타트업 투자 플랫폼</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <Layout>
        <Component {...pageProps} />
      </Layout>
    </ThemeProvider>
  );
}
