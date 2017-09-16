import objectAssign from 'object-assign';
import {reducer} from './util'


/**
 * Reducer state structure
 */
const initialState =  {
    fetching: null,
    clanAccountId: null,
    accountClan: null,
    fetchedClan: null
};

/**
 * Create and export the reducer
 */
export default reducer(initialState, {
        
    setAccountClan: (state, action) =>  
        objectAssign({}, state, { 
            accountClan: action.clan
        }),
        
    fetchClanDataSuccess: (state, action) =>  
        objectAssign({}, state, { 
            fetchedClan: action.clan,
            fetching: null
        }),
        
    fetchAndSetAccountClanSuccess: (state, action) =>  
        objectAssign({}, state, { 
            fetchedClan: action.clan,
            accountClan: action.clan,
            fetching: null
        }),
        
    fetchClanDataRequest: (state, action) =>  
        objectAssign({}, state, { 
            fetchedClan: null,
            fetching: 'fetchClanDataRequest'
        }),
    
    serviceFailure: (state, action) =>
        objectAssign({}, state, { 
            fetching: null
        })
    
});