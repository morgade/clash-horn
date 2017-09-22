import React from 'react';
import Glyphicon from 'react-bootstrap/lib/Glyphicon';
import { connect } from 'react-redux';

import WarBoard from './WarBoard.jsx';

import { fetchUserBoundClanAccount, fetchWarPlan, pushAttackQueueOnOpenWarPlan } from '../../../flux/actions/clans';
import pathValue from '../../../util/path-value';

export class WarPlanner extends React.Component {
    
    componentWillMount() {
       // Load war plan on mount
        this.loadWarPlan(this.props.clanAccount.id, this.props.match.params.wid);
    }
   
    componentWillReceiveProps(nextProps) {
        // Load war plan on clan account or warId change
        if (    pathValue(nextProps, 'clanAccount', 'id') !== pathValue(this.props, 'clanAccount', 'id') 
             || nextProps.match.params.wid !== this.props.match.params.wid) {
            this.loadWarPlan(nextProps.clanAccount.id, this.props.match.params.wid);
        }
    }
    
    loadWarPlan(clanAccountId, warPlanId, forceReload) {
        if (!this.props.warPlan || forceReload) {
            this.props.dispatch(fetchWarPlan(clanAccountId, warPlanId==='current'?null:warPlanId));
        }
    }
    
    positionQueuePush(enemyPosition, attackerPosition) {
        this.props.dispatch(pushAttackQueueOnOpenWarPlan(enemyPosition, attackerPosition));
    }
    
    render() {
        if (this.props.fetching['fetchUserBoundClanAccount']) {
            return (<span><Glyphicon glyph="refresh" /> Loading clan account data ...</span>);
            
        } else if (this.props.fetching['fetchWarPlan'] || !this.props.warPlan) {
            return (<span><Glyphicon glyph="refresh" /> Loading war data ...</span>);
            
        } else if (!this.props.clanAccount) {
            return (
                <div>
                    <p>A clan account could not be found for the id <strong>{this.props.match.params.cid}</strong>.</p>
                    <p>Check your ID or register a new account <Link to="/register">here</Link></p>
                </div>
            );
    
        } else {
            return (
                <div>
                    { /*This is the <strong>{this.props.clanAccount.clan.name}</strong> war planner.*/ null}
                    <WarBoard war={this.props.warPlan} onPositionQueuePush={this.positionQueuePush.bind(this)} />
                </div>
            );
        }
    

    }
};

export default connect(
    (store) => ({
        fetching: store.clans.fetching,
        clanAccount: store.clans.clanAccount,
        warPlan: store.clans.openWarPlan
    })
)(WarPlanner);
