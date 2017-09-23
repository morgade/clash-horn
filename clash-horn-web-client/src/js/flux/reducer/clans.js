import objectAssign from 'object-assign';
import {reducer} from './util'


/**
 * Reducer state structure
 */
const initialState =  {
    // map used to control async fetch actions in progress
    fetching: {  },
    // indicates the last operation executed
    lastOperation: null,
    // user context bound clan account
    clanAccount: null,
    // clan obtained in the las fetch clan action
    fetchedClan: null,
    // war obtained in the last fetchWarPlan action
    openWarPlan: null,
    // 
    cocApiLatestError: null
};

const clearFetchState = {
    fetching: {  },
    cocApiLatestError: null
};

/**
 * Create and export the reducer
 */
export default reducer(initialState, {
        
    setAccountClan: (state, action) =>  
        objectAssign({}, state, { 
            accountClan: action.clan
        }),
        
    pushAttackQueueOnOpenWarPlan: (state, action) => {
        var newState = objectAssign({}, state);
        newState.openWarPlan.positions[action.enemyPosition-1].attackQueue.push(action.attackerPosition);
        return newState;
    },
        
    fetchClanDataRequest: (state, action) =>  
        objectAssign({}, state, clearFetchState, { 
            fetchedClan: null,
            fetching: { 'fetchClanDataRequest': true }
        }),
        
    fetchClanDataSuccess: (state, action) =>  
        objectAssign({}, state, clearFetchState,  { 
            fetchedClan: action.clan,
            lastOperation: 'fetchClanData'
        }),
        
    registerClanAccountRequest: (state, action) =>  
        objectAssign({}, state, clearFetchState, { 
            clanAccount: null,
            openWarPlan: null,
            fetching: { 'registerClanAccount': true }
        }),
        
    registerClanAccountSuccess: (state, action) =>  
        objectAssign({}, state, clearFetchState, { 
            clanAccount: action.clanAccount,
            lastOperation: 'registerClanAccount'
        }),
        
    fetchClanAccountRequest: (state, action) =>  
        objectAssign({}, state, clearFetchState, { 
            clanAccount: null,
            openWarPlan: null,
            fetching: { 'fetchClanAccount': true }
        }),
        
    fetchClanAccountSuccess: (state, action) =>  
        objectAssign({}, state, clearFetchState, { 
            clanAccount: action.clanAccount,
            lastOperation: 'fetchClanAccount'
        }),
        
    fetchWarPlanRequest: (state, action) =>  
        objectAssign({}, state, clearFetchState, { 
            openWarPlan: null,
            fetching: { 'fetchWarPlan': true }
        }),
        
    fetchWarPlanSuccess: (state, action) =>  
        objectAssign({}, state, clearFetchState, { 
            openWarPlan: action.warPlan,
            lastOperation: 'fetchWarPlan'
        }),
    
    serviceFailure: (state, action) => {
         // CoC API error codes: -606404, -606500, etc ...
        if (action.error.data && action.error.data.status && Math.ceil(action.error.code / 1000)===-606) {
            return objectAssign({}, state, clearFetchState, { 
                cocApiLatestError: action.error.data
            });
        } else {
            return objectAssign({}, state, clearFetchState);
        }
        
    }
    
});
