import objectAssign from 'object-assign';
import {reducer} from './util'


/**
 * Reducer state structure
 */
const initialState =  {
    // map used to control async fetch actions in progress
    fetching: {  },
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
        
    fetchClanDataRequest: (state, action) =>  
        objectAssign({}, state, clearFetchState, { 
            fetchedClan: null,
            fetching: { 'fetchClanDataRequest': action.args }
        }),
        
    fetchClanDataSuccess: (state, action) =>  
        objectAssign({}, state, clearFetchState,  { 
            fetchedClan: action.clan
        }),
        
    registerClanAccountRequest: (state, action) =>  
        objectAssign({}, state, clearFetchState, { 
            clanAccount: null,
            openWarPlan: null,
            fetching: { 'registerClanAccount': action.args }
        }),
        
    registerClanAccountSuccess: (state, action) =>  
        objectAssign({}, state, clearFetchState, { 
            clanAccount: action.clanAccount
        }),
        
    fetchClanAccountRequest: (state, action) =>  
        objectAssign({}, state, clearFetchState, { 
            clanAccount: null,
            openWarPlan: null,
            fetching: { 'fetchClanAccount': action.args }
        }),
        
    fetchClanAccountSuccess: (state, action) =>  
        objectAssign({}, state, clearFetchState, { 
            clanAccount: action.clanAccount
        }),
        
    fetchWarPlanRequest: (state, action) =>  
        objectAssign({}, state, clearFetchState, { 
            openWarPlan: null,
            fetching: { 'fetchWarPlan': action.args }
        }),
        
    fetchWarPlanSuccess: (state, action) =>  
        objectAssign({}, state, clearFetchState, { 
            openWarPlan: action.warPlan
        }),
        
    pushToAttackQueueRequest: (state, action) =>  
        objectAssign({}, state, clearFetchState, { 
            fetching: { 'pushToAttackQueue': action.args }
        }),
        
    pushToAttackQueueSuccess: (state, action) =>  
        objectAssign({}, state, clearFetchState, { 
            openWarPlan: action.warPlan
        }),
        
    removeFromAttackQueueRequest: (state, action) =>  
        objectAssign({}, state, clearFetchState, { 
            fetching: { 'removeFromAttackQueue': action.args }
        }),
        
    removeFromAttackQueueSuccess: (state, action) =>  
        objectAssign({}, state, clearFetchState, { 
            openWarPlan: action.warPlan
        }),
        
//    pushAttackQueueOnOpenWarPlan: (state, action) => {
//        var newState = objectAssign({}, state);
//        newState.openWarPlan.positions[action.enemyPosition-1].attackQueue.push(action.attackerPosition);
//        return newState;
//    },
    
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
