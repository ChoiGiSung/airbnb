import { useRef } from 'react'
import {
  ModalWrapper,
  BarBlock,
  BarInnerWrapper,
  BarTitle,
  BarMessage,
} from '../../../style/BarStyle'
import ModalFee from './ModalFee'
import useModalCtrl from '../../../customHook/useModalCtrlArray'
import { useRecoilValue, useRecoilState, useSetRecoilState } from 'recoil'
import { FeeMsg, FeeMin, defaultValue, FeeMinChange, FeeMaxChange } from '../../../customHook/atoms'
import useXclick from '../../../customHook/useXclick'

const Fee = () => {
  const FeeToggle = useRef<HTMLDivElement>(null)
  const FeeModal = useRef<HTMLDivElement>(null)
  const open = useModalCtrl({
    toggle: [FeeToggle],
    modal: FeeModal,
    init: false,
  })

  const setMinFeePercent = useSetRecoilState(FeeMinChange)
  const setMaxFeePercent = useSetRecoilState(FeeMaxChange)
  const [priceMin, setPriceMin] = useRecoilState(FeeMin)
  const getFeeMsg = useRecoilValue(FeeMsg)

  const RenderXbtn = useXclick(
    priceMin,
    [setPriceMin, setMinFeePercent, setMaxFeePercent],
    defaultValue.fee
  )

  return (
    <>
      <BarBlock ref={FeeToggle}>
        <BarInnerWrapper>
          <div>
            <BarTitle>요금</BarTitle>
            <BarMessage>{getFeeMsg}</BarMessage>
          </div>
          <RenderXbtn />
        </BarInnerWrapper>
      </BarBlock>
      {open && (
        <ModalWrapper ref={FeeModal} modalType={'ModalFee'}>
          <ModalFee modalType={'ModalFee'} />
        </ModalWrapper>
      )}
    </>
  )
}
export default Fee
