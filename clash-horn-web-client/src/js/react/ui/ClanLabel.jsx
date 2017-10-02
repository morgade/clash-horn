import React from 'react';
import badgeErrorImage from '../../../img/badge-error.png';

/**
 * Clan name/badge label
 */
class ClanLabel extends React.Component {
    
    constructor(props) {
        super(props);
        this.state = {
            badgeLoadError: false
        };
    }
    
    loadBadgeError() {
        this.setState({ badgeLoadError: true });
    }
    
    render() {
        if (!this.props.clan) {
            return null;
        }
        
        var badge = this.props.clan.badgeUrls ? (<img src={this.props.clan.badgeUrls.small} onError={this.loadBadgeError.bind(this)} />) : (<img src={badgeErrorImage} />) ;
        if (this.state.badgeLoadError) {
            badge = (<img src={badgeErrorImage} />);
        }
        
        return (
            this.props.clan ?
                <span className="clan-label">
                    {this.props.badgeAlignment==='left' ? badge : null }
                    {this.props.clan.name}
                    {this.props.badgeAlignment==='right' ? badge : null }
                </span>
            :
                null
        );
    }
};

ClanLabel.defaultProps = {
    clan: null,
    badgeAlignment: 'left'
};

export default ClanLabel;