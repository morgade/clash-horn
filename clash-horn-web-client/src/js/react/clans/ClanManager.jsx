import React from 'react';
import { Link } from 'react-router-dom';
import Glyphicon from 'react-bootstrap/lib/Glyphicon';
import { connect } from 'react-redux';

import ClanAccountNavigator from '../ui/ClanAccountNavigator.jsx';

import { fetchUserBoundClanAccount } from '../../flux/actions/clans';

export class ClanManager extends React.Component {
   
    render() {
        return (
            this.props.clanAccount ?
                <div>
                    <p>This is the <strong>{this.props.clanAccount.clan.name}</strong> clan management root component.</p>
                    <p>Remember to bookmark <strong><a href={window.location.href}>this page</a></strong> to keep your history of war plans!</p>
                    <p>Manage your clan's current war <a href={`#/${this.props.clanAccount.id}/current`}>here</a></p>
                </div>
            :
            null
        );
    }
};

export default connect(
    store => ({ 
        clanAccount: store.clans.clanAccount,
        fetching: store.clans.fetching
    })
)(ClanManager);
