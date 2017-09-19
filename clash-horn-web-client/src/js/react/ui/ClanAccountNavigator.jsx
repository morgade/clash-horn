import React from 'react';
import Row from 'react-bootstrap/lib/Row';
import Col from 'react-bootstrap/lib/Col';
import Nav from 'react-bootstrap/lib/Nav';
import NavItem from 'react-bootstrap/lib/NavItem';
import CSSTransition  from 'react-transition-group/CSSTransition';
import { Link } from 'react-router-dom';

/**
 * Clan base navigation parent component
 */
class ClanAccountNavigator extends React.Component {
    
    constructor(props) {
        super(props);
        this.state = {
            latestActive: null
        }
    }
    
    navItem(key, label) {
        return (
            <NavItem eventKey={key} 
                     href={`#/${this.props.clanAccount!=null?this.props.clanAccount.id:''}${key}`} 
                     disabled={this.props.clanAccount==null}>
                {label}
            </NavItem>
        );
    }
    
    render() {
        return (
            <Row>
                <Col md={9}>
                    <CSSTransition in={this.props.fadeInWhen || this.props.active!=this.state.latestActive} classNames="fade" timeout={500}>
                        {this.props.children}
                    </CSSTransition>
                </Col>
                <Col md={3}>
                {this.state.latestActive}
                {this.props.active}
                    <Nav bsStyle="pills" stacked activeKey={this.props.active}>
                        {this.navItem('', 'Account Data')}
                        {this.navItem('/current', 'Current War')}
                        {this.navItem('/history', 'War Plan History')}
                    </Nav>
                </Col>
            </Row>
        );
    }
};

ClanAccountNavigator.defaultProps = {
    fadeInWhen: true
};

export default ClanAccountNavigator;