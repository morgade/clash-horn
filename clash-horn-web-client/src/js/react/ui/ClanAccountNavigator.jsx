import React from 'react';
import { connect } from 'react-redux';

import Row from 'react-bootstrap/lib/Row';
import Col from 'react-bootstrap/lib/Col';
import Nav from 'react-bootstrap/lib/Nav';
import NavItem from 'react-bootstrap/lib/NavItem';
import Glyphicon from 'react-bootstrap/lib/Glyphicon';
import CSSTransition  from 'react-transition-group/CSSTransition';
import TransitionGroup  from 'react-transition-group/TransitionGroup';
import { Link, Switch, Route } from 'react-router-dom';

import ClanManager from '../clans/ClanManager.jsx';
import WarPlanner from '../warplan/WarPlanner.jsx';
import WarHistory from '../warhistory/WarHistory.jsx';
import {fetchUserBoundClanAccount} from '../../flux/actions/clans';
import pathValue from '../../util/path-value';
/**
 * Clan base navigation parent component
 */
class ClanAccountNavigator extends React.Component {
    
    componentWillMount() {
        this.props.dispatch(fetchUserBoundClanAccount(this.props.match.params.cid));
    }
   
    componentWillReceiveProps(newProps) {
        if (newProps.match.params.cid !== this.props.match.params.cid) {
           this.props.dispatch(fetchUserBoundClanAccount(newProps.match.params.cid));
        }
    }
    
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
        let activeKey = slash===0?'':path.substring(slash);
        
        let message = null;
        if (this.props.fetching['fetchUserBoundClanAccount']) {
            message = (<span><Glyphicon glyph="refresh" /> Loading clan account data ...</span>);
        } else if (!this.props.clanAccount) {
            message = (
                <div>
                    <p>A clan account could not be found for the id <strong>{this.props.match.params.cid}</strong>.</p>
                    <p>Check your ID or register a new account <Link to="/register">here</Link></p>
                </div>
            );
        }
        
        return (
            <Row>
                <Col md={9}>
                    <TransitionGroup>
                        <CSSTransition key={activeKey} classNames="fade" timeout={300}>
                        {message ||
                        (
                                <Switch>
                                    <Route exact path="/:cid" component={ClanManager} key="a" />
                                    <Route exact path="/:cid/history" component={WarHistory} key="b" />
                                    <Route exact path="/:cid/:wid" component={WarPlanner} key="c" />
                                </Switch>
                        )}
                        </CSSTransition>
                    </TransitionGroup>
                    
                </Col>
                <Col md={3}>
                    <Nav bsStyle="pills" stacked activeKey={activeKey}>
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

export default connect(
    store => ({ 
        clanAccount: store.clans.clanAccount,
        fetching: store.clans.fetching
    })
)(ClanAccountNavigator);