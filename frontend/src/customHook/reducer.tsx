export interface IAction {
  type: string
  data?: any
  error?: Error
}

export function reducer(state: object, action: IAction) {
  switch (action.type) {
    case 'LOADING':
      return { loading: true, data: null, error: null }
    case 'SUCCESS':
      return { loading: false, data: action.data, error: null }
    case 'ERROR':
      return { loading: false, data: null, error: action.error }
    default:
      throw new Error(`Trouble issued :${action.type}`)
  }
}
