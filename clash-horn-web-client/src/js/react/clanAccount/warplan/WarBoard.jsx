import React from 'react';
import Grid from 'react-bootstrap/lib/Grid';
import Row from 'react-bootstrap/lib/Row';
import Col from 'react-bootstrap/lib/Col';
import Image from 'react-bootstrap/lib/Image';
import Glyphicon from 'react-bootstrap/lib/Glyphicon';


import WarPosition from './WarPosition.jsx';
import ClanBadge from '../../ui/ClanBadge.jsx';


class WarBoard extends React.Component {
    
    render() {
        if (!this.props.war) {
            return null;
        }
        
        const clanNameClassSuffix = Math.ceil(this.props.war.clan.name.length / 3);
        const enemyNameClassSuffix = Math.ceil(this.props.war.enemy.name.length / 3);
        
        return (
            <div className="war-board">
                <Row>
                    <Col md={3} mdOffset={3} sm={6} xs={6}>
                        <div className="war-board-col">
                        <div className="clan-badge" style={{'backgroundImage':  `url('${this.props.war.clan.badge}')`}} />

                            <div className="war-board-stars">
                                {this.props.war.clanScore.stars}
                            </div>
                            
                            <div className="war-board-destruction">
                                <Glyphicon glyph="fire" />
                                {' '}
                                {this.props.war.clanScore.destruction}%
                            </div>

                            <div className={`war-board-name`}>
                                {this.props.war.clan.name}
                            </div>
                        </div>
                    </Col>
                    
                    <Col md={3} sm={6} xs={6}>
                        <div className="war-board-col">
                            <div className="clan-badge" style={{'backgroundImage':  this.props.war.clan.badge }} />

                            <div className="war-board-stars">
                                {this.props.war.enemyScore.stars}
                            </div>
                            
                            <div className="war-board-destruction">
                                <Glyphicon glyph="fire" />
                                {' '}
                                {this.props.war.enemyScore.destruction}%
                            </div>

                            <div className={`war-board-name`}>
                                {this.props.war.enemy.name}
                            </div>
                        </div>
                    </Col>
                </Row>
                
                <Row>
                    <Col md={6} mdOffset={3} sm={12} xs={12}>
                    <div className="war-status">
                        <p><strong>WAR IN PROGRESS</strong></p>
                        <p className="text-secondary"><i className="glyphicon glyphicon-time"> </i>  12 hours 12 minutes to end	</p>
                        <p className="text-secondary"><i className="glyphicon glyphicon-king"> </i>  3 attacks performed, 27 available	</p>
                    </div>
                    </Col>
                </Row>
                
                {this.props.war.positions.map( (p) => 
                    <Row key={p.member.tag}>
                        <Col md={6} mdOffset={3} sm={12} xs={12}>
                            <WarPosition war={this.props.war} position={p}  />
                        </Col>
                    </Row>
                )}
            </div>
        );
    }
};

WarBoard.defaultProps = {
    war: null
};

export default WarBoard
