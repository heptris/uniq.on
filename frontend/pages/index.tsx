import Head from "next/head";
import Image from "next/image";

import Button from "@/components/Button";
import Text from "@/components/Text";
import Container from "@/components/Container";
import Grid from "@/components/Grid";
import Navbar from "@/components/Navbar";
import Card from "@/components/Card";
import SelectTab from "@/components/SelectTab";

export default function Home() {
  return (
    <Container>
      <Head>
        <title>Create Next App</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <main>
        <Navbar />
        <Text as="h1">
          Welcome to <a href="https://nextjs.org">Next.js!</a>
        </Text>
        <SelectTab />
        <Text as="p">
          Get started by editing <code>pages/index.js</code>
        </Text>
        <Button type={"blue"}>상세 페이지 이동</Button>

        <Grid>
          <Card />
          <a href="https://nextjs.org/docs">
            <Text as="h3">Documentation &rarr;</Text>
            <Text as="p">
              Find in-depth information about Next.js features and API.
            </Text>
          </a>

          <a href="https://nextjs.org/learn">
            <Text as="h3">Learn &rarr;</Text>
            <Text as="p">
              Learn about Next.js in an interactive course with quizzes!
            </Text>
          </a>

          <a href="https://github.com/vercel/next.js/tree/canary/examples">
            <Text as="h3">Examples &rarr;</Text>
            <Text as="p">
              Discover and deploy boilerplate example Next.js projects.
            </Text>
          </a>

          <a href="https://vercel.com/new">
            <Text as="h3">Deploy &rarr;</Text>
            <Text as="p">
              Instantly deploy your Next.js site to a public URL with Vercel.
            </Text>
          </a>
        </Grid>
      </main>

      <footer>
        <a
          href="https://vercel.com?utm_source=create-next-app&utm_medium=default-template&utm_campaign=create-next-app"
          target="_blank"
          rel="noopener noreferrer"
        >
          Powered by{" "}
          <span>
            <Image src="/vercel.svg" alt="Vercel Logo" width={72} height={16} />
          </span>
        </a>
      </footer>
    </Container>
  );
}
