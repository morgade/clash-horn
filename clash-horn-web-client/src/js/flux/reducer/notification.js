import objectAssign from 'object-assign';

import {reducer} from '../util'
import * as CommentActions from '../actions/comments'
import * as CommonActions from '../actions/commons'


/**
 * Comments reducer state structure
 */
const initialState =  null;

/**
 * Notification reducer
 */
export default reducer(initialState, {
    
    [CommentActions.POST_COMMENT_SUCCESS]: (state, action) =>
        objectAssign({}, state, {
            message: 'Comment posted !',
            level: 'success'
        }),
        
    [CommentActions.DELETE_COMMENT_SUCCESS]: (state, action) =>
        objectAssign({}, state, {
            message: 'Comment deleted !',
            level: 'success'
        }),
    
    [CommonActions.SERVICE_FAILURE]: (state, action) => 
        objectAssign({}, state, {
            message: action.error.message,
            level: 'error'
        })
    
});