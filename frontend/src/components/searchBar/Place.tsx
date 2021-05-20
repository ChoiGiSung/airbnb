import {useState, useRef, useEffect, } from 'react'
import { PlaceSection, ModalWrapper, BarBlock, BarInnerWrapper, BarTitle, BarMessage} from '../../style/BarStyle'
import { TiDelete } from "react-icons/ti";
import IconButton from '@material-ui/core/IconButton';
import useModalCtrl from '../../customHook/useModalCtrl'
import ModalPlace from './ModalPlace';

const Place = () => {
  const PlaceToggle = useRef<HTMLDivElement>(null)
  const PlaceModal = useRef<HTMLDivElement>(null)
  const open = useModalCtrl({toggle:PlaceToggle, modal:PlaceModal, init:false})
  const [clicked, setClick] = useState(false)
  useEffect(() => {
    open? setClick(true) : setClick(false)
  },[open])

  const defaultMsg = '어디로 여행가세요?';
  const [targetPlace, setTargetPlace] = useState<string>(defaultMsg)
  const [viewX, setViewX] = useState(false)
  const handleClick = () =>{
    setTargetPlace(defaultMsg);
  }
  useEffect(() => {targetPlace && targetPlace!==defaultMsg ? setViewX(true): setViewX(false);}
  ,[targetPlace])

  return(
    <PlaceSection>
    <BarBlock clicked={clicked} ref={PlaceToggle}>
      <BarInnerWrapper>
        <div>
          <BarTitle>위치</BarTitle>
          <BarMessage>{targetPlace}</BarMessage>
        </div>
        {viewX && <IconButton onClick={handleClick}><TiDelete/></IconButton>}
      </BarInnerWrapper>
    </BarBlock>
    {open && 
      (
      <ModalWrapper ref={PlaceModal}>
        <ModalPlace modalType="place" {...{targetPlace, setTargetPlace, defaultMsg}}/>
      </ModalWrapper>
      )
    }
    </PlaceSection>
  )
}

export default Place;