import objectAssign from 'object-assign';

import {reducer} from '../util'
import * as CommentActions from '../actions/comments'
import * as CommonActions from '../actions/commons'


/**
 * Comments reducer state structure
 */
const initialState =  {
    list: [],
    focused: null,
    listInvalidated: false,
    pendingFetch: false
};

/**
 * Comments reducer
 */
export default reducer(initialState, {
        
    [CommentActions.FETCH_COMMENTS_SUCCESS]: (state, action) =>  
        objectAssign({}, state, { 
            pendingFetch: false,
            listInvalidated: false,
            list: action.comments
        }),
        
    [CommentActions.FETCH_COMMENTS_REQUEST]: (state, action) =>  
        objectAssign({}, state, { 
            pendingFetch: true
        }),
    
    [CommentActions.FOCUS_COMMENT]: (state, action) =>  
        objectAssign({}, state, { 
            focused: action.comment
        }),
    
    [CommentActions.FETCH_FOCUSED_COMMENT_REQUEST]: (state, action) =>  
        objectAssign({}, state, { 
            pendingFetch: true
        }),
    
    [CommentActions.FETCH_FOCUSED_COMMENT_SUCCESS]: (state, action) =>  
        objectAssign({}, state, { 
            focused: action.comment,
            pendingFetch: false
        }),
    
    [CommentActions.POST_COMMENT_REQUEST]: (state, action) => 
        objectAssign({}, state, { 
            pendingFetch: true,
        }),
        
    [CommentActions.POST_COMMENT_SUCCESS]: (state, action) =>
        objectAssign({}, state, { 
            pendingFetch: false,
            listInvalidated: true
        }),
    
    [CommentActions.DELETE_COMMENT_REQUEST]: (state, action) => 
        objectAssign({}, state, { 
            pendingFetch: true,
        }),
        
    [CommentActions.DELETE_COMMENT_SUCCESS]: (state, action) =>
        objectAssign({}, state, { 
            pendingFetch: false,
            focused: null,
            listInvalidated: true
        }),
        
    [CommonActions.SERVICE_FAILURE]: (state, action) =>
        objectAssign({}, state, { 
            pendingFetch: false
        })
    
});