import styled, { css } from 'styled-components'
import { useRecoilState, useRecoilValue } from 'recoil'
import { checkInMessage, checkOutMessage } from '../../../customHook/atoms'
import { DateInfo } from '../../../customHook/useDateInfo'
interface ICheckProps {
  year?: number
  currentMonth?: number
  dateEl?: number | null
  nonClickable?: boolean
}
interface IDate {
  currentMonth: number
}
interface IClick {
  nonClickable: boolean
  onClick: () => void
}

const Calendar: React.FunctionComponent<IDate> = ({ currentMonth }) => {
  const dayList: string[] = ['일', '월', '화', '수', '목', '금', '토']
  const MonthList = Array.from({ length: 12 }, (_, i) => i + 1)

  let { year, month, date } = DateInfo(new Date())
  let currentYear = year
  let index
  if (currentMonth > 12) {
    index = (currentMonth % 12) - 1
    year += Math.floor(currentMonth / 12)
    currentMonth = MonthList[index]
  }
  if (currentMonth < 1) {
    index = (currentMonth % 12) + 12
    year =
      currentMonth === 0
        ? year + Math.floor(currentMonth - 1 / 12)
        : year + Math.floor(currentMonth / 12)
    currentMonth = MonthList[index - 1]
  }
  const { day: dayOfFirst, dateOfLast } = DateInfo(new Date(`${year}-${currentMonth}-1`))

  const DateArray = Array.from({ length: dateOfLast + dayOfFirst }, (_, i) => {
    if (i < dayOfFirst) return null
    return i - dayOfFirst + 1
  })

  const nonClickableCheck = (dateEl: number | null): boolean => {
    if (typeof dateEl === 'number') {
      if (
        year < currentYear ||
        (year === currentYear && currentMonth < month) ||
        (year === currentYear && currentMonth === month && date > dateEl)
      )
        return false
      return true
    }
    return false
  }

  //handleClick 안에서 사용하기
  const [checkIn, setCheckIn] = useRecoilState(checkInMessage)
  const [checkOut, setCheckOut] = useRecoilState(checkOutMessage)
  const handleDateCLick = ({ year, currentMonth, dateEl, nonClickable }: ICheckProps): void => {
    if (!nonClickable) return

    const clickedDate = new Date(`${year}-${currentMonth}-${dateEl}`).valueOf()
    if (checkIn === '날짜입력') setCheckIn(clickedDate)
    if (checkIn !== '날짜입력' && checkIn <= clickedDate) setCheckOut(clickedDate)
    if (checkIn !== '날짜입력' && checkIn > clickedDate) setCheckIn(clickedDate)
  }

  return (
    <CalendarBox>
      <DayBlock>
        {dayList.map((day, idx) => (
          <Day key={idx}>{day}</Day>
        ))}
      </DayBlock>
      <NonFixedArea>
        <YearMonth>
          {year}년&nbsp;&nbsp;{currentMonth}월
        </YearMonth>
        <DateSection>
          {DateArray.map((dateEl, idx) => (
            <DateBlock
              key={idx}
              nonClickable={nonClickableCheck(dateEl)}
              onClick={() =>
                handleDateCLick({
                  year,
                  currentMonth,
                  dateEl,
                  nonClickable: nonClickableCheck(dateEl),
                })
              }
            >
              {dateEl}
            </DateBlock>
          ))}
        </DateSection>
      </NonFixedArea>
    </CalendarBox>
  )
}
const CalendarBox = styled.div`
  margin: 65px 90px;
`

const DateSection = styled.div`
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  grid-template-rows: repeat(6, 1fr);
  margin-top: 55px;
`
const DateBlock = styled.div<IClick>`
  width: 50px;
  height: 50px;
  margin-top: 4px;
  font-weight: ${({ theme }) => theme.fontWeight.w2};
  font-size: ${({ theme }) => theme.fontSize.super_sm};
  display: flex;
  align-items: center;
  justify-content: center;
  color: ${({ theme }) => theme.color.grey_2};
  ${(props) =>
    props.nonClickable &&
    css`
      color: ${props.theme.color.black};
      &:hover {
        border: 1px solid;
        border-radius: 50%;
        cursor: pointer;
      }
    `};
`
const NonFixedArea = styled.div`
  width: 336px;
  text-align: center;
`
const YearMonth = styled.div`
  font-weight: ${({ theme }) => theme.fontWeight.w2};
`
const DayBlock = styled.div`
  display: flex;
  position: absolute;
  top: 125px;
`
const Day = styled.div`
  color: ${({ theme }) => theme.color.grey_3};
  font-size: ${({ theme }) => theme.fontSize.super_sm};
  width: 50px;
  text-align: center;
`

export default Calendar
