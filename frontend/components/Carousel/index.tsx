import { useEffect, useRef, useState } from "react";
import Image from "next/image";

import { css } from "@emotion/react";
import styled from "@emotion/styled";
import {
  faChevronCircleLeft,
  faChevronCircleRight,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import type { CarouselProps } from "@/types/props";
import { minDesktopWidth, minTabletWidth } from "@/styles/utils";

const useInterval = (callback: Function, delay: number) => {
  const savedCallback = useRef<Function>();
  useEffect(() => {
    savedCallback.current = callback;
  }, [callback]);

  useEffect(() => {
    function tick() {
      if (!savedCallback.current) return;
      savedCallback.current();
    }
    if (delay !== null) {
      let id = setInterval(tick, delay);
      return () => clearInterval(id);
    }
  }, [delay]);
};

/**
 *
 * @param items CarouselItem[]
 * @returns ReactElement
 */
function Carousel(props: CarouselProps) {
  const items = props.items;

  const [currentIdx, setCurrentIdx] = useState(0);
  const [style, setStyle] = useState({
    marginLeft: `-${currentIdx * 100}vw`,
  });
  const itemSize = useRef(items.length);

  const moveSlide = (di: number) => {
    setCurrentIdx((currentIdx) => {
      let nextIndex = currentIdx + di;
      if (nextIndex < 0) nextIndex = itemSize.current - 1;
      else if (nextIndex >= itemSize.current) nextIndex = 0;

      return nextIndex;
    });
  };

  useEffect(() => {
    setStyle({ marginLeft: `-${currentIdx * 100}vw` });
  }, [currentIdx]);
  useInterval(() => {
    moveSlide(1);
  }, 10000);

  return (
    <CarouselContainer>
      <BluredBackground>
        <Image src={items[currentIdx].image} />
      </BluredBackground>
      <Slide style={style}>
        {items.map((item, i) => (
          <SlideItem key={i}>
            <CorporationName>{item.corpName}</CorporationName>
            <Image
              src={item.image}
              layout={"fill"}
              objectFit={"cover"}
              alt={`Carousel Image${i}`}
            />
          </SlideItem>
        ))}
      </Slide>
      <SlideButton
        role="left-button"
        onClick={() => moveSlide(-1)}
        css={css`
          top: 50%;
          left: 0;

          @media (${minDesktopWidth}) {
            left: 1rem;
          }
        `}
      >
        <StyledFontAwesomeIcon icon={faChevronCircleLeft} />
      </SlideButton>
      <SlideButton
        role="right-button"
        onClick={() => moveSlide(1)}
        css={css`
          top: 50%;
          right: 0;

          @media (${minDesktopWidth}) {
            right: 1rem;
          }
        `}
      >
        <StyledFontAwesomeIcon icon={faChevronCircleRight} />
      </SlideButton>
    </CarouselContainer>
  );
}

const CarouselContainer = styled.div`
  width: 100vw;
  height: 30rem;
  overflow: hidden;
  display: flex;
  align-items: center;
  position: relative;
  color: ${({ theme }) => theme.color.text.main};
  border-bottom: 1px solid ${({ theme }) => theme.color.border.main};

  @media (${minTabletWidth}) {
    height: 55vh;
  }
`;
const BluredBackground = styled.div`
  position: absolute;
  transform: translate(-50%, -50%);
  transform: scale(2.2);
  width: 100vw;
  height: 55vh;
  top: 0;
  left: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  filter: blur(30px);
`;
const Slide = styled.div`
  display: flex;
  flex-direction: row;
  width: 300vw;
  height: 27rem;
  transition: margin-left 0.3s ease 0s;

  @media (${minTabletWidth}) {
    height: 50vh;
  }
`;
const SlideItem = styled.div`
  position: relative;
  display: block;
  margin: 0 3vw 0 3vw;
  width: 94vw;
  height: 27rem;
  border-radius: 16px;
  overflow: hidden;

  @media (${minTabletWidth}) {
    height: 50vh;
  }
`;
const CorporationName = styled.p`
  position: absolute;
  left: 3%;
  bottom: 10%;
  padding: 0.3rem 1rem;
  border-radius: 8px;
  font-size: 3rem;
  font-weight: 700;
  z-index: 50;
  color: ${({ theme }) => theme.color.text.main};
  backdrop-filter: blur(20px);
`;
const SlideButton = styled.button`
  width: fit-content;
  position: absolute;
  background-color: transparent;
  border: 0;
  transform: translate(0, -50%);

  &:hover,
  &:active {
    path {
      color: ${({ theme }) => theme.color.text.hover};
    }
  }
  &:hover {
    path {
      cursor: pointer;
    }
  }
`;
const StyledFontAwesomeIcon = styled(FontAwesomeIcon)`
  width: 2rem;
  color: ${({ theme }) => theme.color.text.sub};
`;

export default Carousel;
