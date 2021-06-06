import styled from 'styled-components'
import { useRecoilState } from 'recoil'
import { clickedPlace } from '../../../customHook/atoms'
import Button from '@material-ui/core/Button'
import { FaMapMarkedAlt } from 'react-icons/fa'
import { Modal } from '../../../style/BarStyle'
import { IModalPropType } from '../../../Interface'

const ModalPlace: React.FunctionComponent<IModalPropType> = ({ modalType, defaultMsg }) => {
  const locations = ['서울', '경기', '부산', '광주', '대전', '전주', '강원', '제주']
  const [placeToSearch, setPlaceToSearch] = useRecoilState(clickedPlace)

  const handleClick = (location: string | null) => {
    setPlaceToSearch(typeof location === 'string' ? location : defaultMsg)
  }

  return (
    <>
      <Modal modalType={modalType}>
        <ContentsWrapper>
          <Button fullWidth onClick={() => handleClick(null)}>
            <FaMapMarkedAlt />
            &nbsp;&nbsp;{defaultMsg}
          </Button>
          <GridWrapper>
            {locations.map((location, idx) => (
              <Button key={'location' + idx} onClick={() => handleClick(location)}>
                {location}
              </Button>
            ))}
          </GridWrapper>
        </ContentsWrapper>
      </Modal>
    </>
  )
}

const ContentsWrapper = styled.div`
  width: -webkit-fill-available;
`
const GridWrapper = styled.div`
  display: grid;
  grid-template-rows: repeat(4, 1fr);
  grid-template-columns: repeat(2, 1fr);
`

export default ModalPlace
