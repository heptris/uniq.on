import Head from "next/head";
import Image from "next/image";

import Button from "@/components/Button";
import Text from "@/components/Text";
import Container from "@/components/Container";
import Grid from "@/components/Grid";
import Navbar from "@/components/Navbar";
import Card from "@/components/Card";
import SelectTab from "@/components/SelectTab";
import Footer from "@/components/Footer";
import { css } from "@emotion/css";
import { useTheme } from "@emotion/react";
import { useEffect, useRef, useState } from "react";
import mainimg1 from "assets/mainimg1.jpeg";
import mainimg2 from "assets/mainimg2.jpeg";
import mainimg3 from "assets/mainimg3.jpeg";

export default function Home() {
  const theme = useTheme();
  const images = useRef([
    {
      src: mainimg1,
    },
    {
      src: mainimg2,
    },
    {
      src: mainimg3,
    },
  ]);

  const [current, setCurrent] = useState(0);
  const [style, setStyle] = useState({
    marginLeft: `-${current}00%`,
  });
  const imgSize = useRef(images.current.length);

  const moveSlide = (i: number) => {
    let nextIndex = current + i;

    if (nextIndex < 0) nextIndex = imgSize.current - 1;
    else if (nextIndex >= imgSize.current) nextIndex = 0;

    setCurrent(nextIndex);
  };

  useEffect(() => {
    setStyle({ marginLeft: `-${current}00%` });
  }, [current]);

  return (
    <Container>
      <Navbar />
      <div
        className={css`
          width: 100%;
          padding: 2rem 0;
          flex: 1 1;
          display: flex;
          flex-direction: column;
          justify-content: flex-start;
          align-items: center;
          color: ${theme.color.text.main};

          .slide {
            width: 100%;
            display: flex;
            align-items: center;
          }

          .window {
            background: coral;
            width: 315px;
            height: 215px;
            overflow: hidden;
          }

          .flexbox {
            display: flex;
          }

          .img {
            width: 315px;
            height: 215px;
            background-position: 50% 50%;
            background-size: contain;
            background-repeat: no-repeat;
            flex: none;
          }

          .btn {
            display: flex;
            align-items: center;
            cursor: pointer;
            font-size: 2rem;
            color: gray;
            padding: 0 10px;
          }

          .position {
            margin-top: 15px;
            display: flex;
            justify-content: center;
          }

          .dot {
            background: ${theme.color.status.disabled};
            border-radius: 100%;
            height: 10px;
            width: 10px;
          }
          .dot + .dot {
            margin-left: 20px;
          }

          .current {
            background: ${theme.color.text.sub};
          }
        `}
      >
        <div className="slide">
          <div
            className="btn"
            onClick={() => {
              moveSlide(-1);
            }}
          >
            &lt;
          </div>
          <div className="window">
            <div className="flexbox" style={style}>
              {images.current.map((img, i) => (
                <div className="img">
                  <Image
                    src={img.src}
                    alt="img"
                    width={315}
                    height={215}
                  ></Image>
                </div>
              ))}
            </div>
          </div>
          <div
            className="btn"
            onClick={() => {
              moveSlide(1);
            }}
          >
            &gt;
          </div>
        </div>
        <div className="position">
          {images.current.map((x, i) => (
            <div
              key={i}
              className={i === current ? "dot current" : "dot"}
            ></div>
          ))}
        </div>
      </div>

      <Footer />
    </Container>
  );
}
