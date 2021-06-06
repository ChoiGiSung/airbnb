import { atom } from 'recoil'

//defaultValue
export const defaultValue = {
  placeToSearch: '어디로 여행가세요?',
  checkIn: '날짜입력',
  checkOut: '날짜입력',
}

//Place
export const clickedPlace = atom<string | undefined>({
  key: 'placeToSearch',
  default: defaultValue.placeToSearch,
})

//Calendar
export const checkInMessage = atom<string | number>({
  key: 'checkIn',
  default: defaultValue.checkIn,
})

export const checkOutMessage = atom<string | number>({
  key: 'checkOut',
  default: defaultValue.checkOut,
})

//ClickCheckPlaceToggle
export const clickPlace = atom<boolean>({
  key: 'placeClicked',
  default: false,
})

//ClickCheckIn&Out
export const clickCheckIn = atom<boolean>({
  key: 'checkInClicked',
  default: false,
})
export const clickCheckOut = atom<boolean>({
  key: 'checkOutnClicked',
  default: false,
})
