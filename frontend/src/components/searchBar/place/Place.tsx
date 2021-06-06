import { useState, useRef, useEffect } from 'react'
import { useRecoilState } from 'recoil'
import { clickedPlace } from '../../../customHook/atoms'
import {
  PlaceSection,
  ModalWrapper,
  BarBlock,
  BarInnerWrapper,
  BarTitle,
  BarMessage,
} from '../../../style/BarStyle'
import { TiDelete } from 'react-icons/ti'
import IconButton from '@material-ui/core/IconButton'
import useModalCtrl from '../../../customHook/useModalCtrlArray'
import ModalPlace from './ModalPlace'
import { clickPlace } from '../../../customHook/atoms'

const Place = () => {
  const PlaceToggle = useRef<HTMLDivElement>(null)
  const PlaceModal = useRef<HTMLDivElement>(null)
  const open = useModalCtrl({
    toggle: [PlaceToggle],
    modal: PlaceModal,
    init: false,
  })

  const [placeClicked, setPlaceClicked] = useRecoilState(clickPlace)
  const [placeToSearch, setPlaceToSearch] = useRecoilState(clickedPlace)
  const defaultMsg = '어디로 여행가세요?'
  const [viewX, setViewX] = useState(false)

  const handleClick = () => {
    setPlaceClicked((clicked) => {
      if (!open) return true
      return !clicked
    })
  }

  const X_handleClick = () => {
    setPlaceToSearch(defaultMsg)
  }

  useEffect(() => {
    placeToSearch && placeToSearch !== defaultMsg ? setViewX(true) : setViewX(false)
  }, [placeToSearch])

  return (
    <PlaceSection>
      <BarBlock clicked={placeClicked && open} onClick={handleClick} ref={PlaceToggle}>
        <BarInnerWrapper>
          <div>
            <BarTitle>위치</BarTitle>
            <BarMessage>{placeToSearch}</BarMessage>
          </div>
          {viewX && (
            <IconButton onClick={X_handleClick}>
              <TiDelete />
            </IconButton>
          )}
        </BarInnerWrapper>
      </BarBlock>
      {open && (
        <ModalWrapper ref={PlaceModal} modalType='place'>
          <ModalPlace modalType='place' defaultMsg={defaultMsg} />
        </ModalWrapper>
      )}
    </PlaceSection>
  )
}

export default Place
