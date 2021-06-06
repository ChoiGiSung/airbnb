import styled from 'styled-components'
import { Modal } from '../../../style/BarStyle'
import { IModalPropType } from '../../../Interface'
const ModalPersonnel: React.FunctionComponent<IModalPropType> = ({ modalType }) => {
  return (
    <>
      <Modal modalType={modalType}>인원</Modal>
    </>
  )
}

export default ModalPersonnel
