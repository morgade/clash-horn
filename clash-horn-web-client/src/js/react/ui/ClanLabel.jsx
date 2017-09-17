import React from 'react';

/**
 * Clan name/badge label
 */
class ClanLabel extends React.Component {
    
    render() {
        return (
            this.props.clan ?
                <span className="clan-label">
                    <img src={this.props.clan.badgeUrls.small} />
                    {this.props.clan.name}
                </span>
            :
                null
        );
    }
};

ClanLabel.defaultProps = {
    clan: null
};

export default ClanLabel;