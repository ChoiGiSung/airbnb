import { useRef } from 'react'
import {
  ModalWrapper,
  BarBlock,
  BarInnerWrapper,
  BarTitle,
  BarMessage,
} from '../../../style/BarStyle'
import ModalPersonnel from './ModalPersonnel'
import useModalCtrl from '../../../customHook/useModalCtrlArray'
const Personnel = () => {
  const PersonnelToggle = useRef<HTMLDivElement>(null)
  const PersonnelModal = useRef<HTMLDivElement>(null)
  const open = useModalCtrl({
    toggle: [PersonnelToggle],
    modal: PersonnelModal,
    init: false,
  })
  return (
    <>
      <BarBlock ref={PersonnelToggle}>
        <BarInnerWrapper>
          <div>
            <BarTitle>인원</BarTitle>
            <BarMessage>게스트 추가</BarMessage>
          </div>
        </BarInnerWrapper>
      </BarBlock>
      {open && (
        <ModalWrapper ref={PersonnelModal} modalType={'personnel'}>
          <ModalPersonnel modalType={'personnel'} />
        </ModalWrapper>
      )}
    </>
  )
}
export default Personnel
