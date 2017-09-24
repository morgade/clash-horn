import jsonRpc from '../../remote/json-rpc'
import {sync, async, route} from './util'
import {serviceFailure, serviceRequest} from './commons'

// endpoint
let endpoint = "/api/clash-horn";

// SYNCHRONOUS ACTION CREATORS
export const setAccountClan = sync('setAccountClan', 'clan');

// SYNCHRONOUS FETCH ACTION CREATORS
export const fetchClanDataRequest = sync('fetchClanDataRequest', 'clan');
export const fetchClanDataSuccess = sync('fetchClanDataSuccess', 'clan');
export const registerClanAccountRequest = sync('registerClanAccountRequest', 'tag', 'id');
export const registerClanAccountSuccess = sync('registerClanAccountSuccess', 'clanAccount');
export const fetchClanAccountRequest = sync('fetchClanAccountRequest', 'id');
export const fetchClanAccountSuccess = sync('fetchClanAccountSuccess', 'clanAccount');
export const fetchWarPlanRequest = sync('fetchWarPlanRequest', 'accountId', 'warPlanId');
export const fetchWarPlanSuccess = sync('fetchWarPlanSuccess', 'warPlan');
export const pushToAttackQueueRequest = sync('pushToAttackQueueRequest', 'warPlanId', 'enemyPosition', 'memberPosition');
export const pushToAttackQueueSuccess = sync('pushToAttackQueueSuccess', 'warPlan');

// ASYNCHRONOUS FETCH ACTION HANDLERS
export const fetchClanData =    async( params => jsonRpc.call(endpoint, 'fetchClanData', params), fetchClanDataRequest, fetchClanDataSuccess, serviceFailure); 
export const registerClanAccount = async( params => jsonRpc.call(endpoint, 'registerClanAccount', params), registerClanAccountRequest, registerClanAccountSuccess, serviceFailure); 
export const fetchClanAccount = async( params => jsonRpc.call(endpoint, 'fetchClanAccount', params), fetchClanAccountRequest, fetchClanAccountSuccess, serviceFailure); 
export const fetchWarPlan = async( params => jsonRpc.call(endpoint, 'fetchWarPlan', params), fetchWarPlanRequest, fetchWarPlanSuccess, serviceFailure); 
export const pushToAttackQueue = async( params => jsonRpc.call(endpoint, 'pushToAttackQueue', params), pushToAttackQueueRequest, pushToAttackQueueSuccess, serviceFailure); 

// SPECIALIZED ACTION HANDLERS
//export const focusOrFetchComment = function (commentId) {
//    return (dispatch, getState) => {
//        let focused = getState().comments.list.find( c => c.id === commentId) || null;
//        return (!focused || focused.id!==commentId) ?  
//            dispatch(fetchFocusedComment(commentId))
//          : dispatch(focusComment(focused));
//    };
//  };
