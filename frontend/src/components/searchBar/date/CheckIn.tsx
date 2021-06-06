import { RefObject, useEffect } from 'react'
import { useRecoilValue, useRecoilState } from 'recoil'
import { checkInMessage, clickCheckIn, clickCheckOut } from '../../../customHook/atoms'
import { BarBlock, BarInnerWrapper, BarTitle, BarMessage } from '../../../style/BarStyle'
import { dateToString } from '../../../customHook/useDateInfo'
interface IProps {
  open: boolean
  type: string
  checkInToggle: RefObject<HTMLDivElement>
}

const CheckIn: React.FunctionComponent<IProps> = ({ open, type, checkInToggle }) => {
  const checkIn = useRecoilValue(checkInMessage)
  const [checkInClicked, setCheckInClick] = useRecoilState(clickCheckIn)
  const [checkOutnClicked, setCheckOutClick] = useRecoilState(clickCheckOut)
  const clickTransfer = () => {
    setCheckInClick(true)
    setCheckOutClick(false)
  }
  const handleClick = () => {
    clickTransfer()
  }
  useEffect(() => {
    clickTransfer()
  }, [checkIn])

  return (
    <BarBlock
      onClick={handleClick}
      clicked={checkInClicked && open}
      type={type}
      ref={checkInToggle}
    >
      <BarInnerWrapper>
        <div>
          <BarTitle>체크인</BarTitle>
          <BarMessage>{dateToString(checkIn)}</BarMessage>
        </div>
      </BarInnerWrapper>
    </BarBlock>
  )
}

export default CheckIn
