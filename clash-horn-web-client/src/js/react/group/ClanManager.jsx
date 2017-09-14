import React from 'react';
import { connect } from 'react-redux'


export class ClanManager extends React.Component {
   
    render() {
        return (
            <div>
                This is the clan management root component.
                <p>
                    <a href={`#/${this.props.routeParams.gid}/${this.props.routeParams.clanTag}/current`}>Manage your current war here</a>
                </p>
                <p>
                    <a href={`#/${this.props.routeParams.gid}/${this.props.routeParams.clanTag}/history`}>View your war plans log here</a>
                </p>
            </div>
        );
    }
};

export default connect()(ClanManager);
