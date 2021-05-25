import { useEffect, useState } from 'react'

type CtrlModal = {
	toggle: unknown[]
	modal: unknown
	init: boolean
}
const useModalCtrl = ({ toggle, modal, init }: CtrlModal): boolean => {
	const [open, setOpen] = useState(init)
	useEffect(() => {
		let prev: number = 0
		let curr: number
		const handle = (e: MouseEvent): void => {
			let count = 0
			const { target } = e
			const toggleTarget = toggle.map((el) => el.current)
			const ModalTarget = modal.current

			toggleTarget.forEach((el: any, idx: number) => {
				if (el.contains(target)) {
					curr = idx
					count++
				}
			})
			console.log(count)
			if (count === 1 && prev === curr) setOpen((open) => !open)
			else if (count === 1 && prev !== curr) setOpen(true)
			else if (ModalTarget?.contains(target)) setOpen(true)
			else setOpen(false)
			count = 0
			prev = curr
		}
		document.addEventListener('click', handle)
		return () => {
			document.removeEventListener('click', handle)
		}
	}, [])

	return open
}

export default useModalCtrl
