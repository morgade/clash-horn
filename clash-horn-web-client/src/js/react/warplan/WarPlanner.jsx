import React from 'react';
import { Link } from 'react-router-dom';
import Grid from 'react-bootstrap/lib/Grid';
import Row from 'react-bootstrap/lib/Row';
import Col from 'react-bootstrap/lib/Col';
import Glyphicon from 'react-bootstrap/lib/Glyphicon';
import Image from 'react-bootstrap/lib/Image';

import WarBoard from '../ui/WarBoard.jsx';

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
    
    loadWarPlan(clanAccountId, warPlanId) {
        this.props.dispatch(fetchWarPlan(clanAccountId, warPlanId==='current'?null:warPlanId));
    }
    
    render() {
        let loadMessage = null;
        if (this.props.fetching['fetchUserBoundClanAccount']) {
            loadMessage = (<span><Glyphicon glyph="refresh" /> Loading clan account data ...</span>);
        } else if (this.props.fetching['fetchWarPlan']) {
            loadMessage = (<span><Glyphicon glyph="refresh" /> Loading war data ...</span>);
        }
    
        return loadMessage ||
            (this.props.clanAccount && this.props.warPlan ?
                <div>
                    <div>
                        This is the <strong>{this.props.clanAccount.name}</strong> current war planner.
                        <p>
                            <a href={`#/${this.props.clanAccount.id}`}>Manage your clan here</a>
                        </p>
                        <p>
                            <a href={`#/${this.props.clanAccount.id}/history`}>View your war plans log here</a>
                        </p>
                    </div>
                    <WarBoard war={this.props.warPlan} />
                </div>
            :
                <div>
                    <p>A clan account could not be found for the id <strong>{this.props.match.params.cid}</strong>.</p>
                    <p>Check your ID or register a new account <Link to="/register">here</Link></p>
                </div>
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
