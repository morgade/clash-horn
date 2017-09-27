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
        
        var imgProps = {className: "clan-badge"};
        
        var badge = this.props.clan.badge ? (<img src={this.props.clan.badge} onError={this.loadBadgeError.bind(this)} {...imgProps} />) : (<img src={badgeErrorImage} {...imgProps} />) ;
        
        if (this.state.badgeLoadError) {
            badge = (<img src={badgeErrorImage} {...imgProps} />);
        }
        
        return badge;
    }
};

ClanBadge.defaultProps = {
    clan: null
};

export default ClanBadge;