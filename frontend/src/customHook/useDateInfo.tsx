interface IDate {
	year: number
	month: number
	date: number
	day: number
	dateOfLast: number
}

export const DateInfo = (today: Date): IDate => {
	const year = today.getFullYear()
	const month = today.getMonth() + 1
	const date = today.getDate()
	const day = today.getDay()
	const dateOfLast = new Date(year, month - 1, 0).getDate()

	return { year, month, date, day, dateOfLast }
}

export const dateToString = (clickedDate: string | number) => {
	if (clickedDate === '날짜입력') return '날짜입력'
	const dateValue = Number(clickedDate)
	return new Date(dateValue).getMonth() + 1 + '월' + new Date(dateValue).getDate() + '일'
}
