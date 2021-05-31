import React from 'react'
import ReactDOM from 'react-dom'
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'
import { RecoilRoot } from 'recoil'
import './index.css'
import App from './App'
import SearchResult from './components/searchResult/SearchResult'
import reportWebVitals from './reportWebVitals'

ReactDOM.render(
  <React.StrictMode>
    <RecoilRoot>
      <Router>
        <Switch>
          <Route path='/' component={App} exact />
          <Route
            path='/searchResult/:place/:checkIn/:checkOut/:priceMin/:priceMax/:minFeePercent/:maxFeePercent/:adult/:child/:baby'
            component={SearchResult}
          />
        </Switch>
      </Router>
    </RecoilRoot>
  </React.StrictMode>,
  document.getElementById('root')
)

reportWebVitals()
