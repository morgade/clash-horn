import React from 'react';
import Col from 'react-bootstrap/lib/Col';
import Row from 'react-bootstrap/lib/Row';
import Glyphicon from 'react-bootstrap/lib/Glyphicon';
import Label from 'react-bootstrap/lib/Label';

/**
 * WarMember label
 */
class WarMemberLabel extends React.Component {
    
    stars() {
        if (this.props.position.performedAttacks.length===0) {
            return -1;
        } else {
            return this.props.position.performedAttacks.map( a => a.stars ).reduce( (o,n) => Math.max(o,n));
        }
    }
    
    render() {
        if (!this.props.war) {
            return null;
        }
        
        let war = this.props.war;
        let position = this.props.position;
        let stars = this.stars();
        
        console.log(war);
        
        return (
            <div className="wml">
                <Row>
                    <Col md={2} className="hidden-xs hidden-sm text-center">
                        <span className={`wml-th wml-th-${stars<3 ? position.enemy.townhallLevel : 'd'}`} />
                        <span className={`wml-stars wml-stars-${stars}`} />
                    </Col>
                    <Col md={10}>
                        <div className="wml-position pull-right">
                            {position.enemy.position}
                        </div>
                            
                        <h4>
                            {position.enemy.name}
                        </h4>
                        
                        <div>
                        Attacks:
                        {position.performedAttacks.map( attack => 
                            <span key={attack.attacker}>
                            <Label bsStyle={ ["danger","warning", "info","success"][stars]}>
                                <Glyphicon glyph={"star"+(stars>0?"":"-empty")} />    
                                <Glyphicon glyph={"star"+(stars>1?"":"-empty")} />    
                                <Glyphicon glyph={"star"+(stars>2?"":"-empty")} />    
                                {' '}
                                {war.positions[attack.attacker-1].member.name}
                            </Label>    
                            {' '}
                            </span>
                        )}
                        </div>
                        
                        <div>
                            Queue:
                        
                        </div>
                        
                    </Col>
                    <div className="wml-actions">
                        <Glyphicon glyph="plus" />    
                    </div>
                </Row>
            </div>
        );
    }
};

WarMemberLabel.defaultProps = {
    war: null,
    position: null
};

export default WarMemberLabel;