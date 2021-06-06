import { useRef } from 'react'
import styled from 'styled-components'
import { PlaceSection, ModalWrapper } from '../../../style/BarStyle'
import useModalCtrl from '../../../customHook/useModalCtrlArray'
import ModalCalendar from './ModalCalendar'
import CheckIn from './CheckIn'
import CheckOut from './CheckOut'

const Date = () => {
	const checkInToggle = useRef<HTMLDivElement>(null)
	const checkOutToggle = useRef<HTMLDivElement>(null)
	const CalendarModal = useRef<HTMLDivElement>(null)
	const open = useModalCtrl({
		toggle: [checkInToggle, checkOutToggle],
		modal: CalendarModal,
		init: false,
	})

	return (
		<PlaceSection>
			<DateFlexWrapper>
				<CheckIn open={open} type='date' checkInToggle={checkInToggle} />
				<CheckOut open={open} type='date' checkOutToggle={checkOutToggle} />
			</DateFlexWrapper>
			{open && (
				<ModalWrapper ref={CalendarModal} modalType='date'>
					<ModalCalendar modalType='date' />
				</ModalWrapper>
			)}
		</PlaceSection>
	)
}

const DateFlexWrapper = styled.div`
	display: flex;
`

export default Date
