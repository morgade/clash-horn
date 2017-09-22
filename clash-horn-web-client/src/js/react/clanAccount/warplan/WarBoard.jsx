import React from 'react';
import Grid from 'react-bootstrap/lib/Grid';
import Row from 'react-bootstrap/lib/Row';
import Col from 'react-bootstrap/lib/Col';
import Image from 'react-bootstrap/lib/Image';


import ClanBadge from '../../ui/ClanBadge.jsx';
import WarMemberLabel from '../../ui/WarMemberLabel.jsx';


class WarBoard extends React.Component {
    
    render() {
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
                    <Col md={3} sd={6} xs={6} className="coc-font">
                         {this.props.war.enemyScore.stars}
                        <ClanBadge clan={this.props.war.enemy} />
                        {this.props.war.enemy.name}
                    </Col>
                </Row>
                {this.props.war.positions.map( (p) => 
                    <Row key={p.member.tag}>
                        <Col md={6} mdOffset={3} sd={12} xs={12}>
                            <WarMemberLabel war={this.props.war} position={p} />
                        </Col>
                    </Row>
                )}
            </div>
        );
    }
};

export default WarBoard
