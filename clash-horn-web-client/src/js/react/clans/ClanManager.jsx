import React from 'react';
import { Link } from 'react-router-dom';
import Glyphicon from 'react-bootstrap/lib/Glyphicon';
import { connect } from 'react-redux';

import ClanAccountNavigator from '../ui/ClanAccountNavigator.jsx';

import { fetchUserBoundClanAccount } from '../../flux/actions/clans';

export class ClanManager extends React.Component {
   
    constructor(props) {
       super(props);

        if (!this.props.clanAccount || this.props.clanAccount.id!==this.props.match.params.cid) {
           // Account clan was not provided or do not match url provided id, so we need to fetch it from the service
           this.props.dispatch(fetchUserBoundClanAccount(this.props.match.params.cid));
       }
   }
   
    render() {
        let message = null;
        if (this.props.fetching['fetchUserBoundClanAccount']) {
            message = (<span><Glyphicon glyph="refresh" /> Loading clan account data ...</span>);
        } else if (!this.props.clanAccount) {
            message = (
                <div>
                    <p>A clan account could not be found for the id <strong>{this.props.match.params.cid}</strong>.</p>
                    <p>Check your ID or register a new account <Link to="/register">here</Link></p>
                </div>
            );
        }
        
        return (
            <ClanAccountNavigator clanAccount={this.props.clanAccount} active="" fadeInWhen={this.props.fetching['fetchUserBoundClanAccount']===false}>
                {message ||
                    <div>
                        <p>This is the <strong>{this.props.clanAccount.clan.name}</strong> clan management root component.</p>
                        <p>Remember to bookmark <strong><a href={window.location.href}>this page</a></strong> to keep your history of war plans!</p>
                        <p>Manage your clan's current war <a href={`#/${this.props.clanAccount.id}/current`}>here</a></p>
                    </div>
                }
            </ClanAccountNavigator>
        );
    }
};

export default connect(
    store => ({ 
        clanAccount: store.clans.clanAccount,
        fetching: store.clans.fetching
    })
)(ClanManager);
