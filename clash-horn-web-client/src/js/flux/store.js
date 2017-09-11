import thunkMiddleware from 'redux-thunk'
import createLogger from 'redux-logger'
import { createStore, combineReducers, applyMiddleware } from 'redux'

import comments from './reducer/comments';
import notification from './reducer/notification';


const mainReducer = combineReducers({comments, notification});

export default createStore(mainReducer,
                            applyMiddleware(
                                thunkMiddleware, // lets us dispatch() functions
                                createLogger() // neat middleware that logs actions
                            )
                );
