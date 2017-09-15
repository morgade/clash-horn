import React from 'react';
import { connect } from 'react-redux';

import { fetchAndSetAccountClan } from '../../flux/actions/clans';

export class ClanManager extends React.Component {
   
   constructor(props) {
       super(props);
       if (!this.props.clan) {
           // Account clan is not loaded. Try to fetch it from the service
           this.props.dispatch(fetchAndSetAccountClan(this.props.match.params.cid));
       }
   }
   
    render() {
        return (
            <div>
                {this.props.clan ?
                    <div>
                        This is the <strong>{this.props.clan.name}</strong> clan management root component.
                        <p>
                            <a href={`#/${this.props.match.params.cid}/current`}>Manage your current war here</a>
                        </p>
                        <p>
                            <a href={`#/${this.props.match.params.cid}/history`}>View your war plans log here</a>
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
    store => ({ clan: store.clans.accountClan })
)(ClanManager);
