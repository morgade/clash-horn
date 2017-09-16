import objectAssign from 'object-assign';
import {reducer} from '../util'


/**
 * Reducer state structure
 */
const initialState =  {
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
            pendingFetch: false,
            fetchedClan: action.clan
        }),
        
    fetchAndSetAccountClanSuccess: (state, action) =>  
        objectAssign({}, state, { 
            pendingFetch: false,
            fetchedClan: action.clan,
            accountClan: action.clan
        }),
        
    fetchClanDataRequest: (state, action) =>  
        objectAssign({}, state, { 
            pendingFetch: true
        }),
    
    serviceFailure: (state, action) =>
        objectAssign({}, state, { 
            pendingFetch: false
        })
    
});