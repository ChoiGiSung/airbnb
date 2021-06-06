import styled from 'styled-components'
import Logo from './Logo'
import CenterMenu from './CenterMenu'
import UserInfo from './UserInfo'

const Header = () => {
  return (
    <FlexBox>
      <Logo />
      <CenterMenu />
      <UserInfo />
    </FlexBox>
  )
}

const FlexBox = styled.div`
  display: flex;
  justify-content: space-around;
  margin-top: 30px;
`
export default Header
