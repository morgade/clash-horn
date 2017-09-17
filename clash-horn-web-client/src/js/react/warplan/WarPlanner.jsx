import React from 'react';
import Grid from 'react-bootstrap/lib/Grid';
import Row from 'react-bootstrap/lib/Row';
import Col from 'react-bootstrap/lib/Col';
import Glyphicon from 'react-bootstrap/lib/Glyphicon';
import Image from 'react-bootstrap/lib/Image';
import { connect } from 'react-redux';

import th9 from '../../../img/th9-small.png';

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
        if (!this.props.clanAccount) {
            loadMessage = (<span><Glyphicon glyph="refresh" /> Loading clan account data ...</span>);
        } else if (!this.props.warPlan) {
            loadMessage = (<span><Glyphicon glyph="refresh" /> Loading war data ...</span>);
        }
    
        return loadMessage ||
            (<div>
                TJF x DARK KINGS
                <Grid fluid>
                    <Row className="show-grid">
                        <Col xs={12} md={12}>
                            <span className="position">1</span>
                            <Image src={th9} rounded/>
                            <span className="villageName">DARK SLAYER</span>
                        </Col>
                    </Row>
                </Grid>
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
