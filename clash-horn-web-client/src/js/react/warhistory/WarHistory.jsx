import React from 'react';
import { connect } from 'react-redux';

import ClanAccountNavigator from '../ui/ClanAccountNavigator.jsx';

import { fetchUserBoundClanAccount } from '../../flux/actions/clans';

export class WarHistory extends React.Component {
   
    constructor(props) {
       super(props);
      
        if (!this.props.clanAccount || this.props.clanAccount.id!==this.props.match.params.cid) {
           // Clan account was not loaded on store or do not match url provided id, so we need to fetch it
           this.props.dispatch(fetchUserBoundClanAccount(this.props.match.params.cid));
       }
   }
   
    render() {
        return (
            <ClanAccountNavigator clanAccount={this.props.clanAccount} active="/history" fadeInWhen={true}>
                <div>
                    War Plan History
                </div>
            </ClanAccountNavigator>
        );
    }
};

export default connect(
    (store) => ({
        clanAccount: store.clans.clanAccount
    })
)(WarHistory);
