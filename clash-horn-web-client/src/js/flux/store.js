import thunkMiddleware from 'redux-thunk'
import createLogger from 'redux-logger'
import { createStore, combineReducers, applyMiddleware } from 'redux'

import clans from './reducer/clans';
import notification from './reducer/notification';


const mainReducer = combineReducers({clans, notification});

export default createStore(mainReducer,
                            applyMiddleware(
                                thunkMiddleware, // lets us dispatch() functions
                                createLogger() // neat middleware that logs actions
                            )
                );
