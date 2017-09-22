import React from 'react';
import Col from 'react-bootstrap/lib/Col';
import Row from 'react-bootstrap/lib/Row';
import Glyphicon from 'react-bootstrap/lib/Glyphicon';
import Tooltip from 'react-bootstrap/lib/Tooltip';
import OverlayTrigger from 'react-bootstrap/lib/OverlayTrigger';
import Modal from 'react-bootstrap/lib/Modal';
import Label from 'react-bootstrap/lib/Label';
import Button from 'react-bootstrap/lib/Button';
import ButtonGroup from 'react-bootstrap/lib/ButtonGroup';
import FormGroup from 'react-bootstrap/lib/FormGroup';
import ControlLabel from 'react-bootstrap/lib/ControlLabel';
import FormControl from 'react-bootstrap/lib/FormControl';

/**
 * WarPosition label
 * TODO: Clean this mess (it's working, however)
 */
class WarPosition extends React.Component {
    
    constructor(props) {
        super(props);
        this.state = {
            queuePushAttackerPosition: -1,
            modal: null
        };
    }
    
    stars() {
        if (this.props.position.performedAttacks.length===0) {
            return -1;
        } else {
            return this.props.position.performedAttacks.map( a => a.stars ).reduce( (o,n) => Math.max(o,n));
        }
    }
    
    bestPerformedAttack() {
        if (this.props.position.performedAttacks.length===0) {
            return 0;
        } else {
            let maxStars = this.props.position.performedAttacks
                                        .map( a => a.stars)
                                        .reduce( (o,n) => Math.max(o, n) );
                                
            return this.props.position.performedAttacks
                                    .filter( a => a.stars === maxStars )
                                    .map( a => a.destructionPercentage)
                                    .reduce( (o,n) => Math.max(o, n));
        }
    }
    
    showPlanAttackModal() {
        this.setState({ modal: 'planAttack' });
    }
    
    close() {
        this.setState({ modal: null });
    }
    
    queue() {
        this.setState({ modal: null });
        this.props.onQueuePush(this.props.position.number, this.state.queuePushAttackerPosition);
    }
    
    tooltip(text) {
        return <Tooltip id="tooltip">{text}</Tooltip>;
    }
    
    clanMemberSelected(evt) {
        this.setState({queuePushAttackerPosition: parseInt(evt.target.value)});
    }
    
    render() {
        if (!this.props.war) {
            return null;
        }
        
        let war = this.props.war;
        let position = this.props.position;
        let stars = this.stars();
        let bestPerformedAttack = this.bestPerformedAttack();
        let bestPerformedAttackTip = "";
        
        let bestPerformedAttackContent = null;
        if (bestPerformedAttack==0) {
            bestPerformedAttackTip = "No destruction yet";
            bestPerformedAttackContent = (<Glyphicon glyph="asterisk" />);
        } else if (bestPerformedAttack===100) {
            bestPerformedAttackTip = "Position cleared!";
            bestPerformedAttackContent = (<Glyphicon glyph="ok" />); 
        } else {
            bestPerformedAttackTip = `Best attack: ${bestPerformedAttack}%`;
            bestPerformedAttackContent = bestPerformedAttack;
        }

        const modalInstance = (
            <Modal show={this.state.modal==='planAttack'} onHide={this.close.bind(this)} bsSize="sm">
              <Modal.Body>
                <FormGroup>
                    <ControlLabel>Assign a Clan Member</ControlLabel>
                    <FormControl componentClass="select" className="coc-font" value={this.state.queuePushAttackerPosition} onChange={this.clanMemberSelected.bind(this)}>
                        <option value={-1} />
                        {war.positions.map( pos => (
                            <option key={pos.member.tag} value={pos.number}>
                                {pos.number}.
                                {' '}
                                {pos.member.name}
                            </option>
                        ))}
                    </FormControl>
                </FormGroup>
              </Modal.Body>
              <Modal.Footer>
              <ButtonGroup justified>
                    <Button bsStyle="danger" href="#" onClick={this.close.bind(this)}>Cancel</Button>
                    <Button bsStyle="info" href="#" onClick={this.queue.bind(this)} disabled={this.state.queuePushAttackerPosition<1}>Confirm</Button>
              </ButtonGroup>
              </Modal.Footer>
            </Modal>
        );
        
        return (
            <div className="wp">
                {modalInstance}
                <Row>
                    <Col md={2} className="wp-th-col hidden-xs hidden-sm">
                        <span className={`wp-th wp-th-${stars<3 ? position.enemy.townhallLevel : 'd'}`} />
                        <span className={`wp-stars wp-stars-${stars}`} />
                        <OverlayTrigger overlay={this.tooltip(bestPerformedAttackTip)}>
                            <span className={`wp-destruction wp-destruction-${bestPerformedAttack>=100?'done':bestPerformedAttack>0?'incomplete':'not-done'}`}>
                                {bestPerformedAttackContent}
                            </span>
                        </OverlayTrigger>
                    </Col>
                    <Col md={10}>
                        <h4>
                            {position.number}. {position.enemy.name}
                        </h4>
                        
                        <div className="wp-queue">
                            {position.attackQueue.map( (attacker, idx) =>
                            <span>
                                {idx > 0 ? <Glyphicon glyph="chevron-left" /> : null}
                                <a key={attacker} className="wp-queue-item" onClick={ () => alert('Implementar aqui') }>
                                    {war.positions[attacker-1].member.name}
                                    {' '}
                                    <Glyphicon glyph="remove" />
                                </a>
                            </span>
                            )}
                        </div>
                        
                    </Col>
                    <div className="wp-actions">
                        <Button bsSize="xsmall" onClick={this.showPlanAttackModal.bind(this)}>
                            <Glyphicon glyph="plus" />    
                        </Button>
                    </div>
                </Row>
            </div>
        );
    }
};

WarPosition.defaultProps = {
    war: null,
    position: null,
    onQueuePush: () => {}
};

export default WarPosition;