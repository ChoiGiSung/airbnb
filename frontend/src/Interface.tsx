import { RefObject } from 'react'
export interface IProps {
  clicked: boolean
  open: boolean
  type: string
  checkInToggle?: RefObject<HTMLDivElement>
  checkOutToggle?: RefObject<HTMLDivElement>
  onClick: () => void
}
export interface IModalPropType {
  modalType: string
  defaultMsg?: string
}
export interface IAction {
  type: string
  data?: any
  error?: Error
}
