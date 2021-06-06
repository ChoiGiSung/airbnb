import axios from 'axios'
async function getData() {
  const response = await axios.get(
    `http://13.125.140.183/search/prices?check-in=2021-03-02&check-out=2021-03-04`
  )
  return response
}

export default getData
