import { hashHistory } from 'react-router'

import { sync } from '../util'

// ACTION TYPES
export const SERVICE_FAILURE = 'SERVICE_FAILURE';

// SYNCHRONOUS ACTION CREATORS
export const serviceFailure = sync(SERVICE_FAILURE, 'error');