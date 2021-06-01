import styled from 'styled-components'

import IconButton from '@material-ui/core/IconButton'
import FavoriteBorderIcon from '@material-ui/icons/FavoriteBorder'
import FavoriteIcon from '@material-ui/icons/Favorite'
function HouseList({ data }: any) {
  console.log(data)
  return (
    <Frame>
      <span>선택한 지역의 숙소</span>
      {data.map((el: any) => (
        <Column key={el.id}>
          <div>
            <img src={el.thumbnail_image} width='300' height='220' />
          </div>
          <InfoBlock>
            <div>
              <div>
                <Title>{el.name}</Title>
                <RoomInfo>
                  침실 {el.home_details.bed}개 · 욕실 {el.home_details.bath_room}개 · 최대 인원
                  {el.home_details.max_guest}명
                </RoomInfo>
              </div>
              <LikeBtn>
                <IconButton aria-label='delete'>
                  <FavoriteBorderIcon fontSize='large' />
                </IconButton>
                {/* <IconButton aria-label='delete'>
                  <FavoriteIcon color='secondary' />
                </IconButton> */}
              </LikeBtn>
            </div>
            <div>
              <Review>
                ⭐{el.review.star.toFixed(1)}
                <span> (후기 {el.review.review}개)</span>
              </Review>
              <Price>
                <div>
                  ₩ {el.price_per_date} <span>/ 박</span>
                </div>
                <div>총액 ₩{el.total_price}</div>
              </Price>
            </div>
          </InfoBlock>
        </Column>
      ))}
    </Frame>
  )
}
const LikeBtn = styled.div`
  position: absolute;
  top: -16px;
  left: 356px;
`
const Price = styled.div`
  display: flex;
  flex-flow: column;
  div {
    font-weight: ${({ theme }) => theme.fontWeight.w2};
    font-size: ${({ theme }) => theme.fontSize.x_sm};
    span {
      font-weight: ${({ theme }) => theme.fontWeight.w1};
    }
    align-self: flex-end;
    &:last-child {
      color: ${({ theme }) => theme.color.grey_2};
      font-size: ${({ theme }) => theme.fontSize.super_sm};
      font-weight: ${({ theme }) => theme.fontWeight.w1};
      text-decoration: underline;
    }
  }
`
const Review = styled.div`
  font-weight: ${({ theme }) => theme.fontWeight.w2};
  span {
    color: ${({ theme }) => theme.color.grey_2};
    font-weight: ${({ theme }) => theme.fontWeight.w1};
  }
`
const RoomInfo = styled.div`
  color: ${({ theme }) => theme.color.grey_3};
  font-size: ${({ theme }) => theme.fontSize.super_sm};
`
const Title = styled.div`
  font-size: ${({ theme }) => theme.fontSize.x_sm};
  &:after {
    content: '⁘⁘⁘⁘⁘⁘⁘⁘⁘';
    display: block;
    color: ${({ theme }) => theme.color.grey_2};
  }
`
const InfoBlock = styled.div`
  display: flex;
  flex-flow: column;
  justify-content: space-between;
  padding: 10px 24px;
  width: 400px;
  div {
    &: first-child {
      position: relative;
    }
    &: last-child {
      display: flex;
      justify-content: space-between;
    }
  }
`
const Column = styled.div`
  display: flex;
  padding: 24px 0;
  border-bottom: 1px solid ${({ theme }) => theme.color.grey_2};
  img {
    border-radius: 10px;
  }
`
const Frame = styled.div`
  width: 50%;
  margin: 24px;
`
export default HouseList
