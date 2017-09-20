import thunk from 'redux-thunk'
import createLogger from 'redux-logger'
import { createStore, combineReducers, applyMiddleware } from 'redux'

import clans from './reducer/clans';
import notification from './reducer/notification';


const middlewares = [ thunk ];
if ( process.env.NODE_ENV !== "production" ) {
    middlewares.push( createLogger() );
}

const mainReducer = combineReducers({clans, notification});

export default createStore(mainReducer, applyMiddleware( ...middlewares ) );
