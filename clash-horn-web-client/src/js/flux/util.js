/**
 * Generates a synchronous action creator
 * 
 * @param {string} type
 * @param {spread} argNames
 * @returns {Function}
 */
export const sync = (type, ...argNames) => {
  return function(...args) {
    let action = { type };
    argNames.forEach((arg, index) => {
      action[argNames[index]] = args[index];
    });
    return action;
  };
};

/**
 * Generates an async request handlers
 * 
 * @param call {Function} promise to be called upon action
 * @param requestActionCreator {Function} 'loading' action creator function
 * @param successActionCreator {Function} 'success' action creator function
 * @param failureActionCreator {Function} 'failure' action creator function
 * @returns {Function}
 */
export const async = (call, requestActionCreator, successActionCreator, failureActionCreator) => {
    return function(...args) {
        return dispatch => {
            dispatch(requestActionCreator(args));
            return call(args)
                    .then( comments => dispatch(successActionCreator(comments)) )
                    .catch( error => dispatch(failureActionCreator(error)) ); 
        };
    };
};


export function reducer(initialState, handlers) {
    return function (state = initialState, action) {
        if (handlers.hasOwnProperty(action.type)) {
            return handlers[action.type](state, action);
        } else {
            return state;
        }
    };
};