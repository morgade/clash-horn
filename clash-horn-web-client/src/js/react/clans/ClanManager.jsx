import React from 'react';
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
                                This is the <strong>{this.props.clanAccount.name}</strong> clan management root component.
                                <p>
                                    <a href={`#/${this.props.clanAccount.id}/current`}>Manage your current war here</a>
                                </p>
                                <p>
                                    <a href={`#/${this.props.clanAccount.id}/history`}>View your war plans log here</a>
                                </p>
                            </div>
                        :
                            <div>
                                Clan account not found
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
