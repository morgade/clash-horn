import { hashHistory } from 'react-router'

import { sync } from './util'

// SYNCHRONOUS ACTION CREATORS
export const serviceRequest = sync('serviceRequest', 'data');
export const serviceFailure = sync('serviceFailure', 'error');