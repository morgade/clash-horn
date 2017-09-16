import jsonRpc from '../../remote/json-rpc'
import {sync, async, route} from './util'
import {serviceFailure, serviceRequest} from './commons'

// endpoint
let endpoint = "/api/clash-horn";

// SYNCHRONOUS ACTION CREATORS
export const setAccountClan = sync('setAccountClan', 'clan');
export const fetchClanDataRequest = sync('fetchClanDataRequest', 'clan');
export const fetchClanDataSuccess = sync('fetchClanDataSuccess', 'clan');
export const fetchAndSetAccountClanSuccess = sync('fetchAndSetAccountClanSuccess', 'clan');

// ASYNCHRONOUS FETCH ACTION HANDLERS
export const fetchClanData = async( params => jsonRpc.call(endpoint, 'fethClanData', params), fetchClanDataRequest, fetchClanDataSuccess, serviceFailure); 

// SPECIALIZED ACTION HANDLERS
//export const focusOrFetchComment = function (commentId) {
//    return (dispatch, getState) => {
//        let focused = getState().comments.list.find( c => c.id === commentId) || null;
//        return (!focused || focused.id!==commentId) ?  
//            dispatch(fetchFocusedComment(commentId))
//          : dispatch(focusComment(focused));
//    };
//  };