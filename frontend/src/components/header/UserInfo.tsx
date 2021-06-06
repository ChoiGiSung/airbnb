import React, { useRef } from 'react'
import styled from 'styled-components'
import useModalCtrl from '../../customHook/useModalCtrlArray'

const UserInfo = () => {
  const userInfoBtn = useRef<HTMLDivElement>(null)
  const userInfoModal = useRef<HTMLDivElement>(null)
  const open = useModalCtrl({
    toggle: [userInfoBtn],
    modal: userInfoModal,
    init: false,
  })
  interface IF_Img {
    desc: string
  }
  const IMG: React.FunctionComponent<IF_Img> = ({ desc }) => {
    return (
      <div>
        <img src={process.env.PUBLIC_URL + desc} alt={desc} />
      </div>
    )
  }

  const HoverMenu = () => {
    return (
      <>
        <MenuBlock ref={userInfoModal}>
          <Menu>로그인</Menu>
        </MenuBlock>
      </>
    )
  }

  return (
    <>
      <BtnBlock ref={userInfoBtn}>
        <IMG desc='/hamburger_btn.png'></IMG>
        <IMG desc='/user_img.png'></IMG>
      </BtnBlock>
      {open && <HoverMenu />}
    </>
  )
}

const Menu = styled.div`
  padding: 32px;
  font-size: ${(props) => props.theme.fontSize.sm};
`
const MenuBlock = styled.div`
  width: 200px;
  height: 87px;
  background-color: ${(props) => props.theme.color.white};
  z-index: 9999;
  position: absolute;
  left: 1140px;
  top: 75px;
  box-shadow: 0px 4px 10px rgba(51, 51, 51, 0.1), 0px 0px 4px rgba(51, 51, 51, 0.05);
  border-radius: 10px;
`
const BtnBlock = styled.div`
  width: 76px;
  height: 40px;
  border: 1px solid ${(props) => props.theme.color.grey_2};
  border-radius: 30px;
  display: flex;
  align-items: center;
  justify-content: space-around;
`
export default UserInfo
