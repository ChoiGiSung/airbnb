import { useState } from 'react'
import styled from 'styled-components'
import IconButton from '@material-ui/core/IconButton'
import FavoriteBorderIcon from '@material-ui/icons/FavoriteBorder'
import FavoriteIcon from '@material-ui/icons/Favorite'
import useAxios from '../../customHook/useAxios'
import { wishToggle } from '../../customHook/axiosAPI'
interface IWish {
  setOpen: React.Dispatch<React.SetStateAction<boolean>>
  targetData : any
}
function WishList({setOpen, targetData}:IWish){
  const [isHeartClick, setHeartClick] = useState<boolean>(false)
  const {state, fetchData} = useAxios(()=>wishToggle(targetData.id),[], true)
  const handleLikeClick = (type:string, id:number) =>{
    if (type==='like'){
      setOpen(false)
      setHeartClick(true)
    }else{
      setOpen(false)
      setHeartClick(false)
    }
    const response = fetchData()
    // const response = wishToggle(id)
    console.log(response) //코스에러.
    }
  

  return !isHeartClick ? (
      <LikeBtn className="likeUnClick" onClick={()=>handleLikeClick('like',targetData.id)}>
        <IconButton  aria-label='like'>
          <FavoriteBorderIcon fontSize='large' />
        </IconButton>
      </LikeBtn>
  ) : (<LikeBtn className="likeClick" onClick={()=>handleLikeClick('unlike',targetData.id)}>
        <IconButton aria-label='like'>
          <FavoriteIcon color='secondary' />
        </IconButton> 
      </LikeBtn>)
}

const LikeBtn = styled.div`
  position: absolute;
  top: -16px;
  left: 356px;
`
export default WishList