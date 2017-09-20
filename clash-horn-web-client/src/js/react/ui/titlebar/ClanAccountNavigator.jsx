import React from 'react';
import { connect } from 'react-redux';

import Nav from 'react-bootstrap/lib/Nav';
import NavItem from 'react-bootstrap/lib/NavItem';

class ClanAccountNavigator extends React.Component {
    
    navItem(key, label) {
        return (
            <NavItem eventKey={`${key}`} 
                    href={`#/${this.props.match.params.cid}${key}`} 
                    disabled={this.props.clanAccount===null}>
                {label}
            </NavItem>
        );
    }
    
    render() {
        let path = this.props.location.pathname;
        let slash = path.lastIndexOf('/');
        let activeKey = slash<=0?'':path.substring(slash);
        
        return (
                <Nav activeKey={activeKey}>
                    {this.navItem('', 'Account Data')}
                    {this.navItem('/current', 'Current War')}
                    {this.navItem('/history', 'War Plan History')}
                </Nav>
        );
    }
};

export default ClanAccountNavigator;