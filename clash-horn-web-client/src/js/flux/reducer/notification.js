import objectAssign from 'object-assign';

import {reducer} from './util'


/**
 * Comments reducer state structure
 */
const initialState =  null;

/**
 * Notification reducer
 */
export default reducer(initialState, {
    
    notifySuccess: (state, action) => 
        objectAssign({}, state, {
            message: action.message,
            level: 'success'
        }),
    
    serviceFailure: (state, action) => {
         // CoC API error codes: -606404, -606500, etc ...
        if (action.error.data && action.error.data.status && Math.ceil(action.error.code / 1000)===-606) {
            //  Don't notify CoC API status messages. Let iterested components do it
            return {};
        } else {
            return objectAssign({}, state, {
                message: action.error.message,
                level: 'error'
            });
        }
        
    }
    
});