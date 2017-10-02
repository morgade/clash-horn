import React from 'react';
import { connect } from 'react-redux';
import { Switch, Route, Link } from 'react-router-dom';

import Glyphicon from 'react-bootstrap/lib/Glyphicon';
import CSSTransition  from 'react-transition-group/CSSTransition';
import TransitionGroup  from 'react-transition-group/TransitionGroup';

import AccountData from './data/AccountData.jsx';
import WarHistory from './history/WarHistory.jsx';
import WarPlanner from './warplan/WarPlanner.jsx';

import {fetchClanAccount} from '../../flux/actions/clans';

class ClanAccountManager extends React.Component {
    
    constructor(props) {
        super(props);
        
        if (!this.props.clanAccount) {
            // Load clan account on mount
            this.props.dispatch(fetchClanAccount(this.props.match.params.cid));
        }
    }
   
    componentWillReceiveProps(newProps) {
        // Reloads clanAccount when route path changes
        if (newProps.match.params.cid !== this.props.match.params.cid) {
           this.props.dispatch(fetchClanAccount(newProps.match.params.cid));
        }
    }
    
    render() {
        let message = null;
        if (this.props.fetching['fetchClanAccount']) {
            message = (<span><Glyphicon glyph="refresh" /> Loading clan account data ...</span>);
        } else if (!this.props.clanAccount) {
            message = (
                <div>
                    <p>A clan account could not be found for the id <strong>{this.props.match.params.cid}</strong>.</p>
                    <p>Check your ID or register a new account <Link to="/register">here</Link></p>
                </div>
            );
        }
        
        return message || (
                <TransitionGroup>
                    <CSSTransition key={this.props.location.pathname} classNames="fade" timeout={300}>
                        <Switch>
                            <Route exact path="/:cid" component={AccountData} />
                            <Route exact path="/:cid/history" component={WarHistory}  />
                            <Route exact path="/:cid/:wid" component={WarPlanner}  />
                        </Switch>
                    </CSSTransition>
                </TransitionGroup>
        );
    }
};

ClanAccountManager.defaultProps = {
    fadeInWhen: true
};

export default connect(
    store => ({ 
        clanAccount: store.clans.clanAccount,
        fetching: store.clans.fetching
    })
)(ClanAccountManager);