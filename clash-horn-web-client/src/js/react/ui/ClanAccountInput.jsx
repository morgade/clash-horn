import React from 'react';
import FormControl from 'react-bootstrap/lib/FormControl';
import InputGroup from 'react-bootstrap/lib/InputGroup';
import Button from 'react-bootstrap/lib/Button';
import Glyphicon from 'react-bootstrap/lib/Glyphicon';

class ClanAccountInput extends React.Component {
    
    constructor(props){
        super(props);
        this.state = {
            clanAccountId: this.props.initialValue
        };
    }
    
    executeAction() {
        this.props.onAction(!this.state.clanAccountId || !this.state.clanAccountId.trim().length ? null : this.state.clanAccountId.trim());
    }
    
    render() {
        return (
            <InputGroup>
    
                <FormControl type="text" 
                        value={this.state.clanAccountId} 
                        disabled={this.props.fetching}
                        onChange={(evt) => this.setState({clanAccountId: evt.target.value}) } />

                <InputGroup.Button>
                    <Button
                        bsStyle="primary"
                        type="button" 
                        disabled={this.props.fetching}
                        onClick={this.executeAction.bind(this)}>
                            {!this.props.fetching ?
                                this.props.label
                                :
                                <Glyphicon glyph="refresh" />
                            }
                    </Button>
                </InputGroup.Button>
                
            </InputGroup>
        );
    }
};

ClanAccountInput.defaultProps = {
    fetching: false,
    initialValue: '',
    label: 'Action',
    onAction: () => {}
};

export default ClanAccountInput;