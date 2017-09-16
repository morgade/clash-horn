import rest from '../../rest'
import {sync, async, route} from '../util'
import {serviceFailure, serviceRequest} from './commons'

// SYNCHRONOUS ACTION CREATORS
export const setAccountClan = sync('setAccountClan', 'clan');
export const fetchClanDataSuccess = sync('fetchClanDataSuccess', 'clan');
export const fetchAndSetAccountClanSuccess = sync('fetchAndSetAccountClanSuccess', 'clan');

// ASYNCHRONOUS FETCH ACTION HANDLERS
//export const fetchClanData = async(args => rest.post('/clanData', args[0]), fetchClanDataRequest, fetchClanDataSuccess, serviceFailure); 
export const fetchClanData = (tag) => fetchClanDataSuccess({ tag:tag, name:'TJF', description:'Somos um clã de guerra ...'}); // Temporary
export const fetchAndSetAccountClan = (tag) => fetchAndSetAccountClanSuccess({ tag:tag, name:'TJF', description:'Somos um clã de guerra ...'}); // Temporary

// SPECIALIZED ACTION HANDLERS
//export const focusOrFetchComment = function (commentId) {
//    return (dispatch, getState) => {
//        let focused = getState().comments.list.find( c => c.id === commentId) || null;
//        return (!focused || focused.id!==commentId) ?  
//            dispatch(fetchFocusedComment(commentId))
//          : dispatch(focusComment(focused));
//    };
//  };