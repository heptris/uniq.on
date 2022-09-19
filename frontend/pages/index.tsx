import Head from "next/head";
import Image from "next/image";

import styles from "@/pages/index.module.css";

import Button from "@/components/Button";
import Text from "@/components/Text";
import Container from "@/components/Container";
import Grid from "@/components/Grid";
import Navbar from "@/components/Navbar";

export default function Home() {
  return (
    <Container>
      <Navbar>
        <Head>
          <title>Create Next App</title>
          <link rel="icon" href="/favicon.ico" />
        </Head>

        <main>
          <Text as="h1" className={styles.title}>
            Welcome to <a href="https://nextjs.org">Next.js!</a>
          </Text>

          <Text as="p" className={styles.description}>
            Get started by editing <code>pages/index.js</code>
          </Text>
          <Button type={"blue"}>상세 페이지 이동</Button>

          <Grid>
            <a href="https://nextjs.org/docs" className={styles.card}>
              <Text as="h3">Documentation &rarr;</Text>
              <Text as="p">
                Find in-depth information about Next.js features and API.
              </Text>
            </a>

            <a href="https://nextjs.org/learn" className={styles.card}>
              <Text as="h3">Learn &rarr;</Text>
              <Text as="p">
                Learn about Next.js in an interactive course with quizzes!
              </Text>
            </a>

            <a
              href="https://github.com/vercel/next.js/tree/canary/examples"
              className={styles.card}
            >
              <Text as="h3">Examples &rarr;</Text>
              <Text as="p">
                Discover and deploy boilerplate example Next.js projects.
              </Text>
            </a>

            <a href="https://vercel.com/new" className={styles.card}>
              <Text as="h3">Deploy &rarr;</Text>
              <Text as="p">
                Instantly deploy your Next.js site to a public URL with Vercel.
              </Text>
            </a>
          </Grid>
        </main>
      </Navbar>

      <footer className={styles.footer}>
        <a
          href="https://vercel.com?utm_source=create-next-app&utm_medium=default-template&utm_campaign=create-next-app"
          target="_blank"
          rel="noopener noreferrer"
        >
          Powered by{" "}
          <span className={styles.logo}>
            <Image src="/vercel.svg" alt="Vercel Logo" width={72} height={16} />
          </span>
        </a>
      </footer>
    </Container>
  );
}
