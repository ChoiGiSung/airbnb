import { useEffect } from 'react'
import { useRecoilValue } from 'recoil'
import styled from 'styled-components'
import { searchMap } from '../../customHook/axiosAPI'
import { RoomData } from '../../customHook/atoms'
declare global {
  interface Window {
    naver: any
  }
}

interface IMapData {
  setRoomDatas: any;
}
function Map({ setRoomDatas }: IMapData) {
  const roomDatas = useRecoilValue(RoomData);
  useEffect(() => {
    //지도생성
    if(roomDatas.length === 0) return;
    let container = document.getElementById('map')
    let options = {
      center: new window.naver.maps.LatLng(
        roomDatas[0].coordinate.latitude,
        roomDatas[0].coordinate.longitude
      ),
      zoom: 15,
      zoomControl: true,
      zoomControlOptions: {
        style: window.naver.maps.ZoomControlStyle.SMALL,
      },
    }
    var map = new window.naver.maps.Map(container, options)

    //마커표시
    for (let i = 1; i < roomDatas.length; i++) {
      let latitude = roomDatas[i].coordinate.latitude
      let longitude = roomDatas[i].coordinate.longitude
      let position = new window.naver.maps.LatLng(latitude, longitude)
      let marker = new window.naver.maps.Marker({
        position,
        map,
        icon: {
          content: `<div style="text-align: center; width: 90px; height: 30px; background-color: white; border-radius: 10px; box-shadow: 0px 0px 4px rgba(204, 204, 204, 0.5), 0px 2px 4px rgba(0, 0, 0, 0.25);" >₩${roomDatas[i].price_per_date}</div>`,
          size: new window.naver.maps.Size(22, 35),
          anchor: new window.naver.maps.Point(11, 35),
        },
      })
    }

    window.naver.maps.Event.addListener(map, 'dragend', async function () {
      let newCoord = map.getCenter()
      let newLat = newCoord._lat
      let newLng = newCoord._lng
      const response = await searchMap(newLat, newLng)
      setRoomDatas(response)
    })
    

  }, [roomDatas]);

  return (
    <Frame>
      <div id='map' style={{ width: '100%', height: '86vh' }} />
    </Frame>
  )
}

const Frame = styled.div`
  width: 50vw;
`

export default Map
