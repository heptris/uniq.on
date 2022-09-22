import Container from "@/components/Container";
import Navbar from "@/components/Navbar";
import Footer from "@/components/Footer";
import { css } from "@emotion/css";
import { useTheme } from "@emotion/react";
import mainimg1 from "assets/mainimg1.jpeg";
import mainimg2 from "assets/mainimg2.jpeg";
import mainimg3 from "assets/mainimg3.jpeg";
import Carousel from "@/components/Carousel";
import { useRef } from "react";
import Layout from "@/components/Layout";

export default function Home() {
  return (
    <Layout>
      <Carousel />
    </Layout>
  );
}
