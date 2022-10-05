import "@/styles/reset.css";
import "@/styles/global.css";
import "@/styles/palette.css";

import type { AppProps } from "next/app";
import Head from "next/head";

import { ThemeProvider } from "@emotion/react";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { ReactQueryDevtools } from "@tanstack/react-query-devtools";

import { uniqonThemes } from "@/styles/theme";
import Layout from "@/components/Layout";
import { Provider } from "react-redux";
import store from "@/store";
import { Suspense } from "react";

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      suspense: true,
      retry: 0,
    },
  },
});

export default function MyApp({ Component, pageProps }: AppProps) {
  return (
    <Provider store={store}>
      <QueryClientProvider client={queryClient}>
        <ThemeProvider theme={uniqonThemes.darkTheme}>
          <Head>
            <title>uniq.on | NFT를 통한 스타트업 투자 플랫폼</title>
            <link rel="icon" href="/logo.png" />
            <meta
              httpEquiv="Content-Security-Policy"
              content="upgrade-insecure-requests"
            />
          </Head>
          <Suspense fallback={<div>App Loading...</div>}>
            <Layout>
              <Component {...pageProps} />
            </Layout>
          </Suspense>
        </ThemeProvider>
        <ReactQueryDevtools initialIsOpen={false} />
      </QueryClientProvider>
    </Provider>
  );
}
