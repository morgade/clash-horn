import React from 'react';
import { Link } from 'react-router-dom';
import Glyphicon from 'react-bootstrap/lib/Glyphicon';
import { connect } from 'react-redux';

import { fetchUserBoundClanAccount } from '../../flux/actions/clans'

export class ClanManager extends React.Component {
   
    constructor(props) {
       super(props);

        if (!this.props.clanAccount || this.props.clanAccount.id!==this.props.match.params.cid) {
           // Account clan was not provided or do not match url provided id, so we need to fetch it from the service
           this.props.dispatch(fetchUserBoundClanAccount(this.props.match.params.cid));
       }
   }
   
    render() {
        return (
            <div>
                {this.props.fetching['fetchUserBoundClanAccount'] ?
                        <div>
                            <Glyphicon glyph="refresh" /> Loading clan account data ...
                        </div>
                    :
                        this.props.clanAccount ?
                            <div>
                            This is the <strong>{this.props.clanAccount.clan.name}</strong> clan management root component. 
                            Remember to bookmark <strong><a href={window.location.href}>this page</a></strong> to keep your history of war plans !
                                <p>
                                    <a href={`#/${this.props.clanAccount.id}/current`}>Manage your current war here</a>
                                </p>
                                <p>
                                    <a href={`#/${this.props.clanAccount.id}/history`}>View your war plans log here</a>
                                </p>
                            </div>
                        :
                            <div>
                                <p>A clan account could not be found for the id <strong>{this.props.match.params.cid}</strong>.</p>
                                <p>Check your ID or register a new account <Link to="/register">here</Link></p>
                            </div>
                }
            </div>
        );
    }
};

export default connect(
    store => ({ 
        clanAccount: store.clans.clanAccount,
        fetching: store.clans.fetching
    })
)(ClanManager);
