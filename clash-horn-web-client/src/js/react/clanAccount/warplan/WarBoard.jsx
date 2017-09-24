import React from 'react';
import Grid from 'react-bootstrap/lib/Grid';
import Row from 'react-bootstrap/lib/Row';
import Col from 'react-bootstrap/lib/Col';
import Image from 'react-bootstrap/lib/Image';


import WarPosition from './WarPosition.jsx';
import ClanBadge from '../../ui/ClanBadge.jsx';


class WarBoard extends React.Component {
    
    render() {
        if (!this.props.war) {
            return null;
        }
        
        return (
            <div>
                <Row>
                    <Col md={3} mdOffset={3} sm={6} xs={6} className="coc-font">
                        {this.props.war.clan.name}
                        <div className="pull-right">
                            <ClanBadge clan={this.props.war.clan} />
                            {this.props.war.clanScore.stars}
                        </div>
                    </Col>
                    <Col md={3} sm={6} xs={6} className="coc-font">
                        {this.props.war.enemyScore.stars}
                        <ClanBadge clan={this.props.war.enemy} />
                        {this.props.war.enemy.name}
                    </Col>
                </Row>
                {this.props.war.positions.map( (p) => 
                    <Row key={p.member.tag}>
                        <Col md={6} mdOffset={3} sm={12} xs={12}>
                            <WarPosition war={this.props.war} position={p} onQueuePush={this.props.onPlanAttack} />
                        </Col>
                    </Row>
                )}
            </div>
        );
    }
};

WarBoard.defaultProps = {
    war: null,
    onPlanAttack: () => {}
};

export default WarBoard
