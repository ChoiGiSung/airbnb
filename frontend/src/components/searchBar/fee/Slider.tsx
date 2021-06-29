import { useRef, useEffect } from 'react'
import styled from 'styled-components'
import { useRecoilState, useSetRecoilState } from 'recoil'
import { FeeMaxChange, FeeMinChange, FeeMin, FeeMax } from '../../../customHook/atoms'
interface IGraphProps {
  dataArr: Array<number[]>
}
interface IBoxProps {
  slideLength: number
}

function Slider({ dataArr }: IGraphProps) {
  const [minFeePercent, setMinFeePercecnt] = useRecoilState(FeeMinChange)
  const [maxFeePercent, setMaxFeePercecnt] = useRecoilState(FeeMaxChange)
  const setPriceMin = useSetRecoilState(FeeMin)
  const setPriceMax = useSetRecoilState(FeeMax)

  let slideLength = dataArr.length
  let minValue = dataArr[0][0]
  let maxValue = dataArr[dataArr.length - 1][0]
  setPriceMin(minValue)
  setPriceMax(maxValue)

  let inputRightVal: string
  let inputLeftVal: string
  const range = useRef<HTMLDivElement | null>(null)
  const btnLeft = useRef<HTMLDivElement | null>(null)
  const btnRight = useRef<HTMLDivElement | null>(null)
  const inputLeft = useRef<HTMLInputElement | null>(null)
  const inputRight = useRef<HTMLInputElement | null>(null)

  useEffect(() => {
    if (inputRight && inputRight.current) {
      inputRightVal = inputRight.current.value
      inputRight.current.addEventListener('input', (e: any) => {
        setMaxValue(inputRight.current)
      })
    }
    if (inputLeft && inputLeft.current) {
      inputLeftVal = inputLeft.current.value
      inputLeft.current.addEventListener('input', () => setMinValue(inputLeft.current))
    }
    return () => {
      if (inputLeft && inputLeft.current) {
        inputLeft.current.removeEventListener('input', () => setMinValue(inputLeft.current))
      }
      if (inputRight && inputRight.current) {
        inputRight.current.removeEventListener('input', () => setMaxValue(inputRight.current))
      }
    }
  }, [])

  const setMinValue = (target: HTMLInputElement | null) => {
    if (!target) return
    let ctrlTarget = target
    let min = parseInt(ctrlTarget.min)
    let max = parseInt(ctrlTarget.max)
    ctrlTarget.value = String(Math.min(parseInt(ctrlTarget.value), parseInt(inputRightVal)))
    let percent = ((parseInt(ctrlTarget.value) - min) / (max - min)) * 100

    if (btnLeft && btnLeft.current) {
      btnLeft.current.style.left = percent + '%'
    }
    if (range && range.current) {
      range.current.style.left = percent + '%'
    }
    setMinFeePercecnt(Math.ceil((percent / 100) * (maxValue - minValue)))
  }

  const setMaxValue = (target: HTMLInputElement | null) => {
    if (!target) return
    let ctrlTarget = target
    let min = parseInt(ctrlTarget.min)
    let max = parseInt(ctrlTarget.max)
    ctrlTarget.value = String(Math.max(parseInt(ctrlTarget.value), parseInt(inputLeftVal)))
    let percent = -((parseInt(ctrlTarget.value) - max) / (max - min)) * 100

    if (btnRight && btnRight.current) {
      btnRight.current.style.right = percent + '%'
    }
    if (range && range.current) {
      range.current.style.right = percent + '%'
    }
    setMaxFeePercecnt(Math.ceil((percent / 100) * (maxValue - minValue)))
  }

  return (
    <SliderWrapper className='wrapper'>
      <SliderBox slideLength={slideLength}>
        <Input
          className='input-min'
          ref={inputLeft}
          type='range'
          min='0'
          max='100'
          value='0'
          // onInput={(e) => handleMinInput(e)}
        />
        <Input
          className='input-max'
          ref={inputRight}
          type='range'
          min='0'
          max='100'
          value='100'
          // onInput={(e) => handleMaxInput(e)}
        />
        <SliderView>
          <Track />
          <Range ref={range} />
          <BtnLeft ref={btnLeft}>⫴</BtnLeft>
          <BtnRight ref={btnRight}>⫴</BtnRight>
        </SliderView>
      </SliderBox>

      <PriceBox>
        <PriceTag>
          <span>최저요금</span>
          <input value={minValue + minFeePercent}></input>
        </PriceTag>
        &nbsp;-&nbsp;
        <PriceTag>
          <span>최고요금</span>
          <input value={maxValue - maxFeePercent}></input>
        </PriceTag>
      </PriceBox>
    </SliderWrapper>
  )
}
const SliderWrapper = styled.div`
  display: flex;
  flex-flow: column;
  align-items: center;
  margin-bottom: 30px;
  position: relative;
  top: -17px;
`
const SliderBox = styled.div<IBoxProps>`
  position: relative;
  width: ${(props) => props.slideLength * 50}px;
  top: -23px;
  max-width: 500px;
  display: flex;
  flex-direction: column;
`
const Input = styled.input`
  position: absolute;
  z-index: 2;
  height: 10px;
  width: 100%;
  -webkit-appearance: none;
  appearance: none;
  pointer-events: none;
  opacity: 0;
  &::-webkit-slider-thumb {
    appearance: none;
    pointer-events: all;
    width: 30px;
    height: 30px;
    background-color: grey;
    -webkit-appearance: none;
  }
`

const SliderView = styled.div`
  position: relative;
  z-index: 1;
  height: 10px;
`
const Track = styled.div`
  position: absolute;
  z-index: 1;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  border-radius: 5px;
  background-color: ${({ theme }) => theme.color.grey_2}; ;
`
const Range = styled.div`
  height: 265px;
  position: absolute;
  z-index: 2;
  left: 0%;
  right: 0%;
  top: -255px;
  bottom: 0;
  border-radius: 5px;
  background-color: rgba(1, 1, 1, 0.5);
`
const Btn = styled.div`
  position: absolute;
  z-index: 3;
  width: 30px;
  height: 30px;
  text-align: center;
  color: ${({ theme }) => theme.color.grey_3};
  background-color: ${({ theme }) => theme.color.grey_1};
  border-radius: 50%;
`
const BtnRight = styled(Btn)`
  right: 0%;
  transform: translate(5px, -10px);
`
const BtnLeft = styled(Btn)`
  left: 0%;
  transform: translate(0px, -10px);
`

const PriceBox = styled.div`
  display: flex;
  align-items: center;
`
const PriceTag = styled.div`
  display: flex;
  flex-direction: column;
  border: 1px solid ${({ theme }) => theme.color.grey_2};
  border-radius: 10px;
  padding: 0 10px;
  span {
    display: inline-block;
    font-size: ${({ theme }) => theme.fontSize.x_sm};
    color: ${({ theme }) => theme.color.grey_3};
  }
  input {
    width: 100px;
    height: 30px;
    border: none;
    &:focus {
      outline: none;
    }
  }
`
export default Slider
