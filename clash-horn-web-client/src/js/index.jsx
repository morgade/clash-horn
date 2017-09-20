import OurCSS from '../less/clash-horn.less';

import React from 'react';
import ReactDom from 'react-dom';
import { HashRouter, Route } from 'react-router-dom'
import { Provider } from 'react-redux'

import App from './react/App.jsx';
import store from './flux/store'

require('es6-promise').polyfill();

/**
 *  Default path routes to RegisterManager at "/register"
 *  
 *  Any other first level path is treated is an account id
 *     /guilaaf -> GroupManager
 *     /guilaaf/2YPLGY2 -> ClanManager
 *     /guilaaf/2YPLGY2/current -> WarPlanner for a clan loading or creating current war
 *     /guilaaf/2YPLGY2/history -> WarPlanner for a clan loading past wars
 */
ReactDom.render(
    <Provider store={store}>
        <HashRouter>
            <Route path="/" component={App} />
        </HashRouter>
    </Provider>
, document.getElementById('app'));