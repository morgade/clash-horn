import { hashHistory } from 'react-router'

import { sync } from './util'

// SYNCHRONOUS ACTION CREATORS
export const serviceRequest = sync('SERVICE_REQUEST', 'data');
export const serviceFailure = sync('SERVICE_FAILURE', 'error');