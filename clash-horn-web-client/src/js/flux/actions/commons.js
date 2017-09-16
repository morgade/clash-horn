import { hashHistory } from 'react-router'

import { sync } from '../util'

// SYNCHRONOUS ACTION CREATORS
export const serviceRequest = sync('SERVICE_REQUEST');
export const serviceFailure = sync('SERVICE_FAILURE', 'error');