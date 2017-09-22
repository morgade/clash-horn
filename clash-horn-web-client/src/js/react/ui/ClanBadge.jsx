import React from 'react';
import badgeErrorImage from '../../../img/badge-error.png';

class ClanBadge extends React.Component {
    
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
            badge = (<img src={badgeErrorImage} {...this.props} />);
        }
        
        return badge;
    }
};

ClanBadge.defaultProps = {
    clan: null
};

export default ClanBadge;