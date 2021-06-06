import { Modal } from '../../../style/BarStyle'
import useAxios from '../../../customHook/useAxios'
import getData from '../../../customHook/axiosAPI'
interface IFeeType {
  modalType: string
}

const ModalFee: React.FunctionComponent<IFeeType> = ({ modalType }) => {
  const state = useAxios(getData)
  const { loading, error, data } = state
  if (loading) return <div>로딩중</div>
  if (error) return <div>에러발생</div>
  if (!data) return null

  return (
    <>
      <Modal modalType={modalType}>
        {data.prices.map((el: number, idx: number) => (
          <li key={idx}>{el}</li>
        ))}
      </Modal>
    </>
  )
}

export default ModalFee
