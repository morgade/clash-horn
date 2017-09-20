import React from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';


export class ClanManager extends React.Component {
   
    render() {
        return (
            this.props.clanAccount ?
                <div>
                    <p>This is the <strong>{this.props.clanAccount.clan.name}</strong> clan management root component.</p>
                    <p>Remember to bookmark <strong><a href={window.location.href}>this page</a></strong> to keep your history of war plans!</p>
                    <p>Manage your clan's current war <Link to={`${this.props.clanAccount.id}/current`}>here</Link></p>
                </div>
            :
            null
        );
    }
};

export default connect(
    store => ({ 
        clanAccount: store.clans.clanAccount
    })
)(ClanManager);
