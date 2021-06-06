import styled from 'styled-components'
import Place from './place/Place'
import Date from './date/Date'
import Fee from './fee/Fee'
import Personnel from './personnel/Personnel'
import SearchBtn from './searchBtn/SearchBtn'

const SearchBar = () => {
  return (
    <BarWrapper>
      <Bar>
        <Place />
        <Date />
        <Fee />
        <Personnel />
        <SearchBtn />
      </Bar>
    </BarWrapper>
  )
}

const BarWrapper = styled.div`
  display: flex;
  justify-content: center;
  position: relative;
  top: 30px;
`
const Bar = styled.div`
  width: 1120px;
  height: 76px;
  border-radius: 60px;
  border: 1px solid ${(props) => props.theme.color.grey_2};
  display: flex;
`

export default SearchBar
