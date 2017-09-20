import React from 'react';
import { connect } from 'react-redux';

import ClanAccountNavigator from '../ui/ClanAccountNavigator.jsx';

import { fetchUserBoundClanAccount } from '../../flux/actions/clans';

export class WarHistory extends React.Component {
   
    render() {
        return (
                <div>
                    War Plan History
                </div>
        );
    }
};

export default connect(
    (store) => ({
        clanAccount: store.clans.clanAccount
    })
)(WarHistory);
