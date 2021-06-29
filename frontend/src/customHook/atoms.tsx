import { atom, selector } from 'recoil'
import { dateToString } from './useDateInfo'
//defaultValue________________________________________
export const defaultValue = {
  placeAdjacent: '가까운 여행지 둘러보기',
  placeToSearch: '어디로 여행가세요?',
  checkIn: '날짜입력',
  checkOut: '날짜입력',
  fee: '금액대 설정',
  guest: '게스트 추가',
}
//Place_______________________________________________
export const clickedPlace = atom<string>({
  key: 'place',
  default: defaultValue.placeToSearch,
})
//Calendar___________________________________________
export const checkInMessage = atom<string | number>({
  key: 'checkIn',
  default: defaultValue.checkIn,
})
export const checkOutMessage = atom<string | number>({
  key: 'checkOut',
  default: defaultValue.checkOut,
})
export const scheduleMessage = selector({
  key:'scheduleMsg',
  get: ({get})=>{
    const checkIn = get(checkInMessage)
    const checkOut = get(checkOutMessage)
    let msg = (checkIn === defaultValue.checkIn)
    ? defaultValue.checkIn
    : dateToString(checkIn) + '-' + dateToString(checkOut)
    return msg;
  }
})

//Fee_______________________________________________
export const FeeMin = atom<string | number>({
  key: 'priceMin',
  default: defaultValue.fee,
})
export const FeeMax = atom<number>({
  key: 'priceMax',
  default: 0,
})
export const FeeMinChange = atom<number>({
  key: 'minFeePercent',
  default: 0,
})
export const FeeMaxChange = atom<number>({
  key: 'maxFeePercent',
  default: 0,
})
export const FeeMsg = selector({
  key: 'getFeeMsg',
  get({get}){
    const priceMin = get(FeeMin)
    const priceMax = get(FeeMax)
    const minFeePercent = get(FeeMinChange)
    const maxFeePercent = get(FeeMaxChange)
    let feeMsg =
    priceMin === defaultValue.fee
      ? priceMin
      : `${Number(priceMin) + Number(minFeePercent)} ~ ${
          Number(priceMax) - Number(maxFeePercent)
        }원`
    return feeMsg;
  }
})
//personnel__________________________________________

export const personnelAudult = atom<number>({
  key: 'adult',
  default: 0,
})
export const personnelChild = atom<number>({
  key: 'child',
  default: 0,
})
export const personnelBaby = atom<number>({
  key: 'baby',
  default: 0,
})
export const personnelMessage = atom<string>({
  key: 'guestMsg',
  default: defaultValue.guest,
})
export const filterPersonnelMessage = selector({
  key: 'returnGuestMsg',
  get({get}){
    const adult=Number(get(personnelAudult))
    const child=Number(get(personnelChild))
    const baby=Number(get(personnelBaby))
    return (adult+child+baby!==0)
    ?`게스트 ${adult + child}명, 유아${baby}명`
    : defaultValue.guest
  }
})

export const totalPerson = selector({
  key: 'totalPersonnel',
   get({get}){
    const adult=get(personnelAudult)
    const child=get(personnelChild)
    const baby=get(personnelBaby)
    return {adult, child, baby}
  },
  set({set}, newValues:any){
    set(personnelAudult, newValues)
    set(personnelChild, newValues)
    set(personnelBaby, newValues)
  }
})

//ClickCtrl___________________________________________
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

//RoomData__________________________________________________
export const RoomData = atom<any>({
  key: 'RoomDatas',
  default: [],
})

//LoginState________________________________________________
export const Login = atom({
  key: 'LoginState',
  default: false,
})

//reserveCheck_Promise ATOM_________________________________
export const reserveMsg = atom({
  key: 'confirmMsg',
  default: false
})

export const RecoilValueGroup = selector({
  key:'recoilValues', 
  get: ({get})=>{
    const place = get(clickedPlace) 
    const checkIn = get(checkInMessage)
    const checkOut = get(checkOutMessage)
    const priceMin =get(FeeMin)
    const priceMax =get(FeeMax)
    const minFeePercent = get(FeeMinChange)
    const maxFeePercent = get(FeeMaxChange)
    const adult = get(personnelAudult)
    const child = get(personnelChild)
    const baby = get(personnelBaby)
    const scheduleMsg = get(scheduleMessage)
    const guestMsg = get(filterPersonnelMessage)
  return {place, checkIn, checkOut, scheduleMsg, priceMin, priceMax, minFeePercent, maxFeePercent, adult, child, baby, guestMsg}
  },
  set:({set}, newValue:any)=>{
    const {place, checkIn, checkOut, priceMin, priceMax, minFeePercent, maxFeePercent, adult, child, baby } = newValue
    set(clickedPlace, place === defaultValue.placeToSearch ? '근처 추천 장소' : place)
    set(checkInMessage, checkIn)
    set(checkOutMessage, checkOut)
    set(personnelAudult, Number(adult))
    set(personnelChild, Number(child))
    set(personnelBaby, Number(baby))
    set(FeeMin, priceMin)
    set(FeeMax, priceMax)
    set(FeeMinChange, minFeePercent)
    set(FeeMaxChange, maxFeePercent)
  }})
