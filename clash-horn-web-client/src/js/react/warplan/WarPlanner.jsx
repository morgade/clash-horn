import React from 'react';
import Glyphicon from 'react-bootstrap/lib/Glyphicon';

import WarBoard from './WarBoard.jsx';
import ClanAccountNavigator from '../ui/ClanAccountNavigator.jsx';

import { connect } from 'react-redux';
import { fetchUserBoundClanAccount, fetchWarPlan } from '../../flux/actions/clans';

export class WarPlanner extends React.Component {
    
    constructor(props) {
       super(props);
      
        if (!this.props.clanAccount || this.props.clanAccount.id!==this.props.match.params.cid) {
           // Clan account was not loaded on store or do not match url provided id, so we need to fetch it
           this.props.dispatch(fetchUserBoundClanAccount(this.props.match.params.cid));
       } else {
           // Clan account loaded, load warplan
           this.loadWarPlan(this.props.clanAccount.id, this.props.match.params.wid);
       }
   }
   
    componentWillReceiveProps(nextProps) {
        if (!this.props.clanAccount && nextProps.clanAccount) {
            // Clan acount loaded, warplan pending
           this.loadWarPlan(nextProps.clanAccount.id, this.props.match.params.wid);
        }
    }
    
    loadWarPlan(clanAccountId, warPlanId, forceReload) {
        if (!this.props.warPlan || forceReload) {
            this.props.dispatch(fetchWarPlan(clanAccountId, warPlanId==='current'?null:warPlanId));
        }
    }
    
    render() {
        // Resolve a possible status message
        let message = null;
        if (this.props.fetching['fetchUserBoundClanAccount']) {
            message = (<span><Glyphicon glyph="refresh" /> Loading clan account data ...</span>);
        } else if (this.props.fetching['fetchWarPlan'] || !this.props.warPlan) {
            message = (<span><Glyphicon glyph="refresh" /> Loading war data ...</span>);
        } else if (!this.props.clanAccount) {
            message = (
                <div>
                    <p>A clan account could not be found for the id <strong>{this.props.match.params.cid}</strong>.</p>
                    <p>Check your ID or register a new account <Link to="/register">here</Link></p>
                </div>
            );
        }
    

        return (
            <ClanAccountNavigator clanAccount={this.props.clanAccount} active="/current" fadeInWhen={message==null}>
                {message || 
                    <div>
                        This is the <strong>{this.props.clanAccount.clan.name}</strong> war planner.
                        <WarBoard war={this.props.warPlan} />
                    </div>
                }
            </ClanAccountNavigator>
        );
    }
};

export default connect(
    (store) => ({
        fetching: store.clans.fetching,
        clanAccount: store.clans.clanAccount,
        warPlan: store.clans.fetchedWarPlan
    })
)(WarPlanner);
