import styled, { css } from 'styled-components'
interface ClickProps {
  clicked?: boolean
  type?: string
}
interface ModalProps {
  modalType: string
}
export const PlaceSection = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
`
export const ModalWrapper = styled.div<ModalProps>`
  position: absolute;
  top: 95px;
  left: ${(props) => props.theme.modalPosition[props.modalType]};
  display: flex;
  justify-content: center;
  align-items: center;
  ${(props) =>
    props.modalType === 'date' &&
    css`
      width: ${props.theme.modalWidth[props.modalType] + 40}px;
      height: 550px;
      overflow: hidden;
    `}
`
export const BarTitle = styled.div`
  height: 17px;
  font-weight: ${(props) => props.theme.fontWeight.w2};
  font-size: ${(props) => props.theme.fontSize.super_sm};
`
export const BarMessage = styled.div`
  height: 23px;
  font-weight: ${(props) => props.theme.fontWeight.w1};
  font-size: ${(props) => props.theme.fontSize.sm};
  color: ${(props) => props.theme.color.grey_4};
`
export const BarInnerWrapper = styled.div`
  padding: 16px;
  display: flex;
  justify-content: space-between;
`
export const BarBlock = styled.div<ClickProps>`
  width: 200px;
  height: 76px;
  cursor: pointer;
  &: hover {
    background-color: ${(props) => props.theme.color.grey_1};
    border-radius: 60px;
  }
  ${(props) =>
    props.clicked &&
    css`
      box-shadow: 0px 4px 10px rgba(51, 51, 51, 0.1), 0px 0px 4px rgba(51, 51, 51, 0.05);
      border-radius: 60px;
      z-index: 9999;
    `};
`

export const Modal = styled.div<ModalProps>`
  background-color: ${(props) => props.theme.color.white};
  padding: ${(props) => props.theme.modalPadding[props.modalType]};
  width: ${(props) => props.theme.modalWidth[props.modalType]}px;
  height: ${(props) => props.theme.modalHeight[props.modalType]};
  box-shadow: 0px 4px 10px rgba(51, 51, 51, 0.1), 0px 0px 4px rgba(51, 51, 51, 0.05);
  border-radius: 40px;
  overflow: hidden;
`
