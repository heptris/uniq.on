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

function Carousel(props: CarouselProps) {
  const items = useRef(props.items);

  const [current, setCurrent] = useState(0);
  const [style, setStyle] = useState({
    marginLeft: `-${current * 100}vw`,
  });
  const itemSize = useRef(items.current.length);

  const moveSlide = (i: number) => {
    let nextIndex = current + i;

    if (nextIndex < 0) nextIndex = itemSize.current - 1;
    else if (nextIndex >= itemSize.current) nextIndex = 0;

    setCurrent(nextIndex);
  };

  useEffect(() => {
    setStyle({ marginLeft: `-${current * 100}vw` });
  }, [current]);

  return (
    <CarouselContainer>
      <BluredBackground>
        <Image src={items.current[current].image} />
      </BluredBackground>
      <Slide style={style}>
        {items.current.map((item, i) => (
          <SlideItem key={i}>
            <Image
              src={item.image}
              layout={"fill"}
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
`;
const BluredBackground = styled.div`
  position: absolute;
  transform: translate(-50%, -50%);
  transform: scale(2.2);
  width: 100vw;
  height: 30rem;
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
`;
const SlideItem = styled.div`
  position: relative;
  display: block;
  margin: 0 3vw 0 3vw;
  width: 94vw;
  height: 27rem;
  border-radius: 16px;
  overflow: hidden;
`;
const SlideButton = styled.button`
  position: absolute;
  background-color: transparent;
  border: 0;

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
  transform: translate(0, -50%);
`;

export default Carousel;
