/* global Promise */

import rest from '../rest'
import { hashHistory } from 'react-router'
import {sync, async, route} from './util'

// ACTION TYPES
export const POST_COMMENT_REQUEST = 'POST_COMMENT_REQUEST';
export const POST_COMMENT_SUCCESS = 'POST_COMMENT_SUCCESS';
export const FOCUS_COMMENT = 'FOCUS_COMMENT';
export const FETCH_FOCUSED_COMMENT_REQUEST = 'FETCH_FOCUSED_COMMENT_REQUEST';
export const FETCH_FOCUSED_COMMENT_SUCCESS = 'FETCH_FOCUSED_COMMENT_SUCCESS';
export const DELETE_COMMENT_REQUEST = 'DELETE_COMMENT_REQUEST';
export const DELETE_COMMENT_SUCCESS = 'DELETE_COMMENT_SUCCESS';
export const FETCH_COMMENTS_REQUEST = 'FETCH_COMMENTS_REQUEST';
export const FETCH_COMMENTS_SUCCESS = 'FETCH_COMMENTS_SUCCESS';
export const SERVICE_FAILURE = 'SERVICE_FAILURE';
export const ROUTE_CHANGE = 'ROUTE_CHANGE';

// SYNCHRONOUS ACTION CREATORS
export const postCommentRequest = sync(POST_COMMENT_REQUEST, 'comment');
export const postCommentSuccess = sync(POST_COMMENT_SUCCESS);
export const fetchCommentsRequest = sync(FETCH_COMMENTS_REQUEST);
export const fetchCommentsRequestSuccess = sync(FETCH_COMMENTS_SUCCESS, 'comments');
export const fetchFocusedCommentRequest = sync(FETCH_FOCUSED_COMMENT_REQUEST);
export const fetchFocusedCommentSuccess = sync(FETCH_FOCUSED_COMMENT_SUCCESS, 'comment');
export const deleteCommentRequest = sync(DELETE_COMMENT_REQUEST);
export const deleteCommentSuccess = sync(DELETE_COMMENT_SUCCESS, 'comment');
export const focusComment = sync(FOCUS_COMMENT, 'comment');
export const serviceFailure = sync(SERVICE_FAILURE, 'error');
const _routeChange = sync(ROUTE_CHANGE, 'route');

// ASYNCHRONOUS FETCH ACTION HANDLERS
export const postComment = async(args => rest.post('/comment', args[0]), postCommentRequest, postCommentSuccess, serviceFailure); 
export const fetchComments = async(() => rest.get('/comment'), fetchCommentsRequest, fetchCommentsRequestSuccess, serviceFailure); 
export const fetchFocusedComment = async(args => rest.get(`/comment/${args[0]}`), fetchFocusedCommentRequest, fetchFocusedCommentSuccess, serviceFailure); 
export const deleteComment = async(args => rest.delete(`/comment/${args[0]}`), deleteCommentRequest, deleteCommentSuccess, serviceFailure); 

// SPECIALIZED ACTION HANDLERS
export const focusOrFetchComment = function (commentId) {
    return (dispatch, getState) => {
        let focused = getState().comments.list.find( c => c.id === commentId) || null;
        return (!focused || focused.id!==commentId) ?  
            dispatch(fetchFocusedComment(commentId))
          : dispatch(focusComment(focused));
    };
  };

export const routeChange = function(route) {
    return (dispatch, getState) => {
        hashHistory.push(route);
        return dispatch(_routeChange(route));
   };
};