import objectAssign from 'object-assign';

import {reducer} from '../util'


/**
 * Comments reducer state structure
 */
const initialState =  null;

/**
 * Notification reducer
 */
export default reducer(initialState, {
    
    serviceFailure: (state, action) => 
        objectAssign({}, state, {
            message: action.error.message,
            level: 'error'
        })
    
});