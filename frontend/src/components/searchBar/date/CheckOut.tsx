import { RefObject, useState, useEffect } from 'react'
import { useRecoilValue, useRecoilState } from 'recoil'
import { checkOutMessage, clickCheckIn, clickCheckOut } from '../../../customHook/atoms'
import { BarBlock, BarInnerWrapper, BarTitle, BarMessage } from '../../../style/BarStyle'
import { dateToString } from '../../../customHook/useDateInfo'

interface IProps {
  open: boolean
  type: string
  checkOutToggle: RefObject<HTMLDivElement>
}

const CheckOut: React.FunctionComponent<IProps> = ({ open, type, checkOutToggle }) => {
  const checkOut = useRecoilValue(checkOutMessage)
  const [checkInClicked, setCheckInClick] = useRecoilState(clickCheckIn)
  const [checkOutnClicked, setCheckOutClick] = useRecoilState(clickCheckOut)
  const clickTransfer = () => {
    setCheckInClick(false)
    setCheckOutClick(true)
  }
  const handleClick = () => {
    clickTransfer()
  }
  useEffect(() => {
    clickTransfer()
  }, [checkOut])

  return (
    <BarBlock
      onClick={handleClick}
      clicked={checkOutnClicked && open}
      type={type}
      ref={checkOutToggle}
    >
      <BarInnerWrapper>
        <div>
          <BarTitle>체크아웃</BarTitle>
          <BarMessage>{dateToString(checkOut)}</BarMessage>
        </div>
      </BarInnerWrapper>
    </BarBlock>
  )
}

export default CheckOut
